/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.isl.desamouryv.sociall.service;

import be.isl.desamouryv.sociall.domain.Password;
import javax.ejb.Local;

/**
 *
 * @author Virginie
 */
@Local
public interface PasswordGenerator {

    public Password createPassword(String pwd) throws Exception;
    
}
