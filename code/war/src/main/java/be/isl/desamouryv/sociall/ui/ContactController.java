/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.ui;

import be.isl.desamouryv.sociall.domain.Contact;
import be.isl.desamouryv.sociall.domain.User;
import be.isl.desamouryv.sociall.facade.ContactFacade;
import be.isl.desamouryv.sociall.service.ContactService;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Virginie
 */
@Named
@ViewScoped
public class ContactController implements Serializable {

    @Inject
    UserSession userSession;

    @EJB
    ContactService contactService;

    @EJB
    ContactFacade contactFacade;

    private List<Contact> sentPending;
    private List<Contact> receivedPending;
    private List<Contact> contacts;

    /**
     * Creates a new instance of ContactController
     */
    public ContactController() {
    }

    @PostConstruct
    public void init() {
        findSentPending();
        findReceivedPending();
        findContacts();
    }

    private void findSentPending() {
        sentPending = contactFacade.findSentInvitesByUserAndStatus(userSession.getUser(), Contact.Status.PENDING);
    }

    private void findReceivedPending() {
        receivedPending = contactFacade.findReceivedInvitesByUserAndStatus(userSession.getUser(), Contact.Status.PENDING);
    }

    private void findContacts() {
        contacts = contactFacade.findContactsByUserAndStatus(userSession.getUser(), Contact.Status.ACCEPTED);
    }

    public User otherUser(Contact contact) {
        if(contact == null)
            return null;
        return userSession.getUser().equals(contact.getSender()) ? contact.getInvited() : contact.getSender();
    }

    public List<Contact> getSentPending() {
        return sentPending;
    }

    public List<Contact> getReceivedPending() {
        return receivedPending;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

}
