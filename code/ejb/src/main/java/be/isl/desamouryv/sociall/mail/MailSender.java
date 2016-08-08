/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.isl.desamouryv.sociall.mail;

import be.isl.desamouryv.sociall.domain.Language;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.ejb.Local;
import javax.mail.MessagingException;

/**
 *
 * @author Virginie
 */
@Local
public interface MailSender {

    public void send(String to, String subject, String text) throws MessagingException, UnsupportedEncodingException;
    
    public void send(List<String> tos, List<String> ccs, List<String> bccs, String subject, String text) throws MessagingException, UnsupportedEncodingException;
    
    public void sendConfirmation(String email, Language language, String token) throws Exception;
    
    public void sendPasswordChangedInfo(String email, Language language) throws Exception;
    
    public void sendAccountDeletedInfo(List<String> emails) throws Exception;

    public void sendInviteInfo(String email, Language language) throws Exception;

}
