package rest;

import domain.Actor;
import domain.Comment;
import domain.Movie;
import domain.Rating;
import domain.services.MoviesHasActorsService;
import domain.services.CommentsService;
import domain.services.MovieService;
import domain.services.RatingsService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/movies")
public class Movies {

  private MoviesHasActorsService moviesHasActorsService = new MoviesHasActorsService();

  private MovieService movieService = new MovieService();
  private CommentsService commentsService = new CommentsService();
  private RatingsService ratingsService = new RatingsService();

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Movie> getAll() {
    return movieService.getAll();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response Add(Movie movie) {

    if(movie.getTitle() == null) {
      return Response.status(404).build();
    }

    movieService.add(movie);
    return Response.ok(movie.getId()).build();
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response get(@PathParam("id") int id) {
    Movie dbMovie = movieService.get(id);

    if(dbMovie == null) {
      return Response.status(404).build();
    }

    return Response.ok(dbMovie).build();
  }

  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response update(@PathParam("id") int id, Movie movie) {
    Movie result = movieService.get(id);

    if(result == null) {
      return Response.status(404).build();
    }

    movie.setId(result.getId());
    movieService.update(movie);

    return Response.ok().build();
  }

  @GET
  @Path("/{id}/comments")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Comment> getComments(@PathParam("id") int id) {
    Movie result = movieService.get(id);

    if(result == null) {
      return null;
    }

    return commentsService.getAllByIdMovie(id);
  }

  @POST
  @Path("/{id}/comments")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addComment(@PathParam("id") int id, Comment comment) {
    Movie result = movieService.get(id);

    if(result == null) {
      return null;
    }

    comment.setIdMovie(id);
    commentsService.add(comment);

    return Response.ok().build();
  }

  @DELETE
  @Path("/{idMovie}/comments/{idComment}")
  public Response delete(@PathParam("idMovie") int idMovie,
                         @PathParam("idComment") int idComment) {
    Movie result = movieService.get(idMovie);

    if(result == null) {
      return Response.status(404).build();
    }

    Comment comment = commentsService.get(idComment);

    if(comment == null || comment.getIdMovie() != idMovie) {
      return Response.status(404).build();
    }

    commentsService.delete(comment);
    return Response.ok().build();
  }

  @GET
  @Path("/{id}/ratings")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getRating(@PathParam("id") int id) {
    Movie result = movieService.get(id);

    if(result == null) {
      return null;
    }

    double ratingResult = ratingsService.getByIdMovie(id);

    return Response.ok(ratingResult).build();
  }

  @POST
  @Path("/{id}/ratings")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addRating(@PathParam("id") int id, Rating rating) {
    Movie result = movieService.get(id);

    if(result == null) {
      return null;
    }

    rating.setIdMovie(id);
    ratingsService.add(rating);

    return Response.ok().build();
  }



  @GET
  @Path("/{id}/actors")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Actor> getAllActorsByMovie(@PathParam("id") int id) {
    Movie result = movieService.get(id);

    if(result == null) {
      return null;
    }

    return moviesHasActorsService.getAllActorsByMovieId(id);
  }




}
