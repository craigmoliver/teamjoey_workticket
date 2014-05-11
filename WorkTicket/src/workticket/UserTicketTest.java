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
		
		assertFalse(UserTicket.validPassword("shoPower", "shopass333"));
		assertTrue(UserTicket.validPassword("shoPower", "shopass"));
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
