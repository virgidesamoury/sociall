/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.service;

import be.isl.desamouryv.sociall.Constants;
import be.isl.desamouryv.sociall.dto.ImageDto;
import be.isl.desamouryv.sociall.domain.AccountActivation;
import be.isl.desamouryv.sociall.domain.Image;
import be.isl.desamouryv.sociall.domain.Password;
import be.isl.desamouryv.sociall.domain.Role;
import be.isl.desamouryv.sociall.domain.SocialUser;
import be.isl.desamouryv.sociall.domain.User;
import be.isl.desamouryv.sociall.facade.AccountActivationFacade;
import be.isl.desamouryv.sociall.facade.ImageFacade;
import be.isl.desamouryv.sociall.facade.RoleFacade;
import be.isl.desamouryv.sociall.facade.SocialUserFacade;
import be.isl.desamouryv.sociall.facade.UserFacade;
import be.isl.desamouryv.sociall.mail.MailSender;
import be.isl.desamouryv.sociall.mail.TokenGenerator;
import be.isl.desamouryv.sociall.settings.SettingsFacade;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Virginie
 */
@Stateless
public class AccountServiceImpl implements AccountService {

    private static final Logger logger = Logger.getLogger(AccountServiceImpl.class.getName());

    @EJB
    MailSender mailSender;

    @EJB
    TokenGenerator tokenGenerator;

    @EJB
    UserFacade userFacade;

    @EJB
    SocialUserFacade socialUserFacade;

    @EJB
    AccountActivationFacade accountActivationFacade;

    @EJB
    PasswordGenerator passwordGenerator;

    @EJB
    ImageFacade imageFacade;

    @EJB
    FileService fileService;

    @EJB
    SettingsFacade settingsFacade;

    @EJB
    RoleFacade roleFacade;

    //http://stackoverflow.com/questions/3038930/user-sign-up-with-email-verification
    @Override
    public void signinUser(User user) throws Exception {
        String token = tokenGenerator.generateToken(user.getEmail());
        AccountActivation accountActivation = new AccountActivation();
        accountActivation.setToken(token);

        user.setActive(false);

        addMemberRole(user);

        user.setPassword(passwordGenerator.createPassword(user.getPassword().getHashed()));

        accountActivation.setUser(user);
        accountActivationFacade.save(accountActivation);
        mailSender.sendConfirmation(user.getEmail(), user.getLanguage(), token);
    }

    public SocialUser signinSocial(SocialUser socialUser) throws Exception{
        String token = tokenGenerator.generateToken(socialUser.getValidatedId());
        Password password = passwordGenerator.createPassword(token);
        socialUser.setPassword(password);
        socialUserFacade.save(socialUser);
        signinUser(socialUser.getUser());
        return socialUser;
    }

    private void addMemberRole(User user) {
        if (user.getRoles() == null) {
            Set<Role> roles = new HashSet<>();
            user.setRoles(roles);
        }
        user.getRoles().add(roleFacade.findByName(Constants.ROLE_MEMBER));
    }

    @Override
    public User confirmUser(String token) {
        logger.info("confirmUser");
        AccountActivation accountActivation = accountActivationFacade.findByToken(token);
        User user = accountActivation.getUser();
        user.setActive(true);
        userFacade.update(user);
        accountActivationFacade.delete(accountActivation);
        return user;
    }

    @Override
    public void changePassword(User user) throws Exception {
        Password password = passwordGenerator.createPassword(user.getPassword().getHashed());
        user.setPassword(password);
        userFacade.update(user);
        mailSender.sendPasswordChangedInfo(user.getEmail(), user.getLanguage());
    }

    @Override
    public void changeProfilePic(User u, ImageDto newImage) throws Exception {
        User user = userFacade.find(u);
        String oldImage = null;
        if (user.getProfilePic() != null) {
            oldImage = user.getProfilePic().getFileName();
        }
        String fileName = fileService.storeFile(newImage.getInputStream(), newImage.getFileName(), settingsFacade.getUploadPath());

        Image image = new Image();
        image.setFileName(fileName);

        user.setProfilePic(image);

        userFacade.update(user);

        if (oldImage != null) {
            fileService.deleteFile(oldImage, settingsFacade.getUploadPath());
        }
        // TODO rollback file save+delete
    }
}
