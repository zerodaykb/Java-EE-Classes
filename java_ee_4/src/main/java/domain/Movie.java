package domain;

public class Movie {

  private int id;
  private String title;
  private String releaseDate;

  public int getId () {
    return id;
  }

  public void setId (int id) {
    this.id = id;
  }

  public String getTitle () {
    return title;
  }

  public void setTitle (String title) {
    this.title = title;
  }

  public String getReleaseDate () {
    return releaseDate;
  }

  public void setReleaseDate (String releaseDate) {
    this.releaseDate = releaseDate;
  }
}


