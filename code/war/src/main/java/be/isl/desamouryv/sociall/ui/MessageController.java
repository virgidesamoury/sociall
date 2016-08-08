/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.ui;

import be.isl.desamouryv.sociall.comparator.ConversationComparator;
import be.isl.desamouryv.sociall.domain.Conversation;
import be.isl.desamouryv.sociall.domain.Message;
import be.isl.desamouryv.sociall.domain.User;
import be.isl.desamouryv.sociall.facade.ConversationFacade;
import be.isl.desamouryv.sociall.facade.MessageFacade;
import be.isl.desamouryv.sociall.service.ConversationService;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;

/**
 *
 * @author Virginie
 */
@Named
@ViewScoped
public class MessageController implements Serializable {

    private static final Logger logger = Logger.getLogger(MessageController.class.getName());

    @EJB
    ConversationFacade conversationFacade;
    @EJB
    MessageFacade messageFacade;

    @EJB
    ConversationService conversationService;

    @Inject
    UserSession userSession;

    @ManagedProperty("#{param.convId}")
    private String c;

    private LinkedList<Conversation> conversations;
    private Conversation detailConversation;
    private String newMessageText;

    /**
     * Creates a new instance of MessagesController
     */
    public MessageController() {
    }

    @PostConstruct
    public void init() {
        conversations = new LinkedList<>(conversationFacade.findByUser(userSession.getUser()));
        Collections.sort(conversations, new ConversationComparator());
        for (Conversation conversation : conversations) {
            Collections.sort(conversation.getMessages());
        }
    }

    public void displayDetailConversation(Conversation conversation) {
        System.out.println("displayDetailConversation");
        System.out.println(conversation);
        detailConversation = conversation;
        for (Iterator<Message> it = detailConversation.getMessages().iterator(); it.hasNext();) {
            Message m = it.next();
            if (m.isRead()) {
                break;
            }
            m.setRead(true);
        }
        detailConversation = conversationFacade.update(detailConversation);
    }

    public User otherUser(Conversation conversation) {
        if (conversation != null) {
            User user = conversation.getThisUser().equals(userSession.getUser())
                    ? conversation.getThatUser()
                    : conversation.getThisUser();
            return user;
        }
        return null;
    }

    public void send() {
        sendMessage(newMessageText);
        Collections.sort(conversations, new ConversationComparator());
        newMessageText = null;
    }

    private void sendMessage(String text) {
        if (text != null) {
            List<Message> messages = conversationService.sendMessage(detailConversation, userSession.getUser(), text);
            for (Conversation conversation : conversations) {
                if (conversation.equals(detailConversation)) {
                    conversation.setMessages(messages);
                    detailConversation = conversation;
                    break;
                }
            }
        }
        PushContext pushContext = PushContextFactory.getDefault().getPushContext();
        pushContext.push("/conversation", detailConversation.getId());
        // Primefaces 5 API for push, does not seem to work yet with Glassfish 4
//        EventBus eventBus = EventBusFactory.getDefault().eventBus();
//        eventBus.publish("/conversation", String.valueOf(detailConversation.getId()));
    }

    public void refreshConversations() {
        logger.log(Level.INFO, "refreshConversations");
        String sConvId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("convId");
        Long convId = Long.valueOf(sConvId);
        Iterator<Conversation> it = conversations.iterator();
        Conversation itConv = null;
        while (it.hasNext()) {
            itConv = it.next();
            if (itConv.getId().equals(convId)) {
                List<Message> messages = messageFacade.findByConversation(itConv);
                itConv.setMessages(messages);
                if (itConv.equals(detailConversation)) {
                    detailConversation = itConv;
                }
                it.remove();
            }
        }
        if(itConv != null){
            conversations.push(itConv);
        }
    }

    public Conversation getDetailConversation() {
        return detailConversation;
    }

    public void setDetailConversation(Conversation detailConversation) {
        this.detailConversation = detailConversation;
    }

    public LinkedList<Conversation> getConversations() {
        return conversations;
    }

    public void setConversations(LinkedList<Conversation> conversations) {
        this.conversations = conversations;
    }

    public String getNewMessageText() {
        return newMessageText;
    }

    public void setNewMessageText(String newMessageText) {
        this.newMessageText = newMessageText;
    }
}
