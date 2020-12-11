package temp.jersey.comfing;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/restapi")
public class ApplicationConfig extends ResourceConfig {
    public ApplicationConfig() {
        packages("temp.jersey.resource");
    }
}