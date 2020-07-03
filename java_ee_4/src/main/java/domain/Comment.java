package domain;

public class Comment {

  private int id;
  private int idMovie;

  private String date;
  private String content;

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

  public String getDate () {
    return date;
  }

  public void setDate (String commentDate) {
    this.date = date;
  }

  public String getContent () {
    return content;
  }

  public void setContent (String content) {
    this.content = content;
  }
}
