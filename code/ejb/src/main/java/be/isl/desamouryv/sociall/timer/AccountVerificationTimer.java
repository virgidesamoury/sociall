/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.timer;

import be.isl.desamouryv.sociall.domain.AccountActivation;
import be.isl.desamouryv.sociall.facade.AccountActivationFacade;
import be.isl.desamouryv.sociall.facade.UserFacade;
import be.isl.desamouryv.sociall.mail.MailSender;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Virginie
 */
@Singleton
@Startup
@LocalBean
public class AccountVerificationTimer {

    private static final Logger logger = Logger.getLogger(AccountVerificationTimer.class.getName());

    @PersistenceContext(unitName = "sociallDevPU")
    protected EntityManager em;
    
    @EJB
    AccountActivationFacade accountActivationFacade;
    @EJB
    UserFacade userFacade;
    @EJB
    MailSender mailSender;

//    @Schedule(minute = "*/5", hour = "*")
    public void doVerifyInactiveAccounts() {
        logger.log(Level.INFO, "doVerifyInactiveAccounts - {}", new Date());
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, -2);
        List<String> mails = new ArrayList<>();
        List<AccountActivation> expiredAccounts = accountActivationFacade.findAccountsCreatedBefore(c.getTime());
        if(expiredAccounts != null){
            for (AccountActivation accountActivation : expiredAccounts) {
                mails.add(accountActivation.getUser().getEmail());
                userFacade.delete(accountActivation.getUser());
            }
            try {
                mailSender.sendAccountDeletedInfo(mails);
            } catch (Exception ex) {
                Logger.getLogger(AccountVerificationTimer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
