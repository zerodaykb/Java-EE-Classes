package rest;

import domain.Actor;
import domain.Comment;
import domain.Movie;
import domain.Rating;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/movies")
@Stateless
public class Movies {

  @PersistenceContext(unitName="demoPU")
  EntityManager em;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Movie> getAll() {
    return em.createNamedQuery("Movie.all", Movie.class).getResultList();

  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response Add(Movie movie) {

    if(movie.getTitle() == null) {
      return Response.status(404).build();
    }

    em.persist(movie);
    return Response.ok(movie).build();
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response get(@PathParam("id") int id) {
    Movie result = em.createNamedQuery("Movie.id", Movie.class)
      .setParameter("movieId", id)
      .getSingleResult();

    if(result == null) {
      return Response.status(404).build();
    }

    return Response.ok(result).build();
  }

  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response update(@PathParam("id") int id, Movie movie) {
    Movie result = em.createNamedQuery("Movie.id", Movie.class)
      .setParameter("movieId", id)
      .getSingleResult();

    if(result == null) {
      return Response.status(404).build();
    }

    result.setTitle(movie.getTitle());
    result.setReleaseDate(movie.getReleaseDate());
    em.persist(result);

    return Response.ok().build();
  }

  @GET
  @Path("/{id}/comments")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Comment> getComments(@PathParam("id") int id) {
    Movie result = em.createNamedQuery("Movie.id", Movie.class)
      .setParameter("movieId", id)
      .getSingleResult();

    if(result == null) {
      return null;
    }

    return result.getComments();
  }

  @POST
  @Path("/{id}/comments")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addComment(@PathParam("id") int id, Comment comment) {
    Movie result = em.createNamedQuery("Movie.id", Movie.class)
      .setParameter("movieId", id)
      .getSingleResult();

    if(result == null) {
      return null;
    }

    result.getComments().add(comment);
    comment.setMovie(result);
    em.persist(comment);

    return Response.ok().build();
  }

  @DELETE
  @Path("/{idMovie}/comments/{idComment}")
  public Response delete(@PathParam("idMovie") int idMovie,
                         @PathParam("idComment") int idComment) {

    Movie movie = em.createNamedQuery("Movie.id", Movie.class)
      .setParameter("movieId", idMovie)
      .getSingleResult();

    if(movie == null) {
      return Response.status(404).build();
    }

    Comment comment = em.createNamedQuery("Comment.id", Comment.class)
      .setParameter("commentId", idComment)
      .getSingleResult();

    if(comment == null || comment.getMovie().getId() != idMovie) {
      return Response.status(404).build();
    }

    movie.getComments().remove(comment);
    em.remove(comment);
    return Response.ok().build();
  }

  @GET
  @Path("/{id}/ratings")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Rating> getRating(@PathParam("id") int id) {
    Movie result = em.createNamedQuery("Movie.id", Movie.class)
      .setParameter("movieId", id)
      .getSingleResult();

    if(result == null) {
      return null;
    }

    return result.getRatings();
  }

  @POST
  @Path("/{id}/ratings")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addRating(@PathParam("id") int id, Rating rating) {
    Movie result = em.createNamedQuery("Movie.id", Movie.class)
      .setParameter("movieId", id)
      .getSingleResult();

    if(result == null) {
      return null;
    }

    result.getRatings().add(rating);
    rating.setMovie(result);
    em.persist(rating);

    return Response.ok().build();
  }

  @GET
  @Path("/{id}/actors")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Actor> getAllActorsByMovie(@PathParam("id") int id) {
    Movie result = em.createNamedQuery("Movie.id", Movie.class)
      .setParameter("movieId", id)
      .getSingleResult();

    if(result == null) {
      return null;
    }

    return result.getActors();
  }




}
