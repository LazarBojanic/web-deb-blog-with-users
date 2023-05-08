package rs.raf.domaci6lazarbojanic11621rn.api;

import rs.raf.domaci6lazarbojanic11621rn.model.ServiceUser;
import rs.raf.domaci6lazarbojanic11621rn.model.Token;
import rs.raf.domaci6lazarbojanic11621rn.service.ServiceUserService;
import rs.raf.domaci6lazarbojanic11621rn.util.TokenUtil;

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
        String token = bearerToken.split(" ")[1];
        if(TokenUtil.parseToken(token) != null){
            ServiceUser serviceUser = serviceUserService.getServiceUserById(id);
            if(serviceUser != null){
                return Response.ok().entity(serviceUser).build();
            }
            else{
                return Response.status(500).build();
            }
        }
        else{
            return Response.status(500).build();
        }

    }
    @GET
    @Path("/getAll")
    @Produces(APPLICATION_JSON)
    public Response getAllServiceUsers(@HeaderParam("authorization") String bearerToken){
        String token = bearerToken.split(" ")[1];
        if(TokenUtil.parseToken(token) != null){
            List<ServiceUser> serviceUserList = serviceUserService.getAllServiceUsers();
            if(serviceUserList != null){
                return Response.ok().entity(serviceUserList).build();
            }
            else{
                return Response.status(500).build();
            }
        }
        else{
            return Response.status(500).build();
        }

    }
    @POST
    @Path("/add")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response addServiceUser(ServiceUser serviceUser, @HeaderParam("authorization") String bearerToken){
        String token = bearerToken.split(" ")[1];
        if(TokenUtil.parseToken(token) != null){
            ServiceUser serviceUserWithId = serviceUserService.addServiceUser(serviceUser);
            if(serviceUserWithId != null){
                return Response.ok().entity(serviceUserWithId).build();
            }
            else{
                return Response.status(500).build();
            }
        }
        else{
            return Response.status(500).build();
        }
    }

    @POST
    @Path("/register")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response registerServiceUser(ServiceUser serviceUser){
        System.out.println("registeringUser1");
        ServiceUser serviceUserWithId = serviceUserService.registerServiceUser(serviceUser);
        if(serviceUserWithId != null){
            return Response.ok().entity(serviceUserWithId).build();
        }
        else{
            return Response.status(500).build();
        }
    }

    @POST
    @Path("/login")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response loginServiceUser(ServiceUser serviceUser){
        System.out.println("loggingInUser1");
        Token token = serviceUserService.loginServiceUser(serviceUser);
        if(token != null){
            return Response.ok().entity(token).build();
        }
        else{
            return Response.status(500).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(APPLICATION_JSON)
    public Response deleteServiceUser(@PathParam("id") Integer id, @HeaderParam("authorization") String bearerToken){
        String token = bearerToken.split(" ")[1];
        if(TokenUtil.parseToken(token) != null){
            boolean isDeleted = serviceUserService.deleteServiceUserById(id);
            if(isDeleted){
                return Response.ok().entity(id).build();
            }
            else{
                return Response.status(500).build();
            }
        }
        else{
            return Response.status(500).build();
        }
    }
}
