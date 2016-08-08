/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.facade;

import be.isl.desamouryv.sociall.domain.Artifact;
import be.isl.desamouryv.sociall.domain.Review;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Virginie
 */
@Stateless
public class ReviewFacadeImpl extends AbstractFacade<Review> implements ReviewFacade {

    @PersistenceContext(unitName = "sociallDevPU")
    protected EntityManager em;

    public ReviewFacadeImpl() {
        super(Review.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }
    
    public Double getAverageRatingForArtifact(Artifact artifact){
        return em.createNamedQuery("Review.getAverageRatingForArtifact", Double.class)
                .setParameter("artifact", artifact)
                .getSingleResult();
    }
}
