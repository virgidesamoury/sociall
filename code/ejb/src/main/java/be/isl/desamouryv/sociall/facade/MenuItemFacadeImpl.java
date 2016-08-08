/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.facade;

import be.isl.desamouryv.sociall.domain.app.MenuItem;
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
public class MenuItemFacadeImpl extends AbstractFacade<MenuItem> implements MenuItemFacade {

    private static final Logger logger = Logger.getLogger(MenuItemFacadeImpl.class.getName());

    @PersistenceContext(unitName = "sociallDevPU")
    protected EntityManager em;

    public MenuItemFacadeImpl() {
        super(MenuItem.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    @Override
    public List<MenuItem> findAll() {
        return em.createNamedQuery("MenuItem.findAll", MenuItem.class)
                .getResultList();
    }
    
   
}
