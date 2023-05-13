package rs.raf.domaci6lazarbojanic11621rn;

import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import rs.raf.domaci6lazarbojanic11621rn.cors.CorsFilter;
import rs.raf.domaci6lazarbojanic11621rn.filter.AuthFilter;
import rs.raf.domaci6lazarbojanic11621rn.repository.implementation.BlogPostCommentRepository;
import rs.raf.domaci6lazarbojanic11621rn.repository.implementation.BlogPostRepository;
import rs.raf.domaci6lazarbojanic11621rn.repository.implementation.ServiceUserRepository;
import rs.raf.domaci6lazarbojanic11621rn.repository.specification.IBlogPostCommentRepository;
import rs.raf.domaci6lazarbojanic11621rn.repository.specification.IBlogPostRepository;
import rs.raf.domaci6lazarbojanic11621rn.repository.specification.IServiceUserRepository;
import rs.raf.domaci6lazarbojanic11621rn.service.BlogPostCommentService;
import rs.raf.domaci6lazarbojanic11621rn.service.BlogPostService;
import rs.raf.domaci6lazarbojanic11621rn.service.ServiceUserService;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class App extends ResourceConfig {
    public App(){
        packages("rs.raf.domaci6lazarbojanic11621rn.api");
        register(AuthFilter.class);
        register(CorsFilter.class);
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {

                this.bind(ServiceUserRepository.class).to(IServiceUserRepository.class).in(Singleton.class);
                this.bindAsContract(ServiceUserService.class);

                this.bind(BlogPostRepository.class).to(IBlogPostRepository.class).in(Singleton.class);
                this.bindAsContract(BlogPostService.class);

                this.bind(BlogPostCommentRepository.class).to(IBlogPostCommentRepository.class).in(Singleton.class);
                this.bindAsContract(BlogPostCommentService.class);
            }
        };
        register(binder);


    }
}