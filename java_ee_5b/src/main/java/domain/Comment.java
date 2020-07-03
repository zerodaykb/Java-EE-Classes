package domain;

import javax.persistence.*;

@Entity
@NamedQueries({
  @NamedQuery(name="Comment.id", query="SELECT c FROM Comment c WHERE c.id=:commentId")
})

public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne
  private Movie movie;

  private String date;
  private String content;

  public int getId () {
    return id;
  }

  public void setId (int id) {
    this.id = id;
  }

  public Movie getMovie () {
    return movie;
  }

  public void setMovie (Movie movie) {
    this.movie = movie;
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
