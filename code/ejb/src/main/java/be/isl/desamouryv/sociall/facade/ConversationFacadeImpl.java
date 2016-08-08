/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.facade;

import be.isl.desamouryv.sociall.domain.Conversation;
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
public class ConversationFacadeImpl extends AbstractFacade<Conversation> implements ConversationFacade {

    private static final Logger logger = Logger.getLogger(ConversationFacadeImpl.class.getName());

    @PersistenceContext(unitName = "sociallDevPU")
    protected EntityManager em;

    public ConversationFacadeImpl() {
        super(Conversation.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    @Override
    public List<Conversation> findByUser(User user) {
        return em.createNamedQuery("Conversation.findByUser", Conversation.class)
                .setParameter("user", user)
                .getResultList();
    }

}
