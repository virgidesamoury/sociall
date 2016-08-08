/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Virginie
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "SocialUser.findByValidatedId", query = "SELECT su FROM SocialUser su WHERE su.validatedId LIKE :validatedId")
})
public class SocialUser extends Bean {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SOC_SEQ")
    @SequenceGenerator(name = "SOC_SEQ",
            sequenceName = "social_user_id_seq",
            initialValue = 100)
    protected Long id;

    @ManyToOne
    User user;
    
    @Enumerated(EnumType.STRING)
    private SocialProvider socialProvider;
    
    private String authKey;
    
    private String validatedId;
    
    @OneToOne(cascade = CascadeType.PERSIST)
    private Password password;
    
    
    @Override
    public Long getId() {
        return this.id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SocialProvider getSocialProvider() {
        return socialProvider;
    }

    public void setSocialProvider(SocialProvider socialProvider) {
        this.socialProvider = socialProvider;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public String getValidatedId() {
        return validatedId;
    }

    public void setValidatedId(String validatedId) {
        this.validatedId = validatedId;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

}
