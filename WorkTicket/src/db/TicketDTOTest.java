package db;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import junit.framework.TestCase;

import org.junit.Test;


/**
 * Test of the TicketDTO for the work ticket system.
 * @author TeamJoey
 */
public class TicketDTOTest extends TestCase {

	
	/**
	 * Tests creating a new Work Ticket
	 * @throws ParseException 
	 * @Test
	 */
	public void testConstructor() throws ParseException {
		//Test default
		TicketDTO ticket = new TicketDTO();
		assertEquals("Ticket ID", 0,ticket.getTicketId());
		assertEquals("Title", "",ticket.getTitle());
		assertEquals("Description", "",ticket.getDescription());
		assertEquals("Assigned To", "",ticket.getAssignedTo());
		assertEquals("Status", "",ticket.getAssignedTo());

		//Test being set	
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date date = (Date)formatter.parse("5/10/2014");
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		TicketDTO ticket1 = new TicketDTO(1, sqlDate, "Printer fire", "Printer caught fire while printing", "george", "open");
		assertEquals("Ticket ID", 1, ticket1.getTicketId());
		assertEquals("Date Posted: Month", 4, ticket1.getDatePosted().get(Calendar.MONTH));
		assertEquals("Date Posted: Year", 2014, ticket1.getDatePosted().get(Calendar.YEAR));
		assertEquals("Date Posted: Year", 10, ticket1.getDatePosted().get(Calendar.DAY_OF_MONTH));
		assertEquals("Title", "Printer fire",ticket1.getTitle());
		assertEquals("Description", "Printer caught fire while printing",ticket1.getDescription());
		assertEquals("Assigned To", "george",ticket1.getAssignedTo());
		assertEquals("Status", "open",ticket1.getStatus());
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
		GregorianCalendar date = (GregorianCalendar) Calendar.getInstance();
		date.clear();
		date.set(2014, 5, 10);
		TicketDTO ticket3 = new TicketDTO();
		ticket3.setDatePosted(date);
		assertEquals("Date Posted: Month", 5, ticket3.getDatePosted().get(Calendar.MONTH));
		assertEquals("Date Posted: Year", 2014, ticket3.getDatePosted().get(Calendar.YEAR));
		assertEquals("Date Posted: Year", 10, ticket3.getDatePosted().get(Calendar.DAY_OF_MONTH));
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
