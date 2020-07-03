package jdbcdemo.dao;

import jdbcdemo.dao.repositories.RepositoryQuery;
import jdbcdemo.dao.uow.JdbcUnitOfWork;
import jdbcdemo.dao.uow.UnitOfWork;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcRepositoriesFactory implements DbCatalogFactory {

    public RepositoryQuery HsqlDbWorkdb() {
        String url = "jdbc:hsqldb:hsql://localhost/testy";

        try {
            Connection connection = DriverManager.getConnection(url);
            connection.setAutoCommit(false);

            UnitOfWork uow = new JdbcUnitOfWork(connection);
            return new JdbcRepositoryQuery(connection, uow);
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
