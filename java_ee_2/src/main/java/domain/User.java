package domain;

public class User implements HaveId {

  private int id;
  private String username;
  private String password;
  private String email;

  private boolean isPremium;
  private boolean isAdmin;

  @Override
  public int getId () {
    return id;
  }

  public void setId (int id) {
    this.id = id;
  }

  public String getUsername () {
    return username;
  }

  public void setUsername (String username) {
    this.username = username;
  }

  public String getPassword () {
    return password;
  }

  public void setPassword (String password) {
    this.password = password;
  }

  public String getEmail () {
    return email;
  }

  public void setEmail (String email) {
    this.email = email;
  }

  public boolean isPremium () {
    return isPremium;
  }

  public void setPremium (boolean premium) {
    isPremium = premium;
  }

  public boolean isAdmin () {
    return isAdmin;
  }

  public void setAdmin (boolean admin) {
    isAdmin = admin;
  }
}
