package rs.raf.domaci6lazarbojanic11621rn.api;

import rs.raf.domaci6lazarbojanic11621rn.model.BlogPost;
import rs.raf.domaci6lazarbojanic11621rn.model.BlogPostComment;
import rs.raf.domaci6lazarbojanic11621rn.service.BlogPostCommentService;
import rs.raf.domaci6lazarbojanic11621rn.util.TokenUtil;

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
    public Response getBlogPostCommentById(@PathParam("blogPostId") Integer blogPostId, @HeaderParam("authorization") String bearerToken){
        String token = bearerToken.split(" ")[1];
        if(TokenUtil.parseToken(token) != null){
            BlogPostComment blogPostComment = blogPostCommentService.getBlogPostCommentById(blogPostId);
            if(blogPostComment != null){
                return Response.ok().entity(blogPostComment).build();
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
    @Path("/getAll/{blogPostId}")
    @Produces(APPLICATION_JSON)
    public Response getAllBlogPostCommentsByPostId(@PathParam("blogPostId") Integer blogPostId, @HeaderParam("authorization") String bearerToken){
        String token = bearerToken.split(" ")[1];
        if(TokenUtil.parseToken(token) != null){
            List<BlogPostComment> blogPostCommentList = blogPostCommentService.getAllBlogPostCommentsByPostId(blogPostId);
            if(blogPostCommentList != null){
                return Response.ok().entity(blogPostCommentList).build();
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
    @Path("/add/{blogPostId}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response addBlogPostComment(@PathParam("blogPostId") Integer blogPostId, BlogPostComment blogPostComment, @HeaderParam("authorization") String bearerToken){
        String token = bearerToken.split(" ")[1];
        if(TokenUtil.parseToken(token) != null){
            System.out.println(blogPostComment);
            BlogPostComment BlogPostCommentWithId = blogPostCommentService.addBlogPostComment(blogPostId, blogPostComment);
            if(BlogPostCommentWithId != null){
                return Response.ok().entity(BlogPostCommentWithId).build();
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
            boolean isDeleted = blogPostCommentService.deleteBlogPostCommentById(id);
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
