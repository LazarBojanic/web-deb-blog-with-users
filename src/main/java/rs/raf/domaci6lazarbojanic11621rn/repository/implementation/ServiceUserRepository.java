package rs.raf.domaci6lazarbojanic11621rn.repository.implementation;

import rs.raf.domaci6lazarbojanic11621rn.database.BlogDatabase;
import rs.raf.domaci6lazarbojanic11621rn.model.BlogPost;
import rs.raf.domaci6lazarbojanic11621rn.model.ServiceUser;
import rs.raf.domaci6lazarbojanic11621rn.model.Token;
import rs.raf.domaci6lazarbojanic11621rn.repository.specification.IServiceUserRepository;
import rs.raf.domaci6lazarbojanic11621rn.util.Hasher;
import rs.raf.domaci6lazarbojanic11621rn.util.TokenUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceUserRepository implements IServiceUserRepository {
    @Override
    public List<ServiceUser> getAllServiceUsers() {
        List<ServiceUser> serviceUserList = new ArrayList<>();
        String query = "SELECT * FROM service_user";
        try (PreparedStatement preparedStatement = BlogDatabase.getInstance().getConnection().prepareStatement(query)){
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    String username = resultSet.getString("username");
                    String pass = resultSet.getString("pass");
                    ServiceUser serviceUser = new ServiceUser(id, username, pass);
                    serviceUserList.add(serviceUser);
                }
            }
            catch (SQLException e) {
                return null;
            }
        }
        catch (SQLException e) {
            return null;
        }
        return serviceUserList;
    }

    @Override
    public ServiceUser getServiceUserById(Integer serviceUserId) {
        String query = "SELECT * FROM service_user WHERE id = ?";
        try (PreparedStatement preparedStatement = BlogDatabase.getInstance().getConnection().prepareStatement(query)){
            preparedStatement.setInt(1, serviceUserId);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    String username = resultSet.getString("username");
                    String pass = resultSet.getString("pass");
                    ServiceUser serviceUser = new ServiceUser(id, username, pass);
                    return serviceUser;
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
    public ServiceUser addServiceUser(ServiceUser serviceUser) {
        String query = "INSERT INTO service_user(username, pass) VALUES(?, ?)";
        try(PreparedStatement preparedStatement = BlogDatabase.getInstance().getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            System.out.println("print1");
            preparedStatement.setString(1, serviceUser.getUsername());
            preparedStatement.setString(2, serviceUser.getPass());
            Integer affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("print2");
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    System.out.println("print3");
                    Integer id = generatedKeys.getInt(generatedKeys.findColumn("id"));
                    serviceUser.setId(id);
                    return serviceUser;
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
    public ServiceUser registerServiceUser(ServiceUser serviceUser) {
        String query = "SELECT * FROM service_user WHERE username = ?";
        try (PreparedStatement preparedStatement = BlogDatabase.getInstance().getConnection().prepareStatement(query)){
            preparedStatement.setString(1, serviceUser.getUsername());
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (!resultSet.next()) {
                    serviceUser.setPass(Hasher.hashPassword(serviceUser.getPass()));
                    return addServiceUser(serviceUser);
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
    public Token loginServiceUser(ServiceUser serviceUser) {
        String query = "SELECT * FROM service_user WHERE username = ?";
        try (PreparedStatement preparedStatement = BlogDatabase.getInstance().getConnection().prepareStatement(query)){
            preparedStatement.setString(1, serviceUser.getUsername());
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    String hashedPass = resultSet.getString("pass");
                    if(Hasher.checkPassword(serviceUser.getPass(), hashedPass)){
                        serviceUser.setId(id);
                        serviceUser.setPass(hashedPass);
                        return new Token(TokenUtil.generate(serviceUser));
                    }
                    else{
                        return null;
                    }
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
    public boolean deleteServiceUserById(Integer id) {
        String query = "DELETE FROM service_user WHERE id = ?";
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
