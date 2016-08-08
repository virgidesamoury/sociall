/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.ui;

import be.isl.desamouryv.sociall.domain.Password;
import be.isl.desamouryv.sociall.domain.User;
import be.isl.desamouryv.sociall.service.AccountService;
import be.isl.desamouryv.sociall.ui.util.JsfUtils;
import be.isl.desamouryv.sociall.ui.util.Utils;
import javax.inject.Named;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Virginie
 */
@Named
@ViewScoped
public class SignupController implements Serializable {

    private static final Logger logger = Logger.getLogger(SignupController.class.getName());

    @Inject
    UserSession userSession;

    @EJB
    AccountService accountService;

    private String token;

    private User user;

    private boolean signed = false;

    private String rptPassword;

    /**
     * Creates a new instance of SignupController
     */
    public SignupController() {
        user = new User();
        user.setPassword(new Password());
    }

    public String signup() {
        logger.log(Level.INFO, "signup");
        try {
            user.setLanguage(Utils.getCurrentLanguage());
            accountService.signinUser(user);
            signed = true;
            JsfUtils.info("signup.success", null);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.toString(), ex);
            JsfUtils.error("common.error", null);
        }
        return null;
    }

    public void checkToken() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            logger.info("checkToken");
            if (token != null) {
                User confirmedUser = accountService.confirmUser(token);
                if (confirmedUser != null) {
                    signed = true;
                    JsfUtils.info("signup.confirm.success", null);
                } else {
                    signed = false;
                    JsfUtils.error("common.error", null);
                }
            }
        }
    }

    public void resetPassword() {
        logger.info("resetPassword");
    }

    // ------------------------------------------------------------------------
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

    public boolean isSigned() {
        return signed;
    }

    public String getRptPassword() {
        return rptPassword;
    }

    public void setRptPassword(String rptPassword) {
        this.rptPassword = rptPassword;
    }
}
