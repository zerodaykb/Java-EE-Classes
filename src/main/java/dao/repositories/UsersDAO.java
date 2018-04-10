package dao.repositories;

import domain.User;

interface UsersDAO {
  public boolean areValuesUsed(String username, String email);

}
