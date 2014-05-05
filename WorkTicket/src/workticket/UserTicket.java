/**
 * 
 */
package workticket;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import db.UserDTO;
import db.WorkTicketDAO;

/**
 * @author craigmoliver
 *
 */
public class UserTicket {

	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public static boolean validPassword(String username, String password){
		WorkTicketDAO workTicketDAO = new WorkTicketDAO();
		UserDTO userDTO = workTicketDAO.loadUser(username);
		if (!userDTO.isNew()) {
			Boolean isValid;
			try {
				isValid = security.PasswordHash.validatePassword(password, userDTO.getPasshash());
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
}
