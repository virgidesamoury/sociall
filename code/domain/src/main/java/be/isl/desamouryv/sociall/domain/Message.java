/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Virginie
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@NamedQueries({
    @NamedQuery(name = "Message.findByUser", query = "SELECT m FROM Message m WHERE m.conversation.thisUser = :user OR m.conversation.thatUser = :user ORDER BY m.sentOn DESC"),
    @NamedQuery(name = "Message.findByConversation", query = "SELECT m FROM Message m WHERE m.conversation = :conversation ORDER BY m.sentOn DESC")
})
public class Message extends Bean implements Comparable<Message> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MES_SEQ")
    @SequenceGenerator(name = "MES_SEQ",
            sequenceName = "message_id_seq",
            initialValue = 100)
    protected Long id;

    @Column(length = 2550)
    private String text;
    
    @XmlTransient
    @ManyToOne
    private Conversation conversation;

    @Column(name = "IS_READ")
    private boolean read;

    @Temporal(TemporalType.TIMESTAMP)
    private Date sentOn;
    
    @XmlTransient
    @ManyToOne
    private User sender;

    public Long getId() {
        return id;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getSentOn() {
        return sentOn;
    }

    public void setSentOn(Date sentOn) {
        this.sentOn = sentOn;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    @Override
    public int compareTo(Message o) {
        return 0 - this.sentOn.compareTo(o.sentOn);
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

}
