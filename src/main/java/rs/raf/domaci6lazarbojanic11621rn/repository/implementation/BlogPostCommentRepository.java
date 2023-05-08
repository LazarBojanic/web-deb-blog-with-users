package rs.raf.domaci5lazarbojanic11621rn.repository.implementation;

import rs.raf.domaci5lazarbojanic11621rn.database.BlogDatabase;
import rs.raf.domaci5lazarbojanic11621rn.model.BlogPost;
import rs.raf.domaci5lazarbojanic11621rn.model.BlogPostComment;
import rs.raf.domaci5lazarbojanic11621rn.repository.specification.IBlogPostCommentRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlogPostCommentRepository implements IBlogPostCommentRepository {
    @Override
    public List<BlogPostComment> getAllBlogPostCommentsByPostId(Integer blogPostId) {
        List<BlogPostComment> blogPostCommentList = new ArrayList<>();
        String query = "SELECT * FROM blog_post_comment WHERE blog_post_id = ?";
        try (PreparedStatement preparedStatement = BlogDatabase.getInstance().getConnection().prepareStatement(query)){
            preparedStatement.setInt(1, blogPostId);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    Integer blogPostIdColumn = resultSet.getInt("blog_post_id");
                    String author = resultSet.getString("author");
                    String content = resultSet.getString("content");
                    BlogPostComment blogPostComment = new BlogPostComment(id, blogPostIdColumn, author, content);
                    blogPostCommentList.add(blogPostComment);
                }
            }
            catch (SQLException e) {
                return null;
            }
        }
        catch (SQLException e) {
            return null;
        }
        return blogPostCommentList;
    }

    @Override
    public BlogPostComment getBlogPostCommentById(Integer id) {
        String query = "SELECT * FROM blog_post_comment WHERE id = ?";
        try (PreparedStatement preparedStatement = BlogDatabase.getInstance().getConnection().prepareStatement(query)){
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    Integer idColumn = resultSet.getInt("id");
                    Integer blogPostIdColumn = resultSet.getInt("blog_post_id");
                    String author = resultSet.getString("author");
                    String content = resultSet.getString("content");
                    BlogPostComment blogPostComment = new BlogPostComment(idColumn, blogPostIdColumn, author, content);
                    return blogPostComment;
                }
            }
            catch (SQLException e) {
                return null;
            }
        }
        catch (SQLException e) {
            return null;
        }
        return null;
    }

    @Override
    public BlogPostComment addBlogPostComment(Integer blogPostId, BlogPostComment blogPostComment) {
        String query = "INSERT INTO blog_post_comment(blog_post_id, author, content) VALUES(?, ?, ?)";
        try(PreparedStatement preparedStatement = BlogDatabase.getInstance().getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            System.out.println("print1");
            preparedStatement.setInt(1, blogPostId);
            preparedStatement.setString(2, blogPostComment.getAuthor());
            preparedStatement.setString(3, blogPostComment.getContent());
            Integer affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("print2");
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    System.out.println("print3");
                    Integer id = generatedKeys.getInt(generatedKeys.findColumn("id"));
                    blogPostComment.setId(id);
                    return blogPostComment;
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
        catch (Exception e){
            System.out.println("print6 " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteBlogPostCommentById(Integer id) {
        String query = "DELETE FROM blog_post_comment WHERE id = ?";
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
