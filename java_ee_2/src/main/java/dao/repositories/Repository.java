package dao.repositories;

import domain.HaveId;

import java.util.List;

public interface Repository<TEntity extends HaveId> {
  public List<TEntity> getAll();
  public TEntity getOne(int id);
  public TEntity getOneByUsername(String value);
  public TEntity getOneByEmail(String value);

  public void add(TEntity user);
  public void update(TEntity user);
  public void createTable();
}
