/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.service;

import be.isl.desamouryv.sociall.domain.Password;
import be.isl.desamouryv.sociall.mail.TokenGenerator;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Virginie
 */
@Stateless
public class PasswordGeneratorImpl implements PasswordGenerator {

    @EJB
    TokenGenerator tokenGenerator;

    @Override
    public Password createPassword(String pwd) throws Exception {
        if(pwd == null){
            return null;
        }
        String salt = tokenGenerator.generateSalt();
            // TODO
        //String sha256hex = DigestUtils.sha256Hex(pwd + salt);
        String sha256hex = DigestUtils.sha256Hex(pwd.getBytes("UTF-8"));
        Password password = new Password();
        password.setHashed(sha256hex);
        password.setSalt(salt);
        return password;

    }
}
