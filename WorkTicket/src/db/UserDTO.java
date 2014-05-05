/**
 * 
 */
package db;

/**
 * @author TeamJoey
 *
 */
public class UserDTO {
	private boolean isNew;
	private String username;
	private	String passhash;
	private String email;
	private String name;
	
	/**
	 * 
	 */
	public UserDTO() {
		setNew(true);
		setUsername("");
		setPasshash("");
		setEmail("");
		setName("");
	}
	
	/**
	 * 
	 * @return
	 */
	public UserDTO(String username, String passhash, String email, String name) {
		setNew(false);
		setUsername(username);
		setPasshash(passhash);
		setEmail(email);
		setName(name);
	}
	
	/**
	 * @return the isNew
	 */
	public boolean isNew() {
		return isNew;
	}

	/**
	 * @param isNew the isNew to set
	 */
	public void setNew(boolean isNew) {
		this.isNew = isNew;
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
}
