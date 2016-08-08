/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.isl.desamouryv.sociall.mail;

import javax.ejb.Local;

/**
 *
 * @author Virginie
 */
@Local
public interface TokenGenerator {

    String generateToken(String string) throws Exception;

    public String generateSalt();
    
}
