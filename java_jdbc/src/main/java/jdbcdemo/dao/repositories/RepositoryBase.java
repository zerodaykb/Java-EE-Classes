package jdbcdemo.dao.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jdbcdemo.dao.mappers.ResultSetMapper;
import jdbcdemo.dao.uow.Entity;
import jdbcdemo.dao.uow.UnitOfWork;
import jdbcdemo.dao.uow.UnitOfWorkRepository;
import jdbcdemo.domain.HaveId;

public abstract class RepositoryBase<TEntity extends HaveId> implements Repository<TEntity>, UnitOfWorkRepository {

	protected Connection connection;
	protected Statement createTable;
	protected PreparedStatement insert;
	protected PreparedStatement selectAll;
	protected PreparedStatement selectOne;
	protected PreparedStatement update;
	protected PreparedStatement delete;
	
	ResultSetMapper<TEntity> mapper;
	UnitOfWork uow;
	
	protected RepositoryBase(Connection connection, ResultSetMapper<TEntity> mapper, UnitOfWork uow) throws SQLException {
		this.mapper = mapper;
		this.connection = connection;
		this.uow = uow;

		createTable = connection.createStatement();
		insert = connection.prepareStatement(insertSql());
		update = connection.prepareStatement(updateSql());
		delete = connection.prepareStatement(deleteSql());
		selectAll = connection.prepareStatement(selectAllSql());
		selectOne = connection.prepareStatement(selectOneSql());
	}

	public void createTable(){
		try {
			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			boolean tableExists = false;
			while(rs.next()){
				if(rs.getString("TABLE_NAME").equalsIgnoreCase(tableName())){
					tableExists=true;
					break;
				}
			}
			if(!tableExists)
				createTable.executeUpdate(createTableSql());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void add(TEntity entity) {
		Entity ent = prepareEntity(entity);
		uow.markAsNew(ent);
	}

	public void update(TEntity entity) {
		Entity ent = prepareEntity(entity);
		uow.markAsChanged(ent);
	}

	public void delete(TEntity entity) {
		Entity ent = prepareEntity(entity);
		uow.markAsDeleted(ent);
	}

	private Entity prepareEntity(TEntity entity) {
		Entity ent = new Entity();
		ent.setEntity(entity);
		ent.setRepository(this);
		return ent;
	}


	public void persistAdd(Entity entity){
		try {
			setupInsert((TEntity) entity.getEntity());
			insert.executeUpdate();
		} catch(SQLException ex){
			ex.printStackTrace();
			uow.rollback();
		}
	}

	public void persistUpdate(Entity entity) {
		try {
			setupUpdate((TEntity) entity.getEntity());
			update.executeUpdate();
		} catch(SQLException ex){
			ex.printStackTrace();
			uow.rollback();
		}
	}

	public void persistDelete(Entity entity) {

		try {
			delete.setInt(1, entity.getEntity().getId());
			delete.executeUpdate();
		} catch(SQLException ex){
			ex.printStackTrace();
			uow.rollback();
		}
	}

	public List<TEntity> getAll(){
		List<TEntity> result = new ArrayList<>();
		try {
			ResultSet rs = selectAll.executeQuery();
			while(rs.next()){
				result.add(mapper.map(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public TEntity getOne(int id){
		try {
			selectOne.setInt(1, id);
			ResultSet rs = selectOne.executeQuery();
			while(rs.next()) {
				return mapper.map(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	private String selectAllSql() {
		return "SELECT * FROM " + tableName();
	}

	private String selectOneSql() {
		return "SELECT * FROM " + tableName() + " WHERE id=?";
	}

	private String createTableSql() {
		return String.format("CREATE TABLE %s (%s)",
				tableName(),
				prepareSchema()
		);
	}

	private String insertSql() {
		return String.format("INSERT INTO %s (%s) VALUES (%s)",
				tableName(),
				prepareParams(),
				prepareQuestionMarks()
		);
	}

	private String updateSql() {
		return String.format("UPDATE %s SET(%s) = (%s) WHERE id=?",
				tableName(),
				prepareParams(),
				prepareQuestionMarks()
		);
	}

	private String deleteSql() {
		return String.format("DELETE FROM %s WHERE id=?", tableName());
	}

	private String prepareQuestionMarks() {
		return String.join(", ", Collections.nCopies(params().length, "?"));
	}

	private String prepareParams() {
		return String.join(", ", params());
	}

	private String prepareSchema() {
		return String.join(", ", schema());
	}

	protected abstract void setupUpdate(TEntity entity) throws SQLException;
	protected abstract void setupInsert(TEntity entity) throws SQLException;
	protected abstract String tableName();
	protected abstract String[] schema();
	protected abstract String[] params();

}
