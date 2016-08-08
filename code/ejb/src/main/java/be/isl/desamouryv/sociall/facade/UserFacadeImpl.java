/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.facade;

import be.isl.desamouryv.sociall.domain.User;
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
public class UserFacadeImpl extends AbstractFacade<User> implements UserFacade {

    private static final Logger logger = Logger.getLogger(UserFacadeImpl.class.getName());

    @PersistenceContext(unitName = "sociallDevPU")
    protected EntityManager em;

    public UserFacadeImpl() {
        super(User.class);
    }

    @Override
    public User findByEmail(String email) {
        List<User> resultList = em.createNamedQuery("User.findByEmail", User.class)
                .setParameter("email", email)
                .getResultList();
        return resultList == null || resultList.isEmpty() ? null : resultList.get(0);
    }
    
    protected EntityManager getEntityManager(){
        return this.em;
    }
    void setEntityManager(EntityManager em) {
        this.em = em;
    }

}
