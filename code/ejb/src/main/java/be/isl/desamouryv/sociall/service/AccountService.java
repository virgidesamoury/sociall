/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.isl.desamouryv.sociall.service;

import be.isl.desamouryv.sociall.domain.SocialUser;
import be.isl.desamouryv.sociall.dto.ImageDto;
import be.isl.desamouryv.sociall.domain.User;
import javax.ejb.Local;

/**
 *
 * @author Virginie
 */
@Local
public interface AccountService {

    public void signinUser(User user) throws Exception;

    public User confirmUser(String token);

    public void changePassword(User user) throws Exception;

    public void changeProfilePic(User user, ImageDto newImage) throws Exception;

    public SocialUser signinSocial(SocialUser socialUser)  throws Exception;
    
}
