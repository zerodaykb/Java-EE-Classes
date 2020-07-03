package domain.services;

import domain.Actor;
import domain.MoviesHasActors;
import domain.Movie;

import java.util.ArrayList;
import java.util.List;

public class MoviesHasActorsService {

  private static List<MoviesHasActors> db = new ArrayList<>();

  private MovieService movieService = new MovieService();
  private ActorsService actorsService = new ActorsService();

  public List<Movie> getAllMoviesByActorId(int id) {
    List<Movie> movies = new ArrayList<>();

    for(MoviesHasActors dbMoviesHasActors : db) {
      if(dbMoviesHasActors.getActorId() == id) {
        Movie movie = movieService.get(dbMoviesHasActors.getMovieId());
        movies.add(movie);
      }
    }

    return movies;
  }

  public List<Actor> getAllActorsByMovieId(int id) {
    List<Actor> actors = new ArrayList<>();

    for(MoviesHasActors dbMoviesHasActors : db) {
      if(dbMoviesHasActors.getMovieId() == id) {
        Actor actor = actorsService.get(dbMoviesHasActors.getActorId());
        actors.add(actor);
      }
    }

    return actors;
  }

  public void addMovieToActor(int idActor, int idMovie) {
    MoviesHasActors moviesHasActors = new MoviesHasActors();

    moviesHasActors.setActorId(idActor);
    moviesHasActors.setMovieId(idMovie);

    db.add(moviesHasActors);
  }
}
