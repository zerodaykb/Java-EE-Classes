package domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.sql.Timestamp;

@Entity
@NamedQueries({
  @NamedQuery(name="Comment.id", query="SELECT c FROM Comment c WHERE c.id=:commentId")
})

public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
  )
  private Timestamp timestamp;

  @Column(nullable = false)
  private String content;

  @ManyToOne
  private Product product;

  public int getId () {
    return id;
  }

  public void setId (int id) {
    this.id = id;
  }

  @XmlTransient
  public Product getProduct () {
    return product;
  }

  public void setProduct (Product product) {
    this.product = product;
  }

  public Timestamp getTimestamp () {
    return timestamp;
  }

  public void setTimestamp (Timestamp timestamp) {
    this.timestamp = timestamp;
  }


  public String getContent () {
    return content;
  }

  public void setContent (String content) {
    this.content = content;
  }
}
