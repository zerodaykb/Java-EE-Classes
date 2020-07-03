package jdbcdemo.dao;

import jdbcdemo.dao.repositories.RepositoryQuery;

public interface DbCatalogFactory {
    public RepositoryQuery HsqlDbWorkdb();
}
