package workticket;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;

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
		assertEquals(instance1.validPassword(username, password));
		String username = "shoPower";
		String password = "shopass";
		assertFalse(instance1.validPassword(username, password));
		assertTrue(instance1.validPassword(username,password));
	}

	/**
	 * Test Loading username within existing session.
	 */
	public void testSetUserTicket() {
		
	}

	/**
	 * Returns work ticket of a user within existing session.
	 */
	public void testGetUserTicket() {
		
	}

	/**
	 * Ends an existing log-in session.
	 */
	public void testDestoryUserTicket() {
		
	}

}
