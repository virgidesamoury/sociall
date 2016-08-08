
package be.isl.desamouryv.sociall.ws.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Virginie Desamoury <vdesamoury@gmail.com>
 */
@javax.ws.rs.ApplicationPath("/")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(be.isl.desamouryv.sociall.ws.service.ArtifactWebService.class);
    }
    
}
