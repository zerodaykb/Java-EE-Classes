package domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@XmlRootElement
@Entity
@NamedQueries({
  @NamedQuery(name="Product.all", query="SELECT p FROM Product p"),
  @NamedQuery(name="Product.id", query="SELECT p FROM Product p WHERE p.id=:productId")
})
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false)
  private String name;

  private Double price;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Category category;

  @OneToMany(mappedBy = "product")
  private List<Comment> comments;

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

  public Double getPrice () {
    return price;
  }

  public void setPrice (Double price) {
    this.price = price;
  }

  public Category getCategory () {
    return category;
  }

  public void setCategory (Category category) {
    this.category = category;
  }

  @XmlTransient
  public List<Comment> getComments () {
    return comments;
  }

  public void setComments (List<Comment> comments) {
    this.comments = comments;
  }
}
