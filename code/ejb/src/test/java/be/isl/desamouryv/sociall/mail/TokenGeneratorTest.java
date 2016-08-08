/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.mail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Virginie
 */
public class TokenGeneratorTest {

    public TokenGeneratorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of generateToken method, of class TokenGenerator.
     */
    @SuppressWarnings("UseInjectionInsteadOfInstantion")
    @Test
    public void testGenerateToken() throws Exception {
        System.out.println("generateToken");
        String string = "vdesamoury@gmail.com";
        TokenGenerator instance = new TokenGeneratorImpl();
        String result = instance.generateToken(string);
        assertNotNull(result);
        System.out.println(result);
        System.out.println(result.length());
    }

}
