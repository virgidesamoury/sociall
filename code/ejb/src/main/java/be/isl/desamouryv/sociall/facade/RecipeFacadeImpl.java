/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.isl.desamouryv.sociall.facade;

import be.isl.desamouryv.sociall.domain.Recipe;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Virginie
 */
@Stateless
public class RecipeFacadeImpl  extends AbstractFacade<Recipe> implements RecipeFacade {
    private static final Logger logger = Logger.getLogger(UserFacadeImpl.class.getName());

    @PersistenceContext(unitName = "sociallDevPU")
    protected EntityManager em;

    public RecipeFacadeImpl() {
        super(Recipe.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }
    
    
}
