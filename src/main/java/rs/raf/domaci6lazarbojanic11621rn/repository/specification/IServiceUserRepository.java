package rs.raf.domaci6lazarbojanic11621rn.repository.specification;

import rs.raf.domaci6lazarbojanic11621rn.model.BlogPost;
import rs.raf.domaci6lazarbojanic11621rn.model.ServiceUser;
import rs.raf.domaci6lazarbojanic11621rn.model.Token;

import java.util.List;

public interface IServiceUserRepository {
    List<ServiceUser> getAllServiceUsers();
    ServiceUser getServiceUserById(Integer id);
    ServiceUser addServiceUser(ServiceUser serviceUser);
    ServiceUser registerServiceUser(ServiceUser serviceUser);
    Token loginServiceUser(ServiceUser serviceUser);
    boolean deleteServiceUserById(Integer id);
}
