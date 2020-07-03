package domain.services;

import domain.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentsService {

  private static List<Comment> db = new ArrayList<>();
  private static int currentId = 1;

  public List<Comment> getAllByIdMovie(int id) {
    List<Comment> commentsToReturn = new ArrayList<>();

    // tu jest przeiterowane całej listy, ale gdyby była baza danych to
    // po prostu sql znalazłby po danym id i byłoby szybko
    for(Comment comment: db) {
      if(comment.getIdMovie() == id) {
        commentsToReturn.add(comment);
      }
    }

    return commentsToReturn;
  }

  public void add(Comment comment) {
    comment.setId(currentId++);
    db.add(comment);
  }

  public Comment get(int id) {
    for(Comment comment: db) {
      if(comment.getId() == id) {
        return comment;
      }
    }

    return null;
  }

  public void delete(Comment comment) {
    db.remove(comment);
  }

}
