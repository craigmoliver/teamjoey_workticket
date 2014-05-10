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
	
	
	/**adds a couple of bands and gets the band list to verify that the band is present */
	@Test
	public void testAddBand() throws Exception{
		try {
		DBHelper instance2 = new DBHelper(); 
		instance2.setBandName("Peach Fuzz");
		instance2.setBandName("Peach Cobbler");
		instance2.setBandName("Peach Trees");
		ArrayList<Band> bandList=instance2.getBandList();
		//System.out.println("Bands on the list "+ instance2.getBandList()); not readable but shows all three albums exist
		assertEquals("The number of bands ",3,bandList.size());
		//assertTrue - bands are on list 
		}
		catch(SQLException sqle){
			System.out.println("Exception in testAddBand: "+sqle.getMessage());
		}
		
	}
	/**adds a couple of bands and albums and gets the band list to verify that the bands and albums are present */
	@Test
	public void testAddAlbum() throws Exception{
		try {
		DBHelper instance3 = new DBHelper();
		instance3.setBandName("Hellions Mercy");
		instance3.setBandName("Creed Fury");
		instance3.setBandName("Lions Maine");
		instance3.setAlbumName(1,"Tap Dancing Monkeys");
		instance3.setAlbumName(1,"High Flying Eagles");
		instance3.setAlbumName(1,"Low Down Frogs");
		ArrayList<Band> bandList=instance3.getBandList();
		//System.out.println("Albums for band with ID of 1 "  getAlbumsforBandStatement.setInt(1, 1));
		assertEquals("The number of bands ",3,bandList.size());
		//assertEquals("The number of albums created ",3,count(instance3.getAlbumsforBandStatement.setInt(1, 1)));
		}
		catch(SQLException sqle){
			System.out.println("Exception in testAddBand: "+sqle.getMessage());
		}
		
	}
}
