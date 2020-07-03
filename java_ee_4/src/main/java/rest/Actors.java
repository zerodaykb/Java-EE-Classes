package rest;


import domain.Actor;
import domain.Movie;
import domain.services.MoviesHasActorsService;
import domain.services.ActorsService;
import domain.services.MovieService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/actors")
public class Actors {

  private ActorsService actorsService = new ActorsService();
  private MovieService movieService = new MovieService();
  private MoviesHasActorsService moviesHasActorsService = new MoviesHasActorsService();

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Actor> getAll() {
    return actorsService.getAll();
  }


  @POST
  @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Actor actor) {
      actorsService.add(actor);

      return Response.ok(actor.getId()).build();
  }

  @POST
  @Path("/{idActor}/movies/{idMovie}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response update(
    @PathParam("idActor") int idActor,
    @PathParam("idMovie") int idMovie
  ) {
    Actor dbActor = actorsService.get(idActor);
    Movie dbMovie = movieService.get(idMovie);

    if(dbActor == null || dbMovie == null) {
      return Response.status(404).build();
    }

    List<Movie> actorsMovies = moviesHasActorsService.getAllMoviesByActorId(idActor);

    if(actorsMovies.contains(dbMovie)) {
      return Response.status(400).build();
    }

    moviesHasActorsService.addMovieToActor(idActor, idMovie);

    return Response.ok().build();
  }


  @Path("/{id}/movies")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Movie> getAllByActor(@PathParam("id") int id) {
    Actor actor = actorsService.get(id);

    if(actor == null) {
      return null;
    }

    return moviesHasActorsService.getAllMoviesByActorId(id);
  }


}

