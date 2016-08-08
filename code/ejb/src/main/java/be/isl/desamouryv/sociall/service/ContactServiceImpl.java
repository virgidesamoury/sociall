/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.isl.desamouryv.sociall.service;

import be.isl.desamouryv.sociall.domain.Contact;
import be.isl.desamouryv.sociall.domain.User;
import be.isl.desamouryv.sociall.facade.ContactFacade;
import be.isl.desamouryv.sociall.facade.UserFacade;
import be.isl.desamouryv.sociall.mail.MailSender;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Virginie
 */
@Stateless
public class ContactServiceImpl implements ContactService {

    private static final Logger logger = Logger.getLogger(ContactServiceImpl.class.getName());

    @EJB
    MailSender mailSender;

    @EJB
    UserFacade userFacade;
    
    @EJB
    ContactFacade contactFacade;
    
    @Override
    public Contact addContact(User sender, User receiver){
        System.out.println("ContactService.addContact begin");
        Contact contact = new Contact();
        contact.setSender(sender);
        contact.setInvited(receiver);
        contact.setStatus(Contact.Status.PENDING);
        contactFacade.save(contact);
        System.out.println("ContactService.addContact end");
//        try {
//            mailSender.sendInviteInfo(receiver.getEmail(), receiver.getLanguage());
//        } catch (Exception ex) {
//            Logger.getLogger(ContactServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return contact;                
    }
    
    public Contact findContactByUsers(User thisUser, User thatUser){
        return contactFacade.findContactByUsers(thisUser, thatUser);
    }
}
