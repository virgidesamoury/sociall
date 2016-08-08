/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.facade;

import be.isl.desamouryv.sociall.domain.AccountActivation;
import java.util.Date;
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
public class AccountActivationFacadeImpl extends AbstractFacade<AccountActivation> implements AccountActivationFacade {

    private static final Logger logger = Logger.getLogger(AccountActivationFacadeImpl.class.getName());

    @PersistenceContext(unitName = "sociallDevPU")
    protected EntityManager em;

    public AccountActivationFacadeImpl() {
        super(AccountActivation.class);
    }

    @Override
    public AccountActivation findByToken(String token) {
        logger.info("findByToken");
        logger.info(token);
        List<AccountActivation> resultList
                = em.createNamedQuery("AccountActivation.findByToken", AccountActivation.class)
                .setParameter("token", token)
                .getResultList();
        if (!resultList.isEmpty()) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public List<AccountActivation> findAccountsCreatedBefore(Date date) {
        return em.createNamedQuery("AccountActivation.findInactiveAccounts", AccountActivation.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    void setEm(EntityManager em) {
        this.em = em;
    }

}
