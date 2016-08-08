/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.facade;

import be.isl.desamouryv.sociall.domain.Role;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Virginie
 */
@Stateless
public class RoleFacadeImpl extends AbstractFacade<Role> implements RoleFacade {

    private static final Logger logger = Logger.getLogger(UserFacadeImpl.class.getName());

    @PersistenceContext(unitName = "sociallDevPU")
    protected EntityManager em;

    public RoleFacadeImpl() {
        super(Role.class);
    }
    
    @Override
    public Role findByName(String name){
        return this.em.createNamedQuery("Role.findByName", Role.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }
    
    
}
