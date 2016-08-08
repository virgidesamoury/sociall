/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.facade;

import be.isl.desamouryv.sociall.domain.Artifact;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.xml.sax.InputSource;

/**
 *
 * @author Virginie
 */
public class ArtifactFacadeTest {

    private static EntityManagerFactory emf;

    public ArtifactFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("setUpClass");
        emf = Persistence.createEntityManagerFactory("sociallTestPU");
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws DatabaseUnitException, FileNotFoundException, SQLException {
        System.out.println("setUp");
        EntityManager em = emf.createEntityManager();
        artifactFacade = new ArtifactFacadeImpl();
        artifactFacade.setEntityManager(em);

        Connection jdbcConnection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/socialltest", "sociall", "sociall");
        FlatXmlDataSet dataSet = new FlatXmlDataSet(new FlatXmlProducer(new InputSource(new FileInputStream("src/test/resources/data/dataset.xml"))));
        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
    }
    private ArtifactFacadeImpl artifactFacade;

    @After
    public void tearDown() {
    }

    @Test
    public void testFindWithString() {
        List<Artifact> list = artifactFacade.findWithString("lasagne");
        assertTrue("list length", list.size() == 3);
        System.out.println(list);
    }

}
