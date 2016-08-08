/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Virginie
 */
@XmlRootElement
@Entity
@NamedQueries({
    @NamedQuery(name = "AccountActivation.findByToken", query = "SELECT a FROM AccountActivation a WHERE a.token LIKE :token"),
    @NamedQuery(name = "AccountActivation.findInactiveAccounts", query = "SELECT a FROM AccountActivation a WHERE a.user.createdOn < :date")
})
public class AccountActivation extends Bean {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACC_SEQ")
    @SequenceGenerator(name = "ACC_SEQ", 
            sequenceName = "account_activation_id_seq", 
            initialValue = 100)
    private Long id;

    private String token;

    @OneToOne(cascade = CascadeType.PERSIST)
    private User user;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
