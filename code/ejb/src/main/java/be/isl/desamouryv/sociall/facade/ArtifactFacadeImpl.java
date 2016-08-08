/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.facade;

import be.isl.desamouryv.sociall.domain.Artifact;
import be.isl.desamouryv.sociall.util.PredicateHelper;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Virginie
 */
@Stateless
public class ArtifactFacadeImpl extends AbstractFacade<Artifact> implements ArtifactFacade {
    
    private static final Logger logger = Logger.getLogger(ArtifactFacadeImpl.class.getName());
    
    @PersistenceContext(unitName = "sociallDevPU")
    protected EntityManager em;

    public ArtifactFacadeImpl() {
        super(Artifact.class);
    }
    
    @Override
    public List<Artifact> findWithString(String string) {
        logger.log(Level.INFO, "findWithString {0}", string);
        
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Artifact> criteriaQuery = criteriaBuilder.createQuery(Artifact.class);
        Root<Artifact> artifactRoot = criteriaQuery.from(Artifact.class);
        
        List<Predicate> predicates = PredicateHelper.generateArtifactTitleLikePredicates(string, criteriaBuilder, artifactRoot);
        
        criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
        
        TypedQuery<Artifact> query = em.createQuery(criteriaQuery);
        List<Artifact> resultList = query.getResultList();
        
        logger.log(Level.INFO, "{0} results found", resultList.size());
        
        return resultList;
    }

    
    @Override
    public Artifact findArtifact(Long id){
        Artifact artifact = this.find(id);
        if(id != null){
            this.refresh(artifact);
        }
        return artifact;
    }
    
    
    @Override
    public List<Artifact> findLatest(int max) {
        int maxResults = 5;
        if(max > 0 || max <= 25){
            maxResults = max;
        }
        return em.createNamedQuery("Artifact.findLatest", Artifact.class)
                .setMaxResults(maxResults)
                .getResultList();
    }
    

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }
    
    void setEntityManager(EntityManager em) {
        this.em = em;
    }

    
}
