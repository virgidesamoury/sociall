/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.isl.desamouryv.sociall.ui;

import be.isl.desamouryv.sociall.domain.Review;
import be.isl.desamouryv.sociall.domain.Role;
import be.isl.desamouryv.sociall.domain.User;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Virginie
 */
@Named
@SessionScoped
public class UserSession implements Serializable {

    private static final Logger logger = Logger.getLogger(UserSession.class.getName());
    
    private User user;
    
    private boolean admin;
    
    /**
     * Creates a new instance of User
     */
    public UserSession() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public boolean isLoggedIn(){
        return user != null;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isAllowed(List<Role> rolesRestriction){
        if(rolesRestriction == null || rolesRestriction.isEmpty()){
            return true;
        }
        for (Role role : rolesRestriction) {
            if(user.getRoles().contains(role))
                return true;
        }
        return false;
    }
    
    void addReview(Review review) {
        if(this.user.getReviews() == null){
            this.user.setReviews(new ArrayList<Review>());
        }
        this.user.getReviews().add(review);
    }
}
