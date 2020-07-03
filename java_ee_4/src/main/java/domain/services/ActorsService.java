package domain.services;

import domain.Actor;

import java.util.ArrayList;
import java.util.List;

public class ActorsService {

  private static List<Actor> db = new ArrayList<>();
  private static int currentId = 1;

  public List<Actor> getAll() {
    return db;
  }

  public Actor get(int id) {
    for(Actor actor: db) {
      if(actor.getId() == id) {
        return actor;
      }
    }

    return null;
  }

  public void add(Actor actor) {
    actor.setId(currentId++);
    db.add(actor);
  }


}
