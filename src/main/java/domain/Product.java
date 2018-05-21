package domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@XmlRootElement
@Entity
@NamedQueries({
  @NamedQuery(
    name="Product.all",
    query="SELECT p FROM Product p"
  ),
  @NamedQuery(
    name="Product.id",
    query="SELECT p FROM Product p WHERE p.id=:productId"
  ),
  @NamedQuery(
    name="Product.search",
    query="SELECT p FROM Product p " +
          "WHERE p.price >= :price_from " +
          "AND p.price <= :price_to " +
          "AND p.name LIKE :name " +
          "AND p.category LIKE :category"
  )
})
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;
  private String category;
  private double price;

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

  public double getPrice () {
    return price;
  }

  public void setPrice (double price) {
    this.price = price;
  }

  public String getCategory () {
    return category;
  }

  public void setCategory (String category) {
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
