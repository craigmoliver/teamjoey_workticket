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
import bands.Band;
import bands.DBHelper;
/**
 * Test of the TicketHelper object.
 * @author Team Joey
 *
 */

public class TicketHelperTest extends TestCase {

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
		 
			try {
				TicketHelper instance1 = new TicketHelper(0);
				assertTrue(instance1.getAnnotations() !=null);
				assertTrue(instance1.getHasAnnotations() !=null);
				assertTrue(instance1.getLatestAnnotation() !=null);
				assertTrue(instance1.getTicket() !=null);
				assertTrue(instance1.getUsers() !=null);
			}
			catch (SQLException sqle){
				System.out.println("exception in setup: "+ sqle.getMessage());
			}
		
	}
	
	
	/**Tests saving a new ticket */
	@Test
	public void testSaveNewTicket() throws Exception{
		try {
		TicketHelper instance2 = new TicketHelper(0); 
		TicketHelper.saveNewTicket("Broken Macbook", "My Macbook is on fire");
		TicketDTO TicketHelper=instance2.getTicket();
		}
		catch(SQLException sqle){
			System.out.println("Exception in saveNewTicket: "+sqle.getMessage());
		}
	}
}

		


