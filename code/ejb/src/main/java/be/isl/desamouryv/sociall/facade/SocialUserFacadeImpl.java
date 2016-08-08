/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.facade;

import be.isl.desamouryv.sociall.domain.SocialUser;
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
public class SocialUserFacadeImpl extends AbstractFacade<SocialUser> implements SocialUserFacade {

    private static final Logger logger = Logger.getLogger(SocialUserFacadeImpl.class.getName());

    @PersistenceContext(unitName = "sociallDevPU")
    protected EntityManager em;

    public SocialUserFacadeImpl() {
        super(SocialUser.class);
    }

    @Override
    public SocialUser findByValidatedId(String validatedId) {
        List<SocialUser> resultList = em.createNamedQuery("SocialUser.findByValidatedId", SocialUser.class)
                .setParameter("validatedId", validatedId)
                .getResultList();
        return resultList == null || resultList.isEmpty() ? null : resultList.get(0);
    }

    protected EntityManager getEntityManager() {
        return this.em;
    }

}
