/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.ui;

import be.isl.desamouryv.sociall.domain.Role;
import be.isl.desamouryv.sociall.domain.SocialProvider;
import be.isl.desamouryv.sociall.domain.SocialUser;
import be.isl.desamouryv.sociall.domain.User;
import be.isl.desamouryv.sociall.facade.SocialUserFacade;
import be.isl.desamouryv.sociall.facade.UserFacade;
import be.isl.desamouryv.sociall.service.AccountService;
import be.isl.desamouryv.sociall.ui.socialauth.Authentication;
import be.isl.desamouryv.sociall.ui.socialauth.SocialAuthenticator;
import be.isl.desamouryv.sociall.ui.util.JsfUtils;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.brickred.socialauth.Profile;

/**
 *
 * @author Virginie
 */
@Named
@ViewScoped
public class AuthController implements Serializable {

    private static final Logger logger = Logger.getLogger(AuthController.class.getName());

    @Inject
    UserSession userSession;

    @Inject
    SocialAuthenticator socialAuthenticator;

    @EJB
    UserFacade userFacade;

    @EJB
    SocialUserFacade socialUserFacade;

    @EJB
    AccountService accountService;

    private String userName;
    private String password;
    private String originalURL;
    private User user;

    private Authentication authentication;

    /**
     * Creates a new instance of AuthController
     */
    public AuthController() {
    }

    /**
     * Retient l'url demandée initialement pour pouvoir rediriger l'utilisateur
     * une fois l'authentification effectuée.
     */
    @PostConstruct
    public void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        originalURL = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI);

        if (originalURL == null) {
            originalURL = externalContext.getRequestContextPath() + "/index.xhtml";
        } else {
            String originalQuery = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_QUERY_STRING);

            if (originalQuery != null) {
                originalURL += "?" + originalQuery;
            }
        }
    }

    public void login(String id) throws Exception {
        socialAuthenticator.login(id, Authentication.LOGIN);
    }

    public void signin(String id) throws Exception {
        socialAuthenticator.login(id, Authentication.SIGNIN);
    }

    public void login() throws IOException {
        System.out.println("login");
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        try {
            logger.log(Level.INFO, "Authenticating {0}", userName);
            request.login(userName, password);
            logger.log(Level.INFO, "Authentication ok!!!!");
            if (user == null) {
                user = userFacade.findByEmail(userName);
            }
            logger.log(Level.INFO, "User found: {0} ", user);
            userSession.setUser(user);
            
            for (Role role : user.getRoles()) {
                if (role.getName().equals("ADMIN")) {
                    logger.log(Level.INFO, "User is admin");
                    userSession.setAdmin(true);
                    break;
                }
            }
            
            if (authentication == Authentication.SIGNIN) {
                logger.log(Level.INFO, "Redirect to profile");
                externalContext.redirect("/pages/member/profile");
            } else {
                logger.log(Level.INFO, "Redirect to:  {0}", originalURL);
                externalContext.redirect(originalURL);
            }
        } catch (ServletException e) {
            logger.log(Level.WARNING, "Invalid credentials");
            JsfUtils.error("common.error.login", null);
        }
    }

    public String logout() {
        JsfUtils.infoFlash("login.logout");
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/pages/signin/login.xhtml?faces-redirect=true";
    }

    public void verify() {
        System.out.println("verify");
        System.out.println("FacesContext.getCurrentInstance().isPostback() " + FacesContext.getCurrentInstance().isPostback());
        System.out.println("userSession " + userSession.getUser());
        if (!FacesContext.getCurrentInstance().isPostback() 
                && userSession.getUser() == null) {
            try {
                socialAuthenticator.connect();
                doAuthenticate(socialAuthenticator.getProfile());
            } catch (Exception e) {
                logger.log(Level.WARNING, "Authentication verification exception: {0}", e.toString());
            }
        }
    }

    private void doAuthenticate(Profile profile) throws Exception {
        System.out.println("doAuthenticate");
        SocialUser socialUser = socialUserFacade.findByValidatedId(profile.getValidatedId());
        if (socialUser == null) {
            socialUser = createSocialUser(profile, socialUser);
            authentication = Authentication.SIGNIN;
        }
        user = socialUser.getUser();
        this.setEmail(socialUser.getValidatedId());
        this.setPassword(socialUser.getAuthKey());
        try {
            this.login();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private SocialUser createSocialUser(Profile profile, SocialUser socialUser) throws Exception {
        System.out.println("createSocialUser");
        User user = new User();
        user.setEmail(profile.getEmail());
        user.setFirstName(profile.getFirstName());
        user.setLastName(profile.getLastName());
        socialUser = new SocialUser();
        socialUser.setUser(user);
        socialUser.setSocialProvider(SocialProvider.findByName(profile.getProviderId()));
        socialUser.setValidatedId(profile.getValidatedId());
        accountService.signinSocial(socialUser);
        return socialUser;
    }

    public String getEmail() {
        return userName;
    }

    public void setEmail(String email) {
        this.userName = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
