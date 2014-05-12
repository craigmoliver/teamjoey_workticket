package db;


import java.sql.Date;
import java.util.GregorianCalendar;

/**
 * Represents a work ticket created by a user. This includes the ticket ID#, date posted, title,
 * description, and assigned user of the ticket. This is a Data Transfer Object (DTO).
 * @author TeamJoey
 *
 */
public class TicketDTO {
	private int ticketId;
	private GregorianCalendar datePosted;
	private String title;
	private String description;
	private String assignedTo;
	private String status;
	
	/**
	 * Sets an empty Work Ticket constructor.
	 */
	public TicketDTO() {
		setTicketId(0);
		setDatePosted(new GregorianCalendar());
		setTitle("");
		setDescription("");
		setAssignedTo("");
		setStatus("open");
	}
	
	/**
	 * Loads a new Work Ticket item with data.
	 * @param ticketId
	 * @param datePosted
	 * @param title
	 * @param description
	 */
	public TicketDTO(int ticketId, Date datePosted, String title, String description, String assignedTo, String status) {
		// setup calendar
		GregorianCalendar calDatePosted = new GregorianCalendar();
		calDatePosted.setTime(datePosted);
		
		setTicketId(ticketId);
		setDatePosted(calDatePosted);
		setTitle(title);
		setDescription(description);
		setAssignedTo(assignedTo);
		setStatus(status);
	}
	
	/**
	 * Returns datePosted as java.util.Date
	 * @return 
	 */
	public java.util.Date getDatePostedAsDate() {
		return this.datePosted.getTime();
	}
	
	
	/**
	 * Returns the ID# of the Work Ticket
	 * @return the ticketId
	 */
	public int getTicketId() {
		return ticketId;
	}
	/**
	 * Sets the ID# of the Work Ticket.
	 * @param ticketId the ticketId to set
	 */
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	/**
	 * Returns the date posted of the Work Ticket.
	 * @return the datePosted
	 */
	public GregorianCalendar getDatePosted() {
		return datePosted;
	}
	/**
	 * Sets the date posted of the Work Ticket.
	 * @param datePosted the datePosted to set
	 */
	public void setDatePosted(GregorianCalendar datePosted) {
		this.datePosted = datePosted;
	}
	/**
	 * Returns the title of the Work Ticket.
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Sets the title of the Work Ticket.
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * Returns the description of the Work Ticket.
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Sets the description of the Work Ticket.
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the username assigned to the Work Ticket.
	 * @return the assignedTo
	 */
	public String getAssignedTo() {
		return assignedTo;
	}

	/**
	 * Sets the username that is assigned the Work Ticket.
	 * @param assignedTo the assignedTo to set
	 */
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	/**
	 * Returns the status of the Work Ticket.
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status that is assigned the Work Ticket.
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
