/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.mail;

import be.isl.desamouryv.sociall.domain.Language;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Virginie
 */
@Stateless
public class MailSenderImpl implements MailSender {

    @Resource(name = "mail/sociall")
    private Session mailSession;

    @Override
    public void send(List<String> tos, List<String> ccs, List<String> bccs, String subject, String text) throws MessagingException, UnsupportedEncodingException {
        Message msg = new MimeMessage(mailSession);
        msg.setSubject(subject);
        if (tos != null) {
            for (String adress : tos) {
                msg.addRecipient(RecipientType.TO, new InternetAddress(
                        adress));
            }
        }

        if (ccs != null) {
            for (String adress : ccs) {
                msg.addRecipient(RecipientType.CC, new InternetAddress(
                        adress));
            }
        }

        if (bccs != null) {
            for (String adress : bccs) {
                msg.addRecipient(RecipientType.BCC, new InternetAddress(
                        adress));
            }
        }
        msg.setFrom(new InternetAddress("vdesamoury@gmail.com", "Foodish"));

        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(text);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        msg.setContent(multipart);

        Transport.send(msg);
    }

    @Override
    public void send(String to, String subject, String text) throws MessagingException, UnsupportedEncodingException {
        List<String> l = new ArrayList<>();
        l.add(to);
        send(l, null, null, subject, text);
    }

    @Override
    public void sendConfirmation(String email, Language language, String token) throws Exception {
        String subject = "Foodish - inscription";
        String text = "Merci de vous être inscrit sur Foodish. Pour valider "
                + "votre inscription, cliquez sur ce lien ou copiez-le dans la barre "
                + "d'adresse de votre navigateur: "
                + "https://foodish.com:8181/foodish/pages/signin/confirm.xhtml?token=" + token;
        send(email, subject, text);
    }

    @Override
    public void sendPasswordChangedInfo(String email, Language language) throws Exception {
        String subject = "Changement de mot de passe";
        String text = "Votre mot de passe a été modifié. Si vous n'être pas à l'origine "
                + "de cette modification, veuillez cliquer sur ce lien pour le réinitialiser: "
                + "https://foodish.com:8181/foodish/pages/signin/resetpassword.xhtml";
        // TODO token
        send(email, subject, text);
    }

    @Override
    public void sendAccountDeletedInfo(List<String> emails) throws Exception {
        String subject = "Compte supprimé";
        String text = "Votre compte a été supprimé de notre base de données car vous ne l'avez pas activé "
                + "dans les délais impartis.";
        send(null, null, emails, subject, text);
    }

    @Override
    public void sendInviteInfo(String email, Language language) throws Exception {
        String subject = "Vous avez reçu une invitation!";
        String text = "Vous avez reçu une invitation. Rendez-vous sur la page suivante "
                + "pour la voir: "
                + "https://foodish.com:8181/foodish/pages/member/friends.xhtml";
        // TODO token
        send(email, subject, text);
    }

}
