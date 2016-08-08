package be.isl.desamouryv.sociall.ws.service;


import be.isl.desamouryv.sociall.domain.Artifact;
import be.isl.desamouryv.sociall.facade.ArtifactFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Virginie Desamoury <vdesamoury@gmail.com>
 */
@Path("artifacts")
@Stateless
public class ArtifactWebService {

    @EJB
    private ArtifactFacade artifactFacade;

    @GET
    @Path("latest/{max}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Artifact> latest(@PathParam("max") Integer max) {
        return artifactFacade.findLatest(max);
    }
    
    @GET
    @Path("search/{query}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Artifact> search(@PathParam("query") String query) {
        return artifactFacade.findWithString(query);
    }

}
