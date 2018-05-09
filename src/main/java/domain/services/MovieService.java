package domain.services;

import domain.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieService {

  private static List<Movie> db = new ArrayList<>();
  private static int currentId = 1;

  public List<Movie> getAll() {
    return db;
  }

  public Movie get(int id) {
    for(Movie movie: db) {
      if(movie.getId() == id) {
        return movie;
      }
    }

    return null;
  }

  public void add(Movie movie) {
    movie.setId(currentId++);
    db.add(movie);
  }

  public void update(Movie movie) {
    for(Movie dbMovie: db) {
      if(dbMovie.getId() == movie.getId()) {
        dbMovie.setTitle(movie.getTitle());
        dbMovie.setReleaseDate(movie.getReleaseDate());
      }
    }
  }

}
