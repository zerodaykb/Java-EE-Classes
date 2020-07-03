package jdbcdemo.dao.uow;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcUnitOfWork implements UnitOfWork {
    private Connection connection;
    private List<Entity> entities = new ArrayList<Entity>();

    public JdbcUnitOfWork(Connection connection) throws SQLException {
        this.connection = connection;
        this.connection.setAutoCommit(false);
    }

    public void markAsNew(Entity entity) {
        entity.setState(EntityState.New);
        entities.add(entity);
    }

    public void markAsDeleted(Entity entity) {
        entity.setState(EntityState.Deleted);
        entities.add(entity);
    }

    public void markAsChanged(Entity entity) {
        entity.setState(EntityState.Changed);
        entities.add(entity);
    }

    private boolean wasRollback = false;

    public void saveChanges() {
        for(Entity entity: entities) {
            entity.persistChange();
            if(wasRollback) break;
        }

        try {
            connection.commit();
            entities.clear();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollback() {
        wasRollback = true;

        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
