/**
 * 
 */
package db;


/**
 * @author TeamJoey
 *
 */
public class UserDTO {
	private boolean newUser;
	private String username;
	private	String passhash;
	private String email;
	private String name;
	private String role;
	
	/**
	 * 
	 */
	public UserDTO() {
		setNewUser(true);
		setUsername("");
		setPasshash("");
		setEmail("");
		setName("");
		setRole("Worker");
	}
	
	/**
	 * 
	 * @return
	 */
	public UserDTO(String username, String passhash, String email, String name, String role) {
		setNewUser(false);
		setUsername(username);
		setPasshash(passhash);
		setEmail(email);
		setName(name);
		setRole(role);
	}

	/**
	 * @return the newUser
	 */
	public boolean isNewUser() {
		return newUser;
	}

	/**
	 * @param newUser the newUser to set
	 */
	public void setNewUser(boolean newUser) {
		this.newUser = newUser;
	}

	/**
	 * 
	 * @return
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the passhash
	 */
	public String getPasshash() {
		return passhash;
	}

	/**
	 * @param passhash the passhash to set
	 */
	public void setPasshash(String passhash) {
		this.passhash = passhash;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
}
