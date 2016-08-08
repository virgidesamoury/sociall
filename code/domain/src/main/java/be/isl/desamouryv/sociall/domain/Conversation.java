/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.domain;

import java.util.List;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Virginie
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "Conversation.findByUser", query = "SELECT c FROM Conversation c WHERE c.thisUser = :user OR c.thatUser = :user")
})
public class Conversation extends Bean {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CON_SEQ")
    @SequenceGenerator(name = "CON_SEQ",
            sequenceName = "conversation_id_seq",
            initialValue = 100)
    protected Long id;

    @OneToOne
    private User thisUser;
    
    @OneToOne
    private User thatUser;
    
    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Message> messages;
    
    @Override
    public Long getId() {
        return id;
    }

    public User getThisUser() {
        return thisUser;
    }

    public void setThisUser(User thisUser) {
        this.thisUser = thisUser;
    }

    public User getThatUser() {
        return thatUser;
    }

    public void setThatUser(User thatUser) {
        this.thatUser = thatUser;
    }
    
    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
    
    
    
    
    
    
}
