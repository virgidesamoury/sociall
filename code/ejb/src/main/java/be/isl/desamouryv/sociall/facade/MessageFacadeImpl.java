/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.facade;

import be.isl.desamouryv.sociall.domain.Conversation;
import be.isl.desamouryv.sociall.domain.Message;
import be.isl.desamouryv.sociall.domain.User;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Virginie
 */
@Stateless
public class MessageFacadeImpl extends AbstractFacade<Message> implements MessageFacade {

    @PersistenceContext(unitName = "sociallDevPU")
    protected EntityManager em;

    public MessageFacadeImpl() {
        super(Message.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    @Override
    public List<Message> findMessagesByUser(User user) {
        List<Message> messages = em.createNamedQuery("Message.findByUser", Message.class)
                .setParameter("user", user)
                .getResultList();
        Collections.sort(messages);
        return messages;
    }
    
    @Override
    public List<Message> findByConversation(Conversation conversation) {
        List<Message> messages = em.createNamedQuery("Message.findByConversation", Message.class)
                .setParameter("conversation", conversation)
                .getResultList();
        return messages;
    }

}
