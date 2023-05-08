package rs.raf.domaci5lazarbojanic11621rn.api;

import rs.raf.domaci5lazarbojanic11621rn.model.BlogPost;
import rs.raf.domaci5lazarbojanic11621rn.model.BlogPostComment;
import rs.raf.domaci5lazarbojanic11621rn.service.BlogPostCommentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
@Path("blog_post_comment")
public class BlogPostCommentResource {
    @Inject
    BlogPostCommentService blogPostCommentService;

    @GET
    @Path("/get/{blogPostId}")
    @Produces(APPLICATION_JSON)
    public Response getBlogPostCommentById(@PathParam("blogPostId") Integer blogPostId){
        BlogPostComment blogPostComment = blogPostCommentService.getBlogPostCommentById(blogPostId);
        if(blogPostComment != null){
            return Response.ok().entity(blogPostComment).build();
        }
        else{
            return Response.noContent().build();
        }
    }
    @GET
    @Path("/getAll/{blogPostId}")
    @Produces(APPLICATION_JSON)
    public Response getAllBlogPostCommentsByPostId(@PathParam("blogPostId") Integer blogPostId){
        List<BlogPostComment> blogPostCommentList = blogPostCommentService.getAllBlogPostCommentsByPostId(blogPostId);
        if(blogPostCommentList != null){
            return Response.ok().entity(blogPostCommentList).build();
        }
        else{
            return Response.serverError().build();
        }
    }
    @POST
    @Path("/add/{blogPostId}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response addBlogPostComment(@PathParam("blogPostId") Integer blogPostId, BlogPostComment blogPostComment){
        System.out.println(blogPostComment);
        BlogPostComment BlogPostCommentWithId = blogPostCommentService.addBlogPostComment(blogPostId, blogPostComment);
        if(BlogPostCommentWithId != null){
            return Response.ok().entity(BlogPostCommentWithId).build();
        }
        else{
            return Response.serverError().build();
        }
    }
    @DELETE
    @Path("/delete/{id}")
    @Produces(APPLICATION_JSON)
    public Response deleteBlogPost(@PathParam("id") Integer id){
        boolean isDeleted = blogPostCommentService.deleteBlogPostCommentById(id);
        if(isDeleted){
            return Response.ok().entity(id).build();
        }
        else{
            return Response.serverError().build();
        }
    }
}
