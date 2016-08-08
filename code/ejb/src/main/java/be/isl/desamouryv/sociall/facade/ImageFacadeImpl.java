/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.facade;

import be.isl.desamouryv.sociall.domain.Artifact;
import be.isl.desamouryv.sociall.domain.Image;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Virginie
 */
@Stateless
public class ImageFacadeImpl extends AbstractFacade<Image> implements ImageFacade {

    @PersistenceContext(unitName = "sociallDevPU")
    protected EntityManager em;

    public ImageFacadeImpl() {
        super(Image.class);
    }


    @Override
    public Image findSingleImageByArtifact(Artifact artifact) {
        List<Image> resultList = em.createNamedQuery("Image.findByArtifact", Image.class)
                .setParameter("artifact", artifact)
                .setMaxResults(1)
                .getResultList();
        Image image = null;
        if(!resultList.isEmpty()){
            image = resultList.get(0);
        }
        return image;
    }
    
    
    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    void setEm(EntityManager em) {
        this.em = em;
    }
    
    
}
