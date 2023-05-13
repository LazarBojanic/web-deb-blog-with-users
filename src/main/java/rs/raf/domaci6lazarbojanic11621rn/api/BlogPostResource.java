package rs.raf.domaci6lazarbojanic11621rn.api;


import rs.raf.domaci6lazarbojanic11621rn.model.BlogPost;
import rs.raf.domaci6lazarbojanic11621rn.model.BlogPostComment;
import rs.raf.domaci6lazarbojanic11621rn.service.BlogPostService;
import rs.raf.domaci6lazarbojanic11621rn.service.TokenService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;


@Path("blog_post")
public class BlogPostResource {
    @Inject
    BlogPostService blogPostService;

    @GET
    @Path("/get/{id}")
    @Produces(APPLICATION_JSON)
    public Response getBlogPostById(@PathParam("id") Integer id, @HeaderParam("authorization") String bearerToken){
        try{
            BlogPost blogPost = blogPostService.getBlogPostById(id);
            return Response.ok().entity(blogPost).build();
        }
        catch(Exception e){
            return Response.status(500).build();
        }
    }
    @GET
    @Path("/getAll")
    @Produces(APPLICATION_JSON)
    public Response getAllBlogPosts(@HeaderParam("authorization") String bearerToken){
        try{
            List<BlogPost> blogPostList = blogPostService.getAllBlogPosts();
            return Response.ok().entity(blogPostList).build();
        }
        catch(Exception e){
            return Response.status(500).build();
        }
    }
    @POST
    @Path("/add")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response addBlogPost(BlogPost blogPost, @HeaderParam("authorization") String bearerToken){
        try{
            BlogPost blogPostWithId = blogPostService.addBlogPost(blogPost);
            return Response.ok().entity(blogPostWithId).build();
        }
        catch(Exception e){
            return Response.status(500).build();
        }
    }
    @DELETE
    @Path("/delete/{id}")
    @Produces(APPLICATION_JSON)
    public Response deleteBlogPost(@PathParam("id") Integer id, @HeaderParam("authorization") String bearerToken){
        try{
            boolean isDeleted = blogPostService.deleteBlogPostById(id);
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
