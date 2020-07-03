package dao.mappers;

import domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserResultMapper implements ResultSetMapper<User> {

  public User map (ResultSet rs) throws SQLException {
    User user = new User();

    user.setId(rs.getInt("id"));
    user.setUsername(rs.getString("username"));
    user.setPassword(rs.getString("password"));
    user.setEmail(rs.getString("email"));
    user.setPremium(rs.getBoolean("isPremium"));
    user.setAdmin(rs.getBoolean("isAdmin"));

    return user;
  }
}
