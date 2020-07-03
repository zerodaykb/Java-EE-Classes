package domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Rating {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne
  private Movie movie;

  private int value;

  public int getId () {
    return id;
  }

  public void setId (int id) {
    this.id = id;
  }

  public int getValue () {
    return value;
  }

  public void setValue (int value) {
    this.value = value;
  }

  public Movie getMovie () {
    return movie;
  }

  public void setMovie (Movie movie) {
    this.movie = movie;
  }

}
