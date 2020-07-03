package domain.services;

import domain.Rating;

import java.util.ArrayList;
import java.util.List;

public class RatingsService {

  private static List<Rating> db = new ArrayList<>();
  private static int currentId = 1;

  public double getByIdMovie(int id) {
    double result = 0;
    int ratingsCount = 0;

    for(Rating rating: db) {
      if(rating.getIdMovie() == id) {
        result += rating.getValue();
        ratingsCount++;
      }
    }

    if(ratingsCount == 0) {
      return 0;
    }

    return result/ratingsCount;
  }

  public void add(Rating rating) {
    rating.setId(currentId++);
    db.add(rating);
  }

}
