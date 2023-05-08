package rs.raf.domaci6lazarbojanic11621rn.api;


import rs.raf.domaci6lazarbojanic11621rn.database.BlogDatabase;
import rs.raf.domaci6lazarbojanic11621rn.model.BlogPost;
import rs.raf.domaci6lazarbojanic11621rn.model.BlogPostComment;
import rs.raf.domaci6lazarbojanic11621rn.repository.specification.IBlogPostRepository;
import rs.raf.domaci6lazarbojanic11621rn.service.BlogPostService;
import rs.raf.domaci6lazarbojanic11621rn.util.TokenUtil;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.ArrayList;
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
        String token = bearerToken.split(" ")[1];
        if(TokenUtil.parseToken(token) != null){
            BlogPost blogPost = blogPostService.getBlogPostById(id);
            if(blogPost != null){
                return Response.ok().entity(blogPost).build();
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
    public Response getAllBlogPosts(@HeaderParam("authorization") String bearerToken){
        String token = bearerToken.split(" ")[1];
        System.out.println("token " + token);
        if(TokenUtil.parseToken(token) != null){
            List<BlogPost> blogPostList = blogPostService.getAllBlogPosts();
            if(blogPostList != null){
                return Response.ok().entity(blogPostList).build();
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
    public Response addBlogPost(BlogPost blogPost, @HeaderParam("authorization") String bearerToken){
        String token = bearerToken.split(" ")[1];
        if(TokenUtil.parseToken(token) != null){
            BlogPost blogPostWithId = blogPostService.addBlogPost(blogPost);
            if(blogPostWithId != null){
                return Response.ok().entity(blogPostWithId).build();
            }
            else{
                return Response.status(500).build();
            }
        }
        else{
            return Response.status(500).build();
        }
    }
    @DELETE
    @Path("/delete/{id}")
    @Produces(APPLICATION_JSON)
    public Response deleteBlogPost(@PathParam("id") Integer id, @HeaderParam("authorization") String bearerToken){
        String token = bearerToken.split(" ")[1];
        if(TokenUtil.parseToken(token) != null){
            boolean isDeleted = blogPostService.deleteBlogPostById(id);
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
