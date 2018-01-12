package jdbcdemo.dao.uow;

import java.sql.SQLException;

public interface UnitOfWork {
    public void markAsNew(Entity entity);
    public void markAsDeleted(Entity entity);
    public void markAsChanged(Entity entity);
    public void saveChanges();
    public void rollback();
}
