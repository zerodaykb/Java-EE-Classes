package domain;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@XmlRootElement
@Entity
@NamedQueries({
  @NamedQuery(name="Movie.all", query="SELECT m FROM Movie m"),
  @NamedQuery(name="Movie.id", query="SELECT m FROM Movie m WHERE m.id=:movieId")
})
public class Movie {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String title;
  private String releaseDate;

  @OneToMany(mappedBy = "movie")
  private List<Comment> comments;

  @OneToMany(mappedBy = "movie")
  private List<Rating> ratings;

  @ManyToMany(mappedBy = "movies")
  private List<Actor> actors;


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

  @XmlTransient
  public List<Comment> getComments() {
    return comments;
  }

  public void setComments (List<Comment> comments) {
    this.comments = comments;
  }

  @XmlTransient
  public List<Rating> getRatings () {
    return ratings;
  }

  public void setRatings (List<Rating> ratings) {
    this.ratings = ratings;
  }

  @XmlTransient
  public List<Actor> getActors () {
    return actors;
  }

  public void setActors (List<Actor> actors) {
    this.actors = actors;
  }

}


