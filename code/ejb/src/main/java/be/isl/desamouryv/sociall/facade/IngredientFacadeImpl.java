/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.facade;

import be.isl.desamouryv.sociall.domain.Ingredient;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Virginie
 */

//Infos:   EJB5181:Portable JNDI names for EJB ConversationFacadeImpl: [java:global/ear-0.2-SNAPSHOT/ejb/ConversationFacadeImpl!be.isl.desamouryv.sociall.facade.ConversationFacade, java:global/ear-0.2-SNAPSHOT/ejb/ConversationFacadeImpl]
//Infos:   EJB5181:Portable JNDI names for EJB IngredientFacadeImpl: [java:global/ear-0.2-SNAPSHOT/ejb/IngredientFacadeImpl, java:global/ear-0.2-SNAPSHOT/ejb/IngredientFacadeImpl!be.isl.desamouryv.sociall.facade.IngredientFacade]
@Stateless
public class IngredientFacadeImpl extends AbstractFacade<Ingredient> implements IngredientFacade {

    private static final Logger logger = Logger.getLogger(UserFacadeImpl.class.getName());

    @PersistenceContext(unitName = "sociallDevPU")
    protected EntityManager em;

    public IngredientFacadeImpl() {
        super(Ingredient.class);
    }

    @Override
    public List<Ingredient> findAll() {
        return em.createNamedQuery("Ingredient.findAll", Ingredient.class).getResultList();
    }
    
    @Override
    public List<Ingredient> findContaining(String query) {
        return em.createNamedQuery("Ingredient.findContaining", Ingredient.class)
                .setParameter("query", "%" + query.toLowerCase() + "%")
                .getResultList();
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

}
