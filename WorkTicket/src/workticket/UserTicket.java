package workticket;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.http.HttpSession;

import db.UserDTO;
import db.WorkTicketDAO;

/**
 * Manages session scope of username and password security log-in.
 * @author TeamJoey
 *
 */
public class UserTicket {

	/**
	 * Validates username and password match in the db to allow access to a work ticket.
	 * @param username
	 * @param password
	 * @return
	 */
	public static boolean validPassword(String username, String password){
		WorkTicketDAO workTicketDAO = new WorkTicketDAO();
		UserDTO user = workTicketDAO.loadUser(username);
		if (!user.isNewUser()) {
			Boolean isValid;
			try {
				isValid = security.PasswordHash.validatePassword(password, user.getPasshash());
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				isValid = false;
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); 
				isValid = false;
			}
			return isValid;
		}
		return false;
	}

	/**
	 * Loads username within existing session.
	 * @param session
	 * @param username
	 */
	public static void setUserTicket(HttpSession session, String username) {
		WorkTicketDAO workTicketDAO = new WorkTicketDAO();
		UserDTO user = workTicketDAO.loadUser(username);		
		session.setAttribute("userTicket", user);
	}

	/**
	 * Returns work ticket of a user within existing session.
	 * @param session
	 * @return
	 */
	public static UserDTO getUserTicket(HttpSession session){
		return (UserDTO)session.getAttribute("userTicket"); 
	}

	/**
	 * Ends an existing log-in session.
	 * @param session
	 */
	public static void destoryUserTicket(HttpSession session) {
		session.invalidate();
	}
}
