package domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@XmlRootElement
@Entity
@NamedQueries({
  @NamedQuery(name="Actor.all", query="SELECT p FROM Actor p"),
  @NamedQuery(name="Actor.id", query="SELECT a FROM Actor a WHERE a.id=:actorId")
})
public class Actor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;

  @ManyToMany
  private List<Movie> movies;

  public int getId () {
    return id;
  }

  public void setId (int id) {
    this.id = id;
  }

  public String getName () {
    return name;
  }

  public void setName (String name) {
    this.name = name;
  }

  @XmlTransient
  public List<Movie> getMovies () {
    return movies;
  }

  public void setMovies (List<Movie> movies) {
    this.movies = movies;
  }

}
