package rs.raf.domaci5lazarbojanic11621rn.service;

import rs.raf.domaci5lazarbojanic11621rn.model.BlogPost;
import rs.raf.domaci5lazarbojanic11621rn.repository.specification.IBlogPostRepository;

import javax.inject.Inject;
import java.util.List;

public class BlogPostService {
    public BlogPostService() {

    }
    @Inject
    private IBlogPostRepository blogPostRepository;

    public List<BlogPost> getAllBlogPosts() {
        return this.blogPostRepository.getAllBlogPosts();
    }
    public BlogPost getBlogPostById(Integer blogPostId) {
        return this.blogPostRepository.getBlogPostById(blogPostId);
    }
    public BlogPost addBlogPost(BlogPost blogPost) {
        return this.blogPostRepository.addBlogPost(blogPost);
    }
    public boolean deleteBlogPostById(Integer id) {
        return this.blogPostRepository.deleteBlogPostById(id);
    }
}
