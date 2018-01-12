package jdbcdemo.dao.repositories;

import java.util.List;

import jdbcdemo.domain.HaveId;

public interface Repository<TEntity extends HaveId> {

	public List<TEntity> getAll();
	public TEntity getOne(int id);

	public void add(TEntity person);
	public void update(TEntity person);
	public void delete(TEntity entity);
	public void createTable();

}