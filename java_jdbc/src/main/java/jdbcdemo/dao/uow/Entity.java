package jdbcdemo.dao.uow;

import jdbcdemo.domain.HaveId;

import java.sql.SQLException;

public class Entity {

    private HaveId entity;
    private UnitOfWorkRepository repository;
    private EntityState state;

    public HaveId getEntity() {
        return entity;
    }

    public void setEntity(HaveId entity) {
        this.entity = entity;
    }

    public UnitOfWorkRepository getRepository() {
        return repository;
    }

    public void setRepository(UnitOfWorkRepository repository) {
        this.repository = repository;
    }

    public EntityState getState() {
        return state;
    }

    public void setState(EntityState state) {
        this.state = state;
    }

    public void persistChange() {
        switch(state) {
            case Changed:
                repository.persistUpdate(this);
                break;
            case Deleted:
                repository.persistDelete(this);
                break;
            case New:
                repository.persistAdd(this);
                break;
            default:
                break;
        }
    }

}
