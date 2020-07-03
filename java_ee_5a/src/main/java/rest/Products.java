package rest;

import domain.Comment;
import domain.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/products")
@Stateless
public class Products {

	@PersistenceContext(unitName="demoPU")
	EntityManager em;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getAll() {
		return em.createNamedQuery("Product.all", Product.class).getResultList();
	}

  @Path("/search")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getQuery(
		@QueryParam("price_from") int price_from,
		@DefaultValue("100000") @QueryParam("price_to") int price_to,
    @DefaultValue("") @QueryParam("name") String name,
		@DefaultValue("") @QueryParam("category") String category) {


		return em.createNamedQuery("Product.search", Product.class)
      .setParameter("price_from", price_from)
      .setParameter("price_to", price_to)
      .setParameter("name", '%' + name + '%')
      .setParameter("category", '%' + category + '%')
      .getResultList();
	}

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Add(Product product) {
		em.persist(product);

		return Response.ok().build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") int id){
		Product result = em.createNamedQuery("Product.id", Product.class)
			.setParameter("productId", id)
			.getSingleResult();

		if(result == null){
			return Response.status(404).build();
		}

		return Response.ok(result).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int id, Product p){
		Product result = em.createNamedQuery("Product.id", Product.class)
			.setParameter("productId", id)
			.getSingleResult();

		if(result == null){
			return Response.status(404).build();
		}

		result.setCategory(p.getCategory());
		result.setName(p.getName());
		result.setPrice(p.getPrice());

		em.persist(result);
		return Response.ok().build();
	}

	@GET
	@Path("/{id}/comments")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Comment> getRating(@PathParam("id") int id) {
		Product result = em.createNamedQuery("Product.id", Product.class)
			.setParameter("productId", id)
			.getSingleResult();

		if(result == null) {
			return null;
		}

		return result.getComments();
	}

	@POST
	@Path("/{id}/comments")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addRating(@PathParam("id") int id, Comment comment) {
		Product result = em.createNamedQuery("Product.id", Product.class)
			.setParameter("productId", id)
			.getSingleResult();

		if(result == null) {
			return null;
		}

		result.getComments().add(comment);
		comment.setProduct(result);
		em.persist(comment);

		return Response.ok().build();
	}


	@DELETE
	@Path("/{idProduct}/comments/{idComment}")
	public Response delete(@PathParam("idProduct") int idProduct,
												 @PathParam("idComment") int idComment) {

		Product result = em.createNamedQuery("Product.id", Product.class)
			.setParameter("productId", idProduct)
			.getSingleResult();

		if(result == null) {
			return Response.status(404).build();
		}

		Comment comment = em.createNamedQuery("Comment.id", Comment.class)
			.setParameter("commentId", idComment)
			.getSingleResult();

		if(comment == null || comment.getProduct().getId() != idProduct) {
			return Response.status(404).build();
		}

		result.getComments().remove(comment);
		em.remove(comment);

		return Response.ok().build();
	}


}
