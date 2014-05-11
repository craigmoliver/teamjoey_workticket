package db;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class WorkTicketDAOTest extends TestCase {

	/**Remove all the records in the work ticket db */
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		String jdbcUrl ="jdbc:mysql://Shos-Air.local/workticket";
		String username = "awsuser";
		String password = "abc123";
		try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
		//System.out.println("Connected to DB!");
			//PreparedStatement clearAnnoTableStatement = conn.prepareStatement("delete from annotation");
			//PreparedStatement clearTicketTableStatement = conn.prepareStatement("delete from ticket");
			//PreparedStatement clearUserTableStatement = conn.prepareStatement("delete from user");
			//clearAnnoTableStatement.execute();
			//clearTicketTableStatement.execute();
			//clearUserTableStatement.execute();
		}
		catch(SQLException sqle){
			System.out.println("exception in setup: "+ sqle.getMessage());
		}
	}

	/**
	 * Test Connects to db. verifies that all the PreparedStatements have been created and aren't null
	*/
	public void testWorkTicketDAO() throws Exception {
		try{
		WorkTicketDAO instance1 = new WorkTicketDAO();
			//Annotation Prepared Statements
			assertTrue(instance1.selectAnnotationStatement !=null);
			assertTrue(instance1.selectAllAnnotationStatement !=null);
			assertTrue(instance1.updateAnnotationStatement !=null);
			assertTrue(instance1.insertAnnotationStatement !=null);
			assertTrue(instance1.selectTicketAnnotationsStatement !=null);
			//Ticket Prepared Statements
			assertTrue(instance1.selectTicketStatement !=null);
			assertTrue(instance1.selectAllTicketStatement !=null);
			assertTrue(instance1.selectAllTicketAssignedToStatement !=null);
			assertTrue(instance1.updateTicketStatement !=null);
			assertTrue(instance1.insertTicketStatement !=null);
			//User Prepared Statements
			assertTrue(instance1.selectUserStatement !=null);
			assertTrue(instance1.selectAllUserStatement !=null);
			assertTrue(instance1.updateUserStatement !=null);
			assertTrue(instance1.insertUserStatement !=null);
		}
		catch (Exception sqle){
			System.out.println("exception in setup: "+ sqle.getMessage());
		}
	}
	/** Testing Date Holder */
				// Need an SQL date for date parameter
				Date tempDate = new Date();
				long longTime = tempDate.getTime();
				java.sql.Date instDatePost = new java.sql.Date(longTime);
				//End of temp parameter creation
	/**
	 * Test Returns new Annotation ID# from db.
	*/
	public void testLoadAnnotation() throws Exception{
		WorkTicketDAO instance2 = new WorkTicketDAO();
		//instance2.insertAnnotationStatement();
		
	}
	// ------------------User Tests----------------- //
	/**
	 * Test adding user, updating user and list users
	 */
	public void testUserDbMethods() throws Exception{
		
		WorkTicketDAO instance3 = new WorkTicketDAO();
		/** Test Adds a new user to the db.*/
		UserDTO user = new UserDTO("shoPower", "shopass", "sho@email.com", "Shomari Me", "Manager");
		instance3.saveUser(user);
		UserDTO user2 = new UserDTO("crayCraig", "crapass12", "craig@email.com", "Craig Name", "Worker");
		instance3.saveUser(user2);
		
		UserDTO testUser1 = instance3.loadUser("shopower");
		assertEquals("first user's name is","Shomari Me",testUser1.getName());
		assertEquals("first user's password is","shopass",testUser1.getPasshash());
		assertEquals("first user's email is","sho@email.com",testUser1.getEmail());
		assertEquals("first user's role is","Manager",testUser1.getRole());
		
		UserDTO testUser2 = instance3.loadUser("crayCraig");
		assertEquals("Craig's password is","crapass12",testUser2.getPasshash());
		assertEquals("Craig's email is","craig@email.com",testUser2.getEmail());
		
		/** Test updating a user on the db.*/
		UserDTO user3 = new UserDTO("crayCraig", "crapass1", "craig@email.net", "Craig Name", "Worker");
		instance3.saveUser(user3);
		UserDTO testUser3 = instance3.loadUser("crayCraig");
		assertEquals("Craig's new password is","crapass1",testUser3.getPasshash());
		assertEquals("Craig's new email is","craig@email.net",testUser3.getEmail());
		
		/**Test to see if List has all entries */
		ArrayList<UserDTO> list  = instance3.listUsers();
		assertEquals("The number of users ",2,list.size());
		
	}
	// ------------------Ticket Tests----------------- //
	/**
	 * Test adding Tickets and listing Tickets
	 */
	public void testTicketDbMethods() throws Exception{
		WorkTicketDAO instance4 = new WorkTicketDAO();
		//ArrayList<TicketDTO> tickets = instance4.listTickets();
		//assertTrue("The initial number of all tickets is empty",tickets.isEmpty());
		TicketDTO tik1 = new TicketDTO(-1, instDatePost, "Tie caught in printer", "What had happened was that there was a tie. Then there was a printer then somehow they decided to fight each other.", "Shomari Me");
		TicketDTO tik2 = new TicketDTO(-2, instDatePost, "Tie caught in fax machine", "What had happened was that there was a tie. Then there was a fax machinethen somehow they decided to fight each other.", "Craig Name");
		TicketDTO tik3 = new TicketDTO(-3, instDatePost, "Tie caught in paper schredder", "What had happened was that there was a tie. Then there was a paper schredder then somehow they decided to fight each other.", "Craig Name");
		//This guy should stop wearing ties... Just saying...
		
		/**Test save tickets */
		int holdID1 = instance4.saveTicket(tik1);
		int holdID2 = instance4.saveTicket(tik2);
		int holdID3 = instance4.saveTicket(tik3);
		
		/**Test List all tickets */
		ArrayList<TicketDTO> tickets2 = instance4.listTickets();
		assertEquals("The number of all tickets after adding more tickets ",3,tickets2.size());
		
		/**Test List tickets per user*/
		ArrayList<TicketDTO> ticketsUser = instance4.listTickets("Craig Name");
		assertEquals("The number of Craig tickets ",2,ticketsUser.size());
		ArrayList<TicketDTO> ticketsUser2 = instance4.listTickets("Shomari Me");
		assertEquals("The number of Sho tickets ",1,ticketsUser2.size());
		
		/** Test loading tickets*/
		TicketDTO testTik1 = instance4.loadTicket(holdID1);
		assertEquals("This ticket was assigned to","Shomari Me",testTik1.getAssignedTo());
		TicketDTO testTik2 = instance4.loadTicket(holdID3);
		assertEquals("This ticket was assigned to","Tie caught in paper schredder",testTik2.getTitle());
	}	
	
	// ------------------Annotation Tests----------------- //
	/**
	 * Test listing annotations, a tickets annotations
	*/
	public void testAnnotationDbMethods() throws Exception{
		WorkTicketDAO instance5 = new WorkTicketDAO();
		ArrayList<AnnotationDTO> annoList = instance5.listAnnotations();
		assertTrue("The initial number of all tickets is empty",annoList.isEmpty());
		
		/**Load some Annotations */
		AnnotationDTO anno1 = new AnnotationDTO(-1, 8, "shoPower", "This guy is stupid", instDatePost);
		AnnotationDTO anno2 = new AnnotationDTO(-2, 9, "crayCraig", "This guy should void all electronics.", instDatePost);
		AnnotationDTO anno3 = new AnnotationDTO(-3, 9, "crayCraig", "Someone please help this man.", instDatePost);
		/**Test save Annotations */
		int annoID1 = instance5.saveAnnotation(anno1);
		int annoID2 = instance5.saveAnnotation(anno2);
		int annoID3 = instance5.saveAnnotation(anno3);
		
		/**Test List all Annotations */
		ArrayList<AnnotationDTO> annoList2 = instance5.listAnnotations();
		assertEquals("The number of all tickets after adding more tickets ",3,annoList2.size());
		
		/**Test List Annotations per ticket  */
		ArrayList<AnnotationDTO> annoTicket = instance5.listTicketAnnotations(9);
		assertEquals("The number of Craig tickets ",2,annoTicket.size());
		ArrayList<AnnotationDTO> annoTicket2 = instance5.listTicketAnnotations(8);
		assertEquals("The number of Sho tickets ",1,annoTicket2.size());
		
		/** Test loading Annotations */
		AnnotationDTO testAnno1 = instance5.loadAnnotation(annoID1);
		assertEquals("This ticket was assigned to","shoPower",testAnno1.getAuthorUsername());
		AnnotationDTO testAnno2 = instance5.loadAnnotation(annoID3);
		assertEquals("This ticket was assigned to","Someone please help this man.",testAnno2.getText());
		
	}

}
