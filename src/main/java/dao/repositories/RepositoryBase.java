package dao.repositories;

import dao.mappers.ResultSetMapper;
import domain.HaveId;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class RepositoryBase<TEntity extends HaveId> implements Repository<TEntity> {

  protected Connection connection;
  protected Statement createTable;
  protected PreparedStatement insert;
  protected PreparedStatement selectAll;
  protected PreparedStatement selectOne;
  protected PreparedStatement selectOneByUsername;
  protected PreparedStatement selectOneByEmail;
  protected PreparedStatement update;
  protected PreparedStatement delete;

  ResultSetMapper<TEntity> mapper;

  protected RepositoryBase(Connection connection, ResultSetMapper<TEntity> mapper) throws SQLException {
    this.mapper = mapper;
    this.connection = connection;

    createTable = connection.createStatement();
    createTable();
    insert = connection.prepareStatement(insertSql());
    update = connection.prepareStatement(updateSql());
    delete = connection.prepareStatement(deleteSql());
    selectAll = connection.prepareStatement(selectAllSql());
    selectOne = connection.prepareStatement(selectOneSql());
    selectOneByUsername = connection.prepareStatement(selectOneByUsername());
    selectOneByEmail = connection.prepareStatement(selectOneByEmail());
  }

  public void createTable(){
    try {
      ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
      boolean tableExists = false;
      while(rs.next()){
        if(rs.getString("TABLE_NAME").equalsIgnoreCase(tableName())){
          tableExists = true;
          break;
        }
      }
      if(!tableExists) {
        createTable.executeUpdate(createTableSql());
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void add(TEntity entity){
    try {
      setupInsert(entity);
      insert.executeUpdate();

    } catch(SQLException ex){
      ex.printStackTrace();
    }
  }

  public void update(TEntity entity) {
    try {
      setupUpdate(entity);
      update.executeUpdate();

    } catch(SQLException ex){
      ex.printStackTrace();
    }
  }


  public List<TEntity> getAll() {
    List<TEntity> result = new ArrayList<TEntity>();
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

  public TEntity getOneByUsername(String value) {
    try {
      selectOneByUsername.setString(1, value);
      ResultSet rs = selectOneByUsername.executeQuery();
      while(rs.next()) {
        return mapper.map(rs);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

  public TEntity getOneByEmail(String value) {
    try {
      selectOneByEmail.setString(1, value);
      ResultSet rs = selectOneByEmail.executeQuery();
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

  private String selectOneByUsername() {
    return "SELECT * FROM " + tableName() + " WHERE username=?";
  }

  private String selectOneByEmail() {
    return "SELECT * FROM " + tableName() + " WHERE email=?";
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
