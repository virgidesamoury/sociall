/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.ui;

import be.isl.desamouryv.sociall.domain.User;
import be.isl.desamouryv.sociall.service.AccountService;
import be.isl.desamouryv.sociall.dto.ImageDto;
import be.isl.desamouryv.sociall.facade.UserFacade;
import be.isl.desamouryv.sociall.ui.util.JsfUtils;
import javax.inject.Named;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Virginie
 */
@Named
@ViewScoped
public class UserController implements Serializable {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    @EJB
    UserFacade userFacade;

    @Inject
    UserSession userSession;

    @EJB
    AccountService accountService;

    private String oldPassword;
    private String newPassword;

    /**
     * Creates a new instance of UserController
     */
    public UserController() {
    }

    public void edit() {
        try {
            User user = userFacade.update(userSession.getUser());
            userSession.setUser(user);
        } catch (Exception e) {
            JsfUtils.error("common.error", null);
            Logger.getLogger(UserController.class.getName()).log(Level.WARNING, "{0}", e);
        }
    }

    public void changePassword() {
        userSession.getUser().getPassword().setHashed(newPassword);
        try {
            accountService.changePassword(userSession.getUser());
            JsfUtils.info("profile.password.changed", null);
        } catch (Exception e) {
            JsfUtils.error("common.error", e.toString());
        }
    }

    //http://stackoverflow.com/questions/14211843/how-to-save-uploaded-file/14214223#14214223
    //http://stackoverflow.com/questions/8588687/pfileupload-does-not-set-uploaded-file-in-backing-bean
    public void uploadProfilePic(FileUploadEvent event) {
        try {
            ImageDto imageDto = new ImageDto();
            imageDto.setFileName(event.getFile().getFileName());
            imageDto.setInputStream(event.getFile().getInputstream());
            accountService.changeProfilePic(userSession.getUser(), imageDto);
            JsfUtils.info("profile.picture.success", null);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            JsfUtils.error("common.error", ex.toString());
        }
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}
