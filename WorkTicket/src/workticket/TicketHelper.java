package workticket;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import db.TicketDTO;
import db.AnnotationDTO;
import db.WorkTicketDAO;
import db.UserDTO;

/**
 * Opens a connection to the db and initializes all PrepareStatements used by the TicketController.
 * @author TeamJoey
 *
 */
public class TicketHelper {
	private TicketDTO ticket;
	private AnnotationDTO latestAnnotation;
	private ArrayList<AnnotationDTO> annotations;
	private Boolean hasAnnotations;
	private ArrayList<String> users;
	
	/**
	 * Verifies annotations on a specific ticket ID#.
	 * @param ticketId
	 */
	public TicketHelper(int ticketId) {
		WorkTicketDAO workTicketDAO = new WorkTicketDAO();
		setTicket(workTicketDAO.loadTicket(ticketId));
		ArrayList<AnnotationDTO> annotations = workTicketDAO.listTicketAnnotations(ticketId);
		if (annotations.size() > 0) {
			setLastestAnnotation(annotations.get(0));
			setHasAnnotations(true);
		}
		else {
			setLastestAnnotation(new AnnotationDTO());
			setHasAnnotations(false);
		}
		setAnnotations(annotations);
		
		ArrayList<String> users = new ArrayList<String>();
		users.add(""); // add blank option
		for (UserDTO user: workTicketDAO.listUsers()) { // adds users for select box
			users.add(user.getUsername());
		}
		setUsers(users);
	}
	
	/**
	 * Returns a list of work tickets.
	 * @return
	 */
	public static ArrayList<TicketHelper> listTickets() {
		WorkTicketDAO workTicketDAO = new WorkTicketDAO();
		ArrayList<TicketHelper> tickets = new ArrayList<TicketHelper>();
		for (TicketDTO ticketDTO : workTicketDAO.listTickets()) { //loops through TicketDTOs
			tickets.add(new TicketHelper(ticketDTO.getTicketId()));
		}
		return tickets;
	}
	
	/**
	 * Returns a list of tickets assigned to a specific user.
	 * @param assignedTo
	 * @return
	 */
	public static ArrayList<TicketHelper> listTickets(String assignedTo) {
		WorkTicketDAO workTicketDAO = new WorkTicketDAO();
		ArrayList<TicketHelper> tickets = new ArrayList<TicketHelper>();
		for (TicketDTO ticketDTO : workTicketDAO.listTickets(assignedTo)) { //loops through TicketDTOs assigned to assignedTo
			tickets.add(new TicketHelper(ticketDTO.getTicketId()));
		}
		return tickets;
	}
	
	/**
	 * Creates new ticket and returns its ticket ID#.
	 * @param title of ticket
	 * @param description of ticket
	 * @return ticketId
	 */
	public static int saveNewTicket(String title, String description) {
		// new ticket to save
		TicketDTO ticket = new TicketDTO();
		ticket.setDatePosted((GregorianCalendar) Calendar.getInstance()); // Set date to right now
		ticket.setDescription(description);
		ticket.setTitle(title);
		ticket.setAssignedTo("");
		
		// save to database and return ticketId
		WorkTicketDAO workTicketDAO = new WorkTicketDAO();
		int ticketId = workTicketDAO.saveTicket(ticket);
		return ticketId;
	}
	
	/**
	 * Loads the object ticket and username from the db.
	 * @param ticket
	 * @param username
	 */
	public static void assignTicketTo(TicketDTO ticket, String username) {
		WorkTicketDAO workTicketDAO = new WorkTicketDAO();
		ticket.setAssignedTo(username);
		workTicketDAO.saveTicket(ticket);
	}
	
	/**
	 * Saves a new annotation with ticket ID#, username, and text.
	 * @param ticketId
	 * @param username
	 * @param text
	 */
	public static void saveNewAnnotation(int ticketId, String username, String text) {
		AnnotationDTO annotation = new AnnotationDTO();
		annotation.setAuthorUsername(username);
		annotation.setText(text);
		annotation.setTicketId(ticketId);
		annotation.setDatePosted((GregorianCalendar) Calendar.getInstance()); // Set date to right now
		
		WorkTicketDAO workTicketDAO = new WorkTicketDAO();
		workTicketDAO.saveAnnotation(annotation);
	}
	
	/**
	 * Returns a work ticket.
	 * @return the ticket
	 */
	public TicketDTO getTicket() {
		return ticket;
	}
	/**
	 * Sets a work ticket.
	 * @param ticket the ticket to set
	 */
	public void setTicket(TicketDTO ticket) {
		this.ticket = ticket;
	}
	/**
	 * Returns the last added annotation by date.
	 * @return the latestAnnotation
	 */
	public AnnotationDTO getLatestAnnotation() {
		return latestAnnotation;
	}
	/**
	 * Sets the last added annotation by date.
	 * @param latestAnnotation the lastestAnnotation to set
	 */
	public void setLastestAnnotation(AnnotationDTO latestAnnotation) {
		this.latestAnnotation = latestAnnotation;
	}
	/**
	 * Returns a list of annotations.
	 * @return the annotations
	 */
	public ArrayList<AnnotationDTO> getAnnotations() {
		return annotations;
	}
	/**
	 * Sets a list of annotations.
	 * @param annotations the annotations to set
	 */
	public void setAnnotations(ArrayList<AnnotationDTO> annotations) {
		this.annotations = annotations;
	}

	/**
	 * Verifies that a work ticket has annotations.
	 * @return the hasAnnotations
	 */
	public Boolean getHasAnnotations() {
		return hasAnnotations;
	}

	/** Loads annotations from db
	 * @param hasAnnotations the hasAnnotations to set
	 */
	public void setHasAnnotations(Boolean hasAnnotations) {
		this.hasAnnotations = hasAnnotations;
	}

	/**
	 * Returns a list of users.
	 * @return the users
	 */
	public ArrayList<String> getUsers() {
		return users;
	}

	/**
	 * Adds a user to the user list.
	 * @param users the users to set
	 */
	public void setUsers(ArrayList<String> users) {
		this.users = users;
	}
	
	
}
