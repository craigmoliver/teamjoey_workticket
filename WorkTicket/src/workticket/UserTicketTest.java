package workticket;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import junit.framework.TestCase;

import org.junit.Test;

import db.TicketDTO;
import db.UserDTO;
import db.WorkTicketDAO;

public class UserTicketTest extends TestCase{

	/**
	 * Validates username and password match in the db to allow access to a work ticket.
	 */
	public void testValidPassword() {
		UserTicket instance1 = new UserTicket();
		//boolean testPass = instance1.validPassword("shoPower", "shopass");
		//System.out.print(testPass);
		//System.out.print(testPass);
		//String username = "shoPower";
		//String password = "shopass";
		//assertEquals("", "isValid",instance1.validPassword(username, password));
		
		//assertFalse(instance1.validPassword(username, password));
		//assertTrue(instance1.validPassword(username,password));
	}

	/**
	 * Test Loading username within existing session.
	 /
	public void testSetUserTicket() {
		UserTicket instance2 = new UserTicket();
		instance2.setUserTicket(null, "shoPower");
		assertEquals("The user was set to ","shoPower", instance2.getUserTicket(null));
	}

	/**
	 * Returns work ticket of a user within existing session.
	 */
	public void testGetUserTicket() {
		UserTicket instance3 = new UserTicket();
		WorkTicketDAO DAO = new WorkTicketDAO();
		UserDTO user = DAO.loadUser("shoPower");		
		//instance3.session.setAttribute("userTicket", user);// testing for session
		ArrayList<TicketDTO> ticketsUser = DAO.listTickets(user.getName());
		assertEquals("The number of Sho tickets ",1,ticketsUser.size());
	}

	/**
	 * Ends an existing log-in session.
	 */
	public void testDestoryUserTicket() {
		UserTicket instance4 = new UserTicket();
		WorkTicketDAO DAO = new WorkTicketDAO();
		UserDTO user = DAO.loadUser("");
		//instance4.session.invalidate();// testing for session
		//assertTrue("This session has no username associated","",instance4.getUserTicket(null));// testing for session
		assertEquals("The number of Sho tickets ","",user.getName());
	}

}
