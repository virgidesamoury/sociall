/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.isl.desamouryv.sociall.comparator;

import be.isl.desamouryv.sociall.domain.Conversation;
import be.isl.desamouryv.sociall.domain.Message;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Virginie
 */
public class ConversationComparator implements Comparator<Conversation>{
    
    /**
     * Sorts a List<Conversation> with unread first, then by most recent last message
     * sent date. 
     * @param t
     * @param o
     * @return 
     */
    @Override
    public int compare(Conversation t, Conversation o) {
        
        boolean tUnread = containsUnread(t.getMessages());
        boolean oUnread = containsUnread(o.getMessages());
        
        if(tUnread == oUnread){
            Date tMostRecentDate = mostRecentDate(t.getMessages());
            Date oMostRecentDate = mostRecentDate(o.getMessages());
            return 0 - tMostRecentDate.compareTo(oMostRecentDate);
        }
        
        return tUnread ? -1 : 1;
    }
    
    private boolean containsUnread(List<Message> messages){
        for (Message message : messages) {
            if(!message.isRead())
                return true;
        }
        return false;
    }
    
    private Date mostRecentDate(List<Message> messages){
        Date date = new Date();
        for (Message message : messages) {
            if(message.getSentOn().before(date)){
                date = message.getSentOn();
            }
        }
        return date;
    }

}
