package rs.raf.domaci6lazarbojanic11621rn.repository.specification;

import rs.raf.domaci6lazarbojanic11621rn.model.BlogPostComment;

import java.util.List;

public interface IBlogPostCommentRepository {
    List<BlogPostComment> getAllBlogPostCommentsByPostId(Integer blogPostId);
    BlogPostComment getBlogPostCommentById(Integer id);
    BlogPostComment addBlogPostComment(Integer blogPostId, BlogPostComment blogPostComment);
    boolean deleteBlogPostCommentById(Integer id);
}
