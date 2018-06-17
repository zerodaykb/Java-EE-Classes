package rest.services;

import domain.Person;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/people")
@Stateless
public class PersonResource {

  @PersistenceContext(unitName="demoPU")
  EntityManager em;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Person> getAll(@QueryParam("page") int page) {
    return em
      .createNamedQuery("Person.all", Person.class)
      .setMaxResults(3)
      .setFirstResult((page-1) * 3)
      .getResultList();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response Add(Person person) {
    em.persist(person);
    return Response.ok(person).build();
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response get(@PathParam("id") int id){
    Person result = em.createNamedQuery("Person.id", Person.class)
      .setParameter("personId", id)
      .getSingleResult();

    if(result == null){
      return Response.status(404).build();
    }

    return Response.ok(result).build();
  }

  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response update(@PathParam("id") int id, Person p){
    Person result = em.createNamedQuery("Person.id", Person.class)
      .setParameter("personId", id)
      .getSingleResult();

    if(result==null){
      return Response.status(404).build();
    }

    result.setFirstName(p.getFirstName());
    result.setLastName(p.getLastName());
    result.setGender(p.getGender());
    result.setBirthday(p.getBirthday());
    result.setEmail(p.getEmail());
    result.setAge(p.getAge());

    em.persist(result);
    return Response.ok().build();
  }

  @DELETE
  @Path("/{id}")
  public Response delete(@PathParam("id") int id){
    Person result = em.createNamedQuery("Person.id", Person.class)
      .setParameter("personId", id)
      .getSingleResult();

    if(result == null){
      return Response.status(404).build();
    }

    em.remove(result);
    return Response.ok().build();
  }


}
