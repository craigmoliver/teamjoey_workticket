package workticket;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

import security.PasswordHash;
import db.UserDTO;
import db.WorkTicketDAO;

/**
 * Opens a connection to the db and initializes all PrepareStatements used by the LoginController.
 * @author TeamJoey
 */
public class UserHelper {
	private UserDTO user;
	private ArrayList<String> allRoles;
	
	/**
	 * Verifies username and its role for access of a work ticket.
	 * @param username
	 */
	public UserHelper(String username) {
		WorkTicketDAO workTicketDAO = new WorkTicketDAO();
		UserDTO user = workTicketDAO.loadUser(username);
		setUser(user);
		populateAllRoles();
	}
	
	/**
	 * Verifies user exists in db.
	 */
	public UserHelper() {
		setUser(new UserDTO());
		populateAllRoles();
	}	
	
	public static Boolean userExists(String username) {
		WorkTicketDAO workTicketDAO = new WorkTicketDAO();
		UserDTO user = workTicketDAO.loadUser(username);
		return !user.getUsername().isEmpty(); // user exists if UserDTO has Username value
	}	
	
	/**
	 * Initializes the roles in the db.
	 */
	private void populateAllRoles(){
		allRoles = new ArrayList<String>();
		allRoles.add("Worker");
		allRoles.add("Manager");
	}
	
	/**
	 * Loads empty user constructor with name, email, username, password, and role.
	 * @param name
	 * @param email
	 * @param username
	 * @param password
	 * @param role
	 */
	public static void saveUser(String name, String email, String username, String password, String role) {
		try {
			WorkTicketDAO workTicketDAO = new WorkTicketDAO();
			UserDTO user = workTicketDAO.loadUser(username);
			user.setEmail(email);
			user.setName(name);
			user.setUsername(username);
			
			// update if set, save if new
			if ((!user.isNewUser() && !password.isEmpty()) || 
				 user.isNewUser()) { 
				user.setPasshash(PasswordHash.createHash(password));
			}
			user.setRole(role);
			workTicketDAO.saveUser(user);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Returns the user's name.
	 * @return the user
	 */
	public UserDTO getUser() {
		return user;
	}

	/**
	 * Sets the user's name.
	 * @param user the user to set
	 */
	public void setUser(UserDTO user) {
		this.user = user;
	}	
	
	/**
	 * Returns a list of roles available for a user.
	 * @return the allRoles
	 */
	public ArrayList<String> getAllRoles() {
		return allRoles;
	}
}
