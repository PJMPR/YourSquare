package rest;

import model.User;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import java.sql.SQLException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/user")
@Stateless
public class UserResources {


//    Mapper mapper = new DozerBeanMapper();

    @PersistenceContext
    EntityManager entityManager;

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getAll(){
//        List<UserDto> result = new ArrayList<UserDto>();
//        System.out.println("zaczynam pobierac dane");
//        for(User u: entityManager.createNamedQuery("user.all",User.class).getResultList()){
//            result.add(mapper.map(u, UserDto.class));
//        }
//
//        System.out.println("koncze");
//        return Response.ok(new GenericEntity<List<UserDto>>(result){}).build();
//    }


//  TODO Ogloszenia danego usera
//  @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/{id}/ad")
//    public Response getPersonAd(@PathParam("id") int userId){
//        Ad a = entityManager.createNamedQuery("ad.id", Ad.class)
//                .setParameter("id", userId)
//                .getSingleResult();
//        if(a==null)
//            return Response.status(Status.NOT_FOUND).build();
//        List<WalletDto> results = new ArrayList<WalletDto>();
//        for(Wallet w: a.getWallets()){
//            results.add(mapper.map(w, WalletDto.class));
//        }
//        return Response.ok(new GenericEntity<List<WalletDto>>(results){}).build();
//    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAll() throws SQLException {
        return entityManager.createNativeQuery("SELECT * FROM User", User.class).getResultList();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Add(User user) {
        entityManager.persist(user);
        return Response.ok(user.getId()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, User u) {
        User result = entityManager.createNamedQuery("user.id", User.class)
                .setParameter("userId", id)
                .getSingleResult();
        if (result == null) {
            return Response.status(404).build();
        }
        u.setName(u.getName());
        u.setSurname(u.getSurname());
        u.setEmail(u.getEmail());
        u.setPassword(u.getPassword());
        entityManager.persist(result);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) {
        User result = entityManager.createNamedQuery("user.id", User.class)
                .setParameter("userId", id)
                .getSingleResult();
        if (result == null) {
            return Response.status(404).build();
        }
        return Response.ok(result).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        User result = entityManager.createNamedQuery("user.id", User.class)
                .setParameter("userId", id)
                .getSingleResult();
        if (result == null)
            return Response.status(404).build();
        entityManager.remove(result);
        return Response.ok().build();
    }
}