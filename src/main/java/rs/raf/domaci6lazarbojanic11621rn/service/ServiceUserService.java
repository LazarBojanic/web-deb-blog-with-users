package rs.raf.domaci6lazarbojanic11621rn.service;


import rs.raf.domaci6lazarbojanic11621rn.model.ServiceUser;
import rs.raf.domaci6lazarbojanic11621rn.model.Token;
import rs.raf.domaci6lazarbojanic11621rn.repository.specification.IServiceUserRepository;

import javax.inject.Inject;
import java.util.List;

public class ServiceUserService {
    public ServiceUserService() {

    }
    @Inject
    private IServiceUserRepository serviceUserRepository;

    public List<ServiceUser> getAllServiceUsers() {
        return this.serviceUserRepository.getAllServiceUsers();
    }
    public ServiceUser getServiceUserById(Integer serviceUserId) {
        return this.serviceUserRepository.getServiceUserById(serviceUserId);
    }
    public ServiceUser addServiceUser(ServiceUser serviceUser) {
        return this.serviceUserRepository.addServiceUser(serviceUser);
    }
    public ServiceUser registerServiceUser(ServiceUser serviceUser) {
        return this.serviceUserRepository.registerServiceUser(serviceUser);
    }
    public Token loginServiceUser(ServiceUser serviceUser) {
        return this.serviceUserRepository.loginServiceUser(serviceUser);
    }
    public boolean deleteServiceUserById(Integer id) {
        return this.serviceUserRepository.deleteServiceUserById(id);
    }
}
