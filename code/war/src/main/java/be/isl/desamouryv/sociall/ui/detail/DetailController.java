/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.ui.detail;

import be.isl.desamouryv.sociall.domain.Artifact;
import be.isl.desamouryv.sociall.facade.ArtifactFacade;
import be.isl.desamouryv.sociall.facade.ReviewFacade;
import be.isl.desamouryv.sociall.ui.util.JsfUtils;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author Virginie
 * @param <T>
 */
public abstract class DetailController<T extends Artifact> implements Serializable {

    private static final Logger logger = Logger.getLogger(DetailController.class.getName());

    protected T artifact;
    protected Long artifactId;

    @EJB
    ArtifactFacade artifactFacade;

    @EJB
    ReviewFacade reviewFacade;

    public void preRender() {
        logger.log(Level.INFO, "preRender");
        if (artifact == null) {
            retrieveArtifact();
            if(artifact == null){
                JsfUtils.error("common.error.notfound", null);
            }
        }
    }

    private void retrieveArtifact() {
        if (FacesContext.getCurrentInstance().isPostback() && artifact == null) {
            artifact = findFromFlash();
            logger.log(Level.INFO, "artifact from flash: {0}", artifact);
        } else if (artifactId != null) {
            artifact = findFromDb(artifactId);
            logger.log(Level.INFO, "artifact from db: {0}", artifact);
        }
    }

    private T findFromFlash() {
        return (T) JsfUtils.getFromFlash("artifact");
    }

    private T findFromDb(Long id) {
        return (T) artifactFacade.findArtifact(id);
    }

    public T getArtifact() {
        return artifact;
    }

    public void setArtifact(T artifact) {
        this.artifact = artifact;
    }

    public Long getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(Long artifactId) {
        this.artifactId = artifactId;
    }

}
