package rest;

import domain.Actor;
import domain.Movie;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/actors")
@Stateless
public class Actors {

  @PersistenceContext(unitName="demoPU")
  EntityManager em;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Actor> getAll() {
    return em.createNamedQuery("Actor.all", Actor.class).getResultList();

  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Actor actor) {
      em.persist(actor);

      return Response.ok(actor).build();
  }

  @POST
  @Path("/{idActor}/movies/{idMovie}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response update(@PathParam("idActor") int idActor, @PathParam("idMovie") int idMovie) {

    Actor actor = em.createNamedQuery("Actor.id", Actor.class)
      .setParameter("actorId", idActor)
      .getSingleResult();

    Movie movie = em.createNamedQuery("Movie.id", Movie.class)
      .setParameter("movieId", idMovie)
      .getSingleResult();

    if(actor == null || movie == null || movie.getActors().contains(actor)) {
      return Response.status(404).build();
    }

    movie.getActors().add(actor);
    actor.getMovies().add(movie);

    return Response.ok().build();
  }


  @Path("/{id}/movies")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Movie> getAllByActor(@PathParam("id") int id) {
    Actor result = em.createNamedQuery("Actor.id", Actor.class)
      .setParameter("actorId", id)
      .getSingleResult();

    if(result == null) {
      return null;
    }

    return result.getMovies();

  }
}

