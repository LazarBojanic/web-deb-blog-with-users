package rs.raf.domaci6lazarbojanic11621rn.api;

import rs.raf.domaci6lazarbojanic11621rn.model.BlogPostComment;
import rs.raf.domaci6lazarbojanic11621rn.service.BlogPostCommentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
@Path("blog_post_comment")
public class BlogPostCommentResource {
    @Inject
    BlogPostCommentService blogPostCommentService;

    @GET
    @Path("/get/{blogPostId}")
    @Produces(APPLICATION_JSON)
    public Response getBlogPostCommentById(@PathParam("blogPostId") Integer blogPostId, @HeaderParam("authorization") String bearerToken){
        try{
            BlogPostComment blogPostComment = blogPostCommentService.getBlogPostCommentById(blogPostId);
            return Response.ok().entity(blogPostComment).build();
        }
        catch(Exception e){
            return Response.status(500).build();
        }
    }
    @GET
    @Path("/getAll/{blogPostId}")
    @Produces(APPLICATION_JSON)
    public Response getAllBlogPostCommentsByPostId(@PathParam("blogPostId") Integer blogPostId, @HeaderParam("authorization") String bearerToken){
        try{
            List<BlogPostComment> blogPostCommentList = blogPostCommentService.getAllBlogPostCommentsByPostId(blogPostId);
            return Response.ok().entity(blogPostCommentList).build();
        }
        catch(Exception e){
            return Response.status(500).build();
        }
    }
    @POST
    @Path("/add/{blogPostId}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response addBlogPostComment(@PathParam("blogPostId") Integer blogPostId, BlogPostComment blogPostComment, @HeaderParam("authorization") String bearerToken){
        try{
            BlogPostComment BlogPostCommentWithId = blogPostCommentService.addBlogPostComment(blogPostId, blogPostComment);
            return Response.ok().entity(BlogPostCommentWithId).build();
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
            boolean isDeleted = blogPostCommentService.deleteBlogPostCommentById(id);
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
