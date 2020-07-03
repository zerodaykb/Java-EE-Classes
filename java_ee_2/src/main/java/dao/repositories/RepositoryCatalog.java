package dao.repositories;


import dao.mappers.UserResultMapper;
import domain.User;

import java.sql.Connection;
import java.sql.SQLException;

public class RepositoryCatalog {
    Connection connection;

    public RepositoryCatalog (Connection connection) {
        this.connection = connection;
    }

    public Repository<User> getUsersRepository() {
        try {
            return new UsersRepository(connection, new UserResultMapper());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
