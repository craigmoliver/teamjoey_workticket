package workticket;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import security.PasswordHash;
import db.UserDTO;
import db.WorkTicketDAO;

/**
 * 
 * @author craigmoliver
 *
 */
public class UserHelper {
	private UserDTO user;
	
	/**
	 * 
	 * @param username
	 */
	public UserHelper(String username) {
		WorkTicketDAO workTicketDAO = new WorkTicketDAO();
		UserDTO user = workTicketDAO.loadUser(username);
		setUser(user);
	}
	
	public UserHelper() {
		setUser(new UserDTO());
	}
	
	/**
	 * 
	 * @param name
	 * @param email
	 * @param username
	 * @param password
	 */
	public static void saveUser(String name, String email, String username, String password) {
		try {
			WorkTicketDAO workTicketDAO = new WorkTicketDAO();
			UserDTO user = workTicketDAO.loadUser(username);
			user.setEmail(email);
			user.setName(name);
			user.setUsername(username);
			user.setPasshash(PasswordHash.createHash(password));
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
	 * @return the user
	 */
	public UserDTO getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(UserDTO user) {
		this.user = user;
	}	
}
