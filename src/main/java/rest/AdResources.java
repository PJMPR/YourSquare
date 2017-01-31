package rest;

import model.*;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("ad")
@Stateless

public class AdResources {


//    Mapper mapper = new DozerBeanMapper();

    @PersistenceContext
    EntityManager entityManager;

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getAll(){
//        List<AdDto> result = new ArrayList<AdDto>();
//        System.out.println("zaczynam pobierac dane");
//        for(Ad a: entityManager.createNamedQuery("ad.all",Ad.class).getResultList()){
//            result.add(mapper.map(a, AdDto.class));
//        }
//
//        System.out.println("koncze");
//        return Response.ok(new GenericEntity<List<AdDto>>(result){}).build();
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


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Add(Ad ad) {
        entityManager.persist(ad);
        return Response.ok(ad.getId()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, Ad a) {
        Ad result = entityManager.createNamedQuery("ad.id", Ad.class)
                .setParameter("adId", id)
                .getSingleResult();
        if (result == null) {
            return Response.status(404).build();
        }
        a.setTitle(a.getTitle());
        a.setContent(a.getContent());
        a.setFee(a.getFee());
        entityManager.persist(result);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) {
        Ad result = entityManager.createNamedQuery("ad.id", Ad.class)
                .setParameter("adId", id)
                .getSingleResult();
        if (result == null) {
            return Response.status(404).build();
        }
        return Response.ok(result).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        Ad result = entityManager.createNamedQuery("ad.id", Ad.class)
                .setParameter("adId", id)
                .getSingleResult();
        if (result == null)
            return Response.status(404).build();
        entityManager.remove(result);
        return Response.ok().build();
    }
}


