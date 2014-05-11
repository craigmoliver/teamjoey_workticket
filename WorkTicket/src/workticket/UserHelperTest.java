package workticket;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;

import db.TicketDTO;

/**
 * Test of the UserHelper object.
 * @author Team Joey
 *
 */

public class UserHelperTest extends TestCase {

	/**Remove all the records in Work Ticket */
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		String JDBC_URL="jdbc:mysql://localhost:3306/workticket";
		String DB_USER="awsuser";
		String DB_PASS="abc123";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					JDBC_URL,DB_USER,DB_PASS);
			PreparedStatement clearTableStatement = conn.prepareStatement("Delete Records");
			clearTableStatement.execute();
		}
		catch(SQLException sqle){
			System.out.println("exception in setup: "+ sqle.getMessage());
		}
	}
	
	/** verifies that all the PreparedStatements have been created and aren't null
	 * @throws SQLException */
	@Test
	public void testConstructor()throws Exception {
		 
			UserHelper instance1 = new UserHelper();
			assertTrue(instance1.getAllRoles() !=null);
			assertTrue(instance1.getUser() !=null);
		
	}
	
	
	/**Tests saving a new user */
	@Test
	public void testSetUser1(){
		UserHelper instance2 = new UserHelper("John Doe"); 
		assertEquals("Name","John Doe",instance2.getUser());
	}
	
}

