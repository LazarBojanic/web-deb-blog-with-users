package rs.raf.domaci6lazarbojanic11621rn.repository.implementation;

import rs.raf.domaci6lazarbojanic11621rn.database.BlogDatabase;
import rs.raf.domaci6lazarbojanic11621rn.model.BlogPost;
import rs.raf.domaci6lazarbojanic11621rn.repository.specification.IBlogPostRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BlogPostRepository implements IBlogPostRepository {
    @Override
    public List<BlogPost> getAllBlogPosts() {
        List<BlogPost> blogPostList = new ArrayList<>();
        String query = "SELECT * FROM blog_post";
        try (PreparedStatement preparedStatement = BlogDatabase.getInstance().getConnection().prepareStatement(query)){
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    Date date_published = resultSet.getDate("date_published");
                    String author = resultSet.getString("author");
                    String content = resultSet.getString("content");
                    BlogPost blogPost = new BlogPost(id, title, date_published, author, content);
                    blogPostList.add(blogPost);
                }
            }
            catch (SQLException e) {
                return null;
            }
        }
        catch (SQLException e) {
            return null;
        }
        return blogPostList;
    }

    @Override
    public BlogPost getBlogPostById(Integer blogPostId) {
        String query = "SELECT * FROM blog_post WHERE id = ?";
        try (PreparedStatement preparedStatement = BlogDatabase.getInstance().getConnection().prepareStatement(query)){
            preparedStatement.setInt(1, blogPostId);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    Date date_published = resultSet.getDate("date_published");
                    String author = resultSet.getString("author");
                    String content = resultSet.getString("content");
                    BlogPost blogPost = new BlogPost(id, title, date_published, author, content);
                    return blogPost;
                }
                else{
                    return null;
                }
            }
            catch (SQLException e){
                return null;
            }
        }
        catch (SQLException e) {
            return null;
        }
    }

    @Override
    public BlogPost addBlogPost(BlogPost blogPost) {
        String query = "INSERT INTO blog_post(title, date_published, author, content) VALUES(?, ?, ?, ?)";
        try(PreparedStatement preparedStatement = BlogDatabase.getInstance().getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            System.out.println("print1");
            preparedStatement.setString(1, blogPost.getTitle());
            preparedStatement.setDate(2, blogPost.getDate_published());
            preparedStatement.setString(3, blogPost.getAuthor());
            preparedStatement.setString(4, blogPost.getContent());
            Integer affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("print2");
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    System.out.println("print3");
                    Integer id = generatedKeys.getInt(generatedKeys.findColumn("id"));
                    blogPost.setId(id);
                    return blogPost;
                }
                else {
                    System.out.println("print4");
                    return null;
                }
            }
            else{
                System.out.println("print5");
                return null;
            }
        }
        catch (SQLException e){
            System.out.println("print6 " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteBlogPostById(Integer id) {
        String query = "DELETE FROM blog_post WHERE id = ?";
        try (PreparedStatement preparedStatement = BlogDatabase.getInstance().getConnection().prepareStatement(query)){
            preparedStatement.setInt(1, id);
            Integer rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }
        catch (SQLException e) {
            return false;
        }
    }
}
