/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.facade;

import be.isl.desamouryv.sociall.domain.Contact;
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
public class ContactFacadeImpl extends AbstractFacade<Contact> implements ContactFacade {

    private static final Logger logger = Logger.getLogger(ContactFacadeImpl.class.getName());

    @PersistenceContext(unitName = "sociallDevPU")
    protected EntityManager em;

    public ContactFacadeImpl() {
        super(Contact.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    @Override
    public Contact findContactByUsers(User thisUser, User thatUser) {
        List<Contact> resultList = em.createNamedQuery("Contact.findContactByUsers", Contact.class)
                .setParameter("thisUser", thisUser)
                .setParameter("thatUser", thatUser)
                .setMaxResults(1)
                .getResultList();
        if (resultList != null && !resultList.isEmpty()) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public List<Contact> findSentInvitesByUserAndStatus(User user, Contact.Status status) {
        List<Contact> resultList = em.createNamedQuery("Contact.findSentInvitesByUserAndStatus", Contact.class)
                .setParameter("user", user)
                .setParameter("status", status)
                .getResultList();
        return resultList;
    }

    @Override
    public List<Contact> findReceivedInvitesByUserAndStatus(User user, Contact.Status status) {
        List<Contact> resultList = em.createNamedQuery("Contact.findReceivedInvitesByUserAndStatus", Contact.class)
                .setParameter("user", user)
                .setParameter("status", status)
                .getResultList();
        return resultList;
    }
    
    @Override
    public List<Contact> findContactsByUserAndStatus(User user, Contact.Status status) {
        List<Contact> resultList = em.createNamedQuery("Contact.findContactsByUserAndStatus", Contact.class)
                .setParameter("user", user)
                .setParameter("status", status)
                .getResultList();
        return resultList;
    }
}
