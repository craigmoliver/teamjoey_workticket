package db;


/**
 * Represents a User account in the work ticket system. This includes a new or existing user, username, password,
 * email address, name of the user, and the user's role. This is a Data Transfer Object (DTO).
 * @author TeamJoey
 */
public class UserDTO {
	private boolean newUser;
	private String username;
	private	String passhash;
	private String email;
	private String name;
	private String role;
	
	/**
	 * Loads an empty User constructor for a User that does not currently have a username.
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
	 * Loads an empty User constructor for a User that currently has an active username.
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
	 * Returns whether the User has an active username, or not.
	 * @return the newUser
	 */
	public boolean isNewUser() {
		return newUser;
	}

	/**
	 * Sets the username of the User.
	 * @param newUser the newUser to set
	 */
	public void setNewUser(boolean newUser) {
		this.newUser = newUser;
	}

	/**
	 * Returns the username of the User.
	 * @return
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Sets the username of the User.
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Returns the password of the User.
	 * @return the passhash
	 */
	public String getPasshash() {
		return passhash;
	}

	/**
	 * Sets the password of the User.
	 * @param passhash the passhash to set
	 */
	public void setPasshash(String passhash) {
		this.passhash = passhash;
	}

	/**
	 * Returns the email address of the User.
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email address of the User.
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Returns the name of the User.
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the User.
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the role of the User.
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets the role of the User.
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
}
