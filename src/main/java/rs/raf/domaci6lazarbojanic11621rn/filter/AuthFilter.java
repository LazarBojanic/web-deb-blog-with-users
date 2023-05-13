package rs.raf.domaci6lazarbojanic11621rn.filter;

import rs.raf.domaci6lazarbojanic11621rn.api.BlogPostCommentResource;
import rs.raf.domaci6lazarbojanic11621rn.api.BlogPostResource;
import rs.raf.domaci6lazarbojanic11621rn.service.ServiceUserService;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import javax.ws.rs.ext.Provider;
@Provider
public class AuthFilter implements ContainerRequestFilter {

    @Inject
    ServiceUserService serviceUserService;
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        try{
            if(isAuthRequired(requestContext)){
                String bearerToken = requestContext.getHeaderString("authorization");
                if(bearerToken.startsWith("Bearer")){
                    String token = bearerToken.split(" ")[1];
                    if (this.serviceUserService.parseToken(token) == null) {
                        System.out.println("unauthorized");
                        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
                    }
                }
                else{
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
                }
            }
        }
        catch (Exception exception) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private boolean isAuthRequired(ContainerRequestContext req) {
        if (req.getUriInfo().getPath().contains("login") || req.getUriInfo().getPath().contains("register")) {
            return false;
        }
        List<Object> matchedResources = req.getUriInfo().getMatchedResources();
        for (Object matchedResource : matchedResources) {
            if (matchedResource instanceof BlogPostResource || matchedResource instanceof BlogPostCommentResource) {
                return true;
            }
        }
        return false;
    }
}
