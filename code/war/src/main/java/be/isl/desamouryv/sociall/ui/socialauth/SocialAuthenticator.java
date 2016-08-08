/*
 ===========================================================================
 Copyright (c) 2010 BrickRed Technologies Limited

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sub-license, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.
 ===========================================================================

 */
package be.isl.desamouryv.sociall.ui.socialauth;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.AuthProviderFactory;
import org.brickred.socialauth.Contact;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.SocialAuthConfig;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.util.SocialAuthUtil;

@Named
@SessionScoped
public class SocialAuthenticator implements Serializable {

    private static final Logger logger = Logger.getLogger(SocialAuthenticator.class.getName());

    private Profile profile;
    private AuthProvider provider;
    private String status;
    private String viewUrl;

    private SocialAuthManager manager;
    private SocialAuthConfig config;

    @PostConstruct
    public void init() {
        System.out.println("SocialAuthenticator.init");
        provider = null;
        viewUrl = "/index.xhtml";
        config = new SocialAuthConfig();
        try {
            config.load();
            manager = new SocialAuthManager();
            manager.setSocialAuthConfig(config);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public SocialAuthenticator() {
    }
    
    public void login(String id, Authentication authentication) throws Exception {
        logger.log(Level.INFO, "login: {0}", id);
        provider = AuthProviderFactory.getInstance(id);
        String returnToUrl = returnToUrl();
        String url = manager.getAuthenticationUrl(id, returnToUrl);
//        String url = manager.getAuthenticationUrl(id, returnToUrl, Permission.AUTHENTICATE_ONLY);
        if (url != null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        }

    }

    private String returnToUrl() throws MalformedURLException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context
                .getExternalContext().getRequest();

        URL returnToUrl;
        if (request.getServerPort() == 8080) {
            returnToUrl = new URL("https", request.getServerName(), context
                    .getApplication().getViewHandler().getActionURL(context,
                            viewUrl));
        } else {
            returnToUrl = new URL("https", request.getServerName(), request
                    .getServerPort(), context.getApplication().getViewHandler()
                    .getActionURL(context, viewUrl));

        }
        return returnToUrl.toExternalForm();
    }

    /**
     * Verifies the user when the external provider redirects back to our
     * application
     *
     * @throws Exception
     */
    public void connect() throws Exception {
        ExternalContext context = javax.faces.context.FacesContext
                .getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        provider = manager.connect(SocialAuthUtil.getRequestParametersMap(request));
        profile = provider.getUserProfile();
    }

    /**
     * Reinitializes the bean
     */
    public void logout() {
        init();
    }

    /**
     * Returns the Profile information for the user. Should be called only after
     * loginImmediately()
     *
     * @return Profile of the user
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Status of the user to be updated on a provider like Facebook or Twitter.
     * Remember this will not give us the current status of the user
     *
     * @return status message to be updated
     */
    public String getStatus() {
        return status;
    }

    /**
     * Status of the user to be updated on a provider like Facebook or Twitter.
     * To actually update the status, call updateStatus action.
     *
     * @param status
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * Updates the status on the given provider. Exception will be thrown if the
     * provider does not provide this facility
     */
    public void updateStatus() throws Exception {
        provider.updateStatus(status);
    }

    /**
     * Gets the list of contacts available from the provider. This may be used
     * to import contacts of any user in your web application from your chosen
     * provider like Gmail, Yahoo or Hotmail
     *
     * @return list of contacts
     */
    public List<Contact> getContactList() throws Exception {
        return provider.getContactList();
    }

    /**
     * Retrieves the user profile from the provider.
     *
     * @return Profile object containing the profile information.
     * @throws Exception
     */
    public Profile getUserProfile() throws Exception {
        return provider.getUserProfile();
    }


}
