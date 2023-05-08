package rs.raf.domaci5lazarbojanic11621rn.repository.specification;

import rs.raf.domaci5lazarbojanic11621rn.model.BlogPost;

import java.util.List;

public interface IBlogPostRepository {
    List<BlogPost> getAllBlogPosts();
    BlogPost getBlogPostById(Integer id);
    BlogPost addBlogPost(BlogPost blogPost);
    boolean deleteBlogPostById(Integer id);
}
