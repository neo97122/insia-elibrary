package org.insia.eLibrary.test.base;

import java.io.FileInputStream;

import org.apache.log4j.Logger;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Michael Courcy
 *
 * Cette classe integre DBUnit et garantit ainsi que les tests sont r�p�tables et
 * en isolation, toute classe de test doit l'�tendre et doit avoir un fichier xml
 * correpondant par exemple
 *
 * src/test/java org.apache.tutorial.tapestrySpringHibernate.test.manager.UserManagerTest
 *
 * on trouvera le fichier xml de dataset
 *
 * src/test/resources org.apache.tutorial.tapestrySpringHibernate.test.manager.UserManagerTest.xml
 *
 */
public class Integration extends DBTestCase
{
	protected ApplicationContext context;
	protected Logger log;

    /**
     * Constructeur de DBTestCase
     *
     * @param name
     */
    public Integration(final String name)
    {
        super( name );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.gjt.mm.mysql.Driver" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:mysql://localhost/mbride" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "mbride" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "mbride" );
        // System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "" );
    }

    /**
     *
     * Renvoie le dataset en suivant la regle classe <MaClasse> MaClasse.xml
     *
     * @see org.dbunit.DatabaseTestCase#getDataSet()
     */
    protected IDataSet getDataSet() throws Exception
    {
    	String className = getClass().getName();
    	int pos = className.lastIndexOf(".");
    	className = className.substring(pos+1);
        return new FlatXmlDataSet(getClass().getResourceAsStream(className+".xml"));
    }

    /**
     * Initialise le contexte Spring et renvoie la DatabaseOperation par d�faut
     *
     * @return {@link DatabaseOperation} la databaseOperation a execut� en d�but de test
     * @see org.dbunit.DatabaseTestCase#getSetUpOperation()
     */
    protected DatabaseOperation getSetUpOperation() throws Exception
    {
    	//met un log a disposition
    	log = Logger.getLogger(this.getClass());
		log.info("Creation du contexte Spring");
		//initialise le contexte spring
        context = new ClassPathXmlApplicationContext(new String[]{
        		"ApplicationContext.xml",
        		"ApplicationContextDao.xml"
        });
        return DatabaseOperation.CLEAN_INSERT;
    }

    /**
     * Renvoie la databaseOperation en fin e test
     *
     * @return {@link DatabaseOperation} la databaseOperation a execut� en fin de test
     *
     * @see org.dbunit.DatabaseTestCase#getTearDownOperation()
     */
    protected DatabaseOperation getTearDownOperation() throws Exception
    {
        return DatabaseOperation.NONE;
    }



}
