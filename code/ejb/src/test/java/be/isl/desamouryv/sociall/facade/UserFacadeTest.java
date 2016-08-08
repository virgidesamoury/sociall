/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.facade;

import be.isl.desamouryv.sociall.domain.User;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.InputSource;

/**
 *
 * @author Virginie
 */
//@LocalClient
public class UserFacadeTest {

//    @EJB
    UserFacadeImpl userFacade;

//    private static InitialContext initialContext;
    
    private static EntityManagerFactory emf;

    // http://tomee.apache.org/examples-trunk/application-composer/
    // http://stackoverflow.com/questions/8713945/unit-testing-a-ejb3-0-which-has-another-ejb-injected
//    @Module
//    public EjbJar beans() {
//        EjbJar ejbJar = new EjbJar();
//        ejbJar.addEnterpriseBean(new StatelessBean(UserFacade.class));
//        ejbJar.addEnterpriseBean(new StatelessBean(MailSender.class));
//        ejbJar.addEnterpriseBean(new StatelessBean(TokenGenerator.class));
//        return ejbJar;
//    }
    public UserFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("setUpClass");

        emf = Persistence.createEntityManagerFactory("sociallTestPU");
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("setUp");
        EntityManager em = emf.createEntityManager();
        userFacade = new UserFacadeImpl();
        userFacade.setEntityManager(em);
//        initialContext.bind("inject", this);
        Connection jdbcConnection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/socialltest", "sociall", "sociall");
        FlatXmlDataSet dataSet = new FlatXmlDataSet(new FlatXmlProducer(new InputSource(new FileInputStream("src/test/resources/data/dataset.xml"))));
        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testFindById() throws Exception {
        System.out.println("testFind");
        User user = new User();
        user.setId(10L);
        User result = userFacade.find(user);
        System.out.println(result);
        Assert.assertTrue(result.getFirstName().equalsIgnoreCase("Harry"));
        Assert.assertTrue(result.getLastName().equalsIgnoreCase("Potter"));
    }

//    @Test
//    public void testFindByToken() throws Exception {
//        System.out.println("testFind");
//        User result = userFacade.findByToken("example token");
//        Assert.assertNotNull(result);
//        System.out.println(result);
//        Assert.assertTrue(result.getToken().equals("example token"));
//        Assert.assertTrue(result.getId() == 3L);
//    }

    @Test
    public void testSave() throws Exception {
        System.out.println("testSave");
        User user = new User();
        user.setFirstName("toto");
        user.setLastName("TOTO");
        user.setEmail("toto@example.com");
        userFacade.save(user);

        System.out.println(user);
    }

}
