/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.isl.desamouryv.sociall.facade;

import be.isl.desamouryv.sociall.domain.Conversation;
import be.isl.desamouryv.sociall.domain.User;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Virginie
 */
@Local
public interface ConversationFacade extends BeanFacade<Conversation>{

    public List<Conversation> findByUser(User user);
    
}
