package db;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;


/**
 * Test of the userDTO for the work ticket system.
 * @author TeamJoey
 */
public class UserDTOTest extends TestCase {

	/**
	 * Tests adding a user to the work ticket system.
	 * @Test
	 */
	public void testConstructor() {
		UserDTO user = new UserDTO("johnq", "public", "johnq@work.com", "John Q Public", "worker");
		assertEquals("Username", "johnq",user.getUsername());
		assertEquals("Password", "public",user.getPasshash());
		assertEquals("Email", "johnq@work.com",user.getEmail());
		assertEquals("Name", "John Q Public",user.getName());
		assertEquals("Role", "worker",user.getRole());
	}

	/**
	 * Verifies an active user in the work ticket system.
	 */
	 public void testNewUser() {
		 UserDTO newuser = new UserDTO("jlewis", "passcode", "jlewis@email.com", "Jeff Lewis", "worker");
		 assertEquals("Username", "jlewis", newuser.getUsername());
		 assertTrue(newuser.getUsername().equals("jlewis"));
		 assertFalse(newuser.getUsername().equals(null));
		 
    }
	
	/**
	 * Tests that the user name method works.
	 */
	public void testSetUsername(){
		UserDTO user1 = new UserDTO();
		user1.setUsername("Jeff");
		assertEquals("Username", "Jeff", user1.getUsername());
	}
	
	/**
	 * Tests that the password method works.
	 */
	public void testSetPasshash(){
		UserDTO user2 = new UserDTO();
		user2.setPasshash("passcode");
		assertEquals("Password", "passcode", user2.getPasshash());
	}
	
	/**
	 * Tests that the email method works.
	 */
	public void testSetEmail(){
		UserDTO user3 = new UserDTO();
		user3.setEmail("jlewis@email.com");
		assertEquals("Email", "jlewis@email.com", user3.getEmail());
	}
	
	/**
	 * Tests that the name method works.
	 */
	public void testName(){
		UserDTO user4 = new UserDTO();
		user4.setName("Jeff Lewis");
		assertEquals("Name", "Jeff Lewis", user4.getName());
	}

	/**
	 * Tests that the role method works.
	 */
	public void testRole(){
		UserDTO user5 = new UserDTO();
		user5.setRole("worker");
		assertEquals("Role", "worker", user5.getRole());
		
	}
}
