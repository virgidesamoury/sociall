/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.isl.desamouryv.sociall.domain;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Virginie
 */
@Entity
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "Contact.findContactByUsers", query = "SELECT c FROM Contact c WHERE c.sender = :thisUser OR c.sender = :thatUser OR c.invited = :thisUser OR c.invited = :thatUser"),
    @NamedQuery(name = "Contact.findContactsByUserAndStatus", query = "SELECT c FROM Contact c WHERE c.sender = :user OR c.invited = :user AND c.status = :status"),
    @NamedQuery(name = "Contact.findSentInvitesByUserAndStatus", query = "SELECT c FROM Contact c WHERE c.sender = :user and c.status = :status"),
    @NamedQuery(name = "Contact.findReceivedInvitesByUserAndStatus", query = "SELECT c FROM Contact c WHERE c.invited = :user and c.status = :status")
})
public class Contact extends Bean{
    
    public enum Status{PENDING, ACCEPTED, REFUSED}

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COT_SEQ")
    @SequenceGenerator(name = "COT_SEQ",
            sequenceName = "contact_id_seq",
            initialValue = 100)
    protected Long id;
    
    @ManyToOne
    private User sender;
    
    @ManyToOne
    private User invited;
    
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @Override
    public Long getId() {
        return this.id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getInvited() {
        return invited;
    }

    public void setInvited(User invited) {
        this.invited = invited;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Contact{" + "id=" + id + ", sender=" + sender + ", invited=" + invited + ", status=" + status + '}';
    }
    
    
    
}
