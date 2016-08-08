/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.mail;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.UUID;
import javax.ejb.Stateless;

/**
 *
 * @author Virginie
 */
@Stateless
public class TokenGeneratorImpl implements TokenGenerator {

    SecureRandom secureRandom = new SecureRandom();
    
    @Override
    public String generateToken(String string) throws Exception{
            return UUID.nameUUIDFromBytes(string.getBytes("UTF-8")).toString();
    }
    
    @Override
    public String generateSalt(){
        return new BigInteger(130, secureRandom).toString(32);
    }

}
