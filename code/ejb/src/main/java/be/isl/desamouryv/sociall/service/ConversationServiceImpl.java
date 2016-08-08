/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.service;

import be.isl.desamouryv.sociall.domain.Conversation;
import be.isl.desamouryv.sociall.domain.Message;
import be.isl.desamouryv.sociall.domain.User;
import be.isl.desamouryv.sociall.facade.AbstractFacade;
import be.isl.desamouryv.sociall.facade.ConversationFacade;
import be.isl.desamouryv.sociall.facade.MessageFacade;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Virginie
 */
@Stateless
public class ConversationServiceImpl extends AbstractFacade<Conversation> implements ConversationService {

    private static final Logger logger = Logger.getLogger(ConversationServiceImpl.class.getName());
    
    @EJB
    ConversationFacade conversationFacade;
    
    @EJB
    MessageFacade messageFacade;

    @PersistenceContext(unitName = "sociallDevPU")
    protected EntityManager em;

    public ConversationServiceImpl() {
        super(Conversation.class);
    }
    
    public List<Message> sendMessage(Conversation conversation, User sender, String text){
        Message message = new Message();
        message.setSender(sender);
        message.setText(text);
        message.setSentOn(new Date());
        message.setRead(false);
        message.setConversation(conversation);
        messageFacade.save(message);
        
        List<Message> messages = messageFacade.findByConversation(conversation);
        
        return messages;
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }
}
