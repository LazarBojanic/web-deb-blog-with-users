package rs.raf.domaci6lazarbojanic11621rn.service;

import rs.raf.domaci6lazarbojanic11621rn.model.BlogPost;
import rs.raf.domaci6lazarbojanic11621rn.model.BlogPostComment;
import rs.raf.domaci6lazarbojanic11621rn.repository.specification.IBlogPostCommentRepository;

import javax.inject.Inject;
import java.util.List;

public class BlogPostCommentService {
    public BlogPostCommentService() {

    }
    @Inject
    private IBlogPostCommentRepository blogPostCommentRepository;

    public List<BlogPostComment> getAllBlogPostCommentsByPostId(Integer blogPostId) {
        return this.blogPostCommentRepository.getAllBlogPostCommentsByPostId(blogPostId);
    }
    public BlogPostComment getBlogPostCommentById(Integer id) {
        return this.blogPostCommentRepository.getBlogPostCommentById(id);
    }
    public BlogPostComment addBlogPostComment(Integer blogPostId, BlogPostComment blogPostComment) {
        return this.blogPostCommentRepository.addBlogPostComment(blogPostId, blogPostComment);
    }
    public boolean deleteBlogPostCommentById(Integer id) {
        return this.blogPostCommentRepository.deleteBlogPostCommentById(id);
    }
}
