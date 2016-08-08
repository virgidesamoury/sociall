/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.isl.desamouryv.sociall.facade;

import be.isl.desamouryv.sociall.domain.Contact;
import be.isl.desamouryv.sociall.domain.User;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Virginie
 */
@Local
public interface ContactFacade extends BeanFacade<Contact>{

    public Contact findContactByUsers(User thisUser, User thatUser);

    public List<Contact> findSentInvitesByUserAndStatus(User user, Contact.Status status);

    public List<Contact> findReceivedInvitesByUserAndStatus(User user, Contact.Status status);

    public List<Contact> findContactsByUserAndStatus(User user, Contact.Status status);
    
}
