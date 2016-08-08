/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.isl.desamouryv.sociall.service;

import be.isl.desamouryv.sociall.domain.Contact;
import be.isl.desamouryv.sociall.domain.User;
import javax.ejb.Local;

/**
 *
 * @author Virginie
 */
@Local
public interface ContactService {

    public Contact addContact(User sender, User receiver);

    public Contact findContactByUsers(User thisUser, User thatUser);
    
}
