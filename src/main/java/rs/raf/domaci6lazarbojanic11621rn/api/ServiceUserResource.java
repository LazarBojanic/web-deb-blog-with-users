package rs.raf.domaci6lazarbojanic11621rn.api;

import rs.raf.domaci6lazarbojanic11621rn.model.ServiceUser;
import rs.raf.domaci6lazarbojanic11621rn.model.Token;
import rs.raf.domaci6lazarbojanic11621rn.service.ServiceUserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("service_user")
public class ServiceUserResource {
    @Inject
    ServiceUserService serviceUserService;

    @GET
    @Path("/get/{id}")
    @Produces(APPLICATION_JSON)
    public Response getServiceUserById(@PathParam("id") Integer id, @HeaderParam("authorization") String bearerToken){
        try{
            ServiceUser serviceUser = serviceUserService.getServiceUserById(id);
            return Response.ok().entity(serviceUser).build();
        }
        catch(Exception e){
            return Response.status(500).build();
        }
    }
    @GET
    @Path("/getAll")
    @Produces(APPLICATION_JSON)
    public Response getAllServiceUsers(@HeaderParam("authorization") String bearerToken){
        try{
            List<ServiceUser> serviceUserList = serviceUserService.getAllServiceUsers();
            return Response.ok().entity(serviceUserList).build();
        }
        catch(Exception e){
            return Response.status(500).build();
        }
    }
    @POST
    @Path("/add")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response addServiceUser(ServiceUser serviceUser, @HeaderParam("authorization") String bearerToken){
        try{
            ServiceUser serviceUserWithId = serviceUserService.addServiceUser(serviceUser);
            return Response.ok().entity(serviceUserWithId).build();
        }
        catch(Exception e){
            return Response.status(500).build();
        }
    }

    @POST
    @Path("/register")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response registerServiceUser(ServiceUser serviceUser){
        try{
            ServiceUser serviceUserWithId = serviceUserService.registerServiceUser(serviceUser);
            return Response.ok().entity(serviceUserWithId).build();
        }
        catch(Exception e){
            return Response.status(500).build();
        }
    }

    @POST
    @Path("/login")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response loginServiceUser(ServiceUser serviceUser){
        try{
            Token token = serviceUserService.loginServiceUser(serviceUser);
            return Response.ok().entity(token).build();
        }
        catch(Exception e){
            return Response.status(500).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(APPLICATION_JSON)
    public Response deleteServiceUser(@PathParam("id") Integer id, @HeaderParam("authorization") String bearerToken){
        try{
            boolean isDeleted = serviceUserService.deleteServiceUserById(id);
            if(isDeleted){
                return Response.ok().entity(id).build();
            }
            else{
                return Response.status(500).build();
            }
        }
        catch(Exception e){
            return Response.status(500).build();
        }
    }
}
