package domain;

public class Rating {

  private int id;
  private int idMovie;

  private int value;

  public int getId () {
    return id;
  }

  public void setId (int id) {
    this.id = id;
  }

  public int getIdMovie () {
    return idMovie;
  }

  public void setIdMovie (int idMovie) {
    this.idMovie = idMovie;
  }

  public int getValue () {
    return value;
  }

  public void setValue (int value) {
    this.value = value;
  }

}
