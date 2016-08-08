/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.isl.desamouryv.sociall.facade;

import be.isl.desamouryv.sociall.domain.Tag;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Virginie
 */
@Stateless
public class TagFacadeImpl extends AbstractFacade<Tag> implements TagFacade {

    private static final Logger logger = Logger.getLogger(UserFacadeImpl.class.getName());

    @PersistenceContext(unitName = "sociallDevPU")
    protected EntityManager em;

    public TagFacadeImpl() {
        super(Tag.class);
    }

    @Override
    public List<Tag> findAll() {
        return em.createNamedQuery("Tag.findAll", Tag.class).getResultList();
    }
    
    @Override
    public List<Tag> findContaining(String query) {
        return em.createNamedQuery("Tag.findContaining", Tag.class)
                .setParameter("query", "%" + query.toLowerCase() + "%")
                .getResultList();
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }
}
