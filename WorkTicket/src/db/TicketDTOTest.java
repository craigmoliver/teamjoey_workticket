package db;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;


/**
 * Test of the TicketDTO for the work ticket system.
 * @author TeamJoey
 */
public class TicketDTOTest extends TestCase {

	
	/**
	 * Tests creating a new Work Ticket
	 * @Test
	 */
	public void testConstructor() {
		//Test default
		TicketDTO ticket = new TicketDTO();
		assertEquals("Ticket ID", 0,ticket.getTicketId());
		assertEquals("Date Posted", 0,ticket.getDatePosted());
		assertEquals("Title", "",ticket.getTitle());
		assertEquals("Description", "",ticket.getDescription());
		assertEquals("Assigned To", "",ticket.getAssignedTo());

		//Test being set
		TicketDTO ticket1 = new TicketDTO(1001, 2014-05-10, "Printer fire", "Printer caught fire while printing", 
				"Craig");
		assertEquals("Ticket ID", 1001,ticket1.getTicketId());
		assertEquals("Date Posted", 2014-05-10,ticket1.getDatePosted());
		assertEquals("Title", "Printer fire",ticket1.getTitle());
		assertEquals("Description", "Printer caught fire while printing",ticket1.getDescription());
		assertEquals("Assigned To", "Bob Lewis",ticket1.getAssignedTo());
	}

	
	/**
	 * Tests that the set ticket ID method works
	 */
	public void testSetTicketId(){
		TicketDTO ticket2 = new TicketDTO();
		ticket2.setTicketId(1001);
		assertEquals("Ticket ID#", 1001, ticket2.getTicketId());
	}
	
	/**
	 * Tests that the date posted method works.
	 */
	public void setDatePosted(){
		TicketDTO ticket3 = new TicketDTO();
		ticket3.setDatePosted(2014-05-10);
		assertEquals("Date Posted", 2014-05-10, ticket3.getDatePosted());
	}
	
	/**
	 * Tests that the set title method works
	 */
	public void testSetTitle(){
		TicketDTO ticket4 = new TicketDTO();
		ticket4.setTitle("Printer fire");
		assertEquals("Title", "Printer fire", ticket4.getTitle());
	}

	/**
	 * Tests that the description method works.
	 */
	public void testDescription(){
		TicketDTO ticket5 = new TicketDTO();
		ticket5.setDescription("Printer caught fire while printing");
		assertEquals("Description", "Printer caught fire while printing", ticket5.getDescription());
	}
	
	/**
	 * Tests that the assigned to method works.
	 */
	public void testAssignedTo(){
		TicketDTO ticket6 = new TicketDTO();
		ticket6.setAssignedTo("Bob Lewis");
		assertEquals("Assigned To", "Bob Lewis", ticket6.getAssignedTo());
	}

}
