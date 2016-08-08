/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.isl.desamouryv.sociall.facade;

import be.isl.desamouryv.sociall.domain.AccountActivation;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Virginie
 */
@Local
public interface AccountActivationFacade extends BeanFacade<AccountActivation>{

    public AccountActivation findByToken(String token);
    
    public List<AccountActivation> findAccountsCreatedBefore(Date date);
    
}
