package rs.raf.domaci5lazarbojanic11621rn;

import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import rs.raf.domaci5lazarbojanic11621rn.cors.CorsFilter;
import rs.raf.domaci5lazarbojanic11621rn.database.BlogDatabase;
import rs.raf.domaci5lazarbojanic11621rn.repository.implementation.BlogPostCommentRepository;
import rs.raf.domaci5lazarbojanic11621rn.repository.implementation.BlogPostRepository;
import rs.raf.domaci5lazarbojanic11621rn.repository.specification.IBlogPostCommentRepository;
import rs.raf.domaci5lazarbojanic11621rn.repository.specification.IBlogPostRepository;
import rs.raf.domaci5lazarbojanic11621rn.service.BlogPostCommentService;
import rs.raf.domaci5lazarbojanic11621rn.service.BlogPostService;
import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class App extends ResourceConfig {
    public App(){
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(BlogPostRepository.class).to(IBlogPostRepository.class).in(Singleton.class);
                this.bindAsContract(BlogPostService.class);

                this.bind(BlogPostCommentRepository.class).to(IBlogPostCommentRepository.class).in(Singleton.class);
                this.bindAsContract(BlogPostCommentService.class);
            }
        };
        register(binder);
        packages("rs.raf.domaci5lazarbojanic11621rn.api");
        register(CorsFilter.class);

    }
}