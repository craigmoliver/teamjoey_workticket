/**
 * 
 */
package db;


import java.sql.Date;
import java.util.GregorianCalendar;

/**
 * @author TeamJoey
 *
 */
public class TicketDTO {
	private int ticketId;
	private GregorianCalendar datePosted;
	private String title;
	private String description;
	private String assignedTo;
	
	/**
	 * 
	 */
	public TicketDTO() {
		setTicketId(0);
		setDatePosted(new GregorianCalendar());
		setTitle("");
		setDescription("");
		setAssignedTo("");
	}
	
	/**
	 * 
	 * @param ticketId
	 * @param datePosted
	 * @param title
	 * @param description
	 */
	public TicketDTO(int ticketId, Date datePosted, String title, String description, String assignedTo) {
		// setup calendar
		GregorianCalendar calDatePosted = new GregorianCalendar();
		calDatePosted.setTime(datePosted);
		
		setTicketId(ticketId);
		setDatePosted(calDatePosted);
		setTitle(title);
		setDescription(description);
		setAssignedTo(assignedTo);
	}
	
	/**
	 * Returns datePosted as java.util.Date
	 * @return 
	 */
	public java.util.Date getDatePostedAsDate() {
		return this.datePosted.getTime();
	}
	
	
	/**
	 * @return the ticketId
	 */
	public int getTicketId() {
		return ticketId;
	}
	/**
	 * @param ticketId the ticketId to set
	 */
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	/**
	 * @return the datePosted
	 */
	public GregorianCalendar getDatePosted() {
		return datePosted;
	}
	/**
	 * @param datePosted the datePosted to set
	 */
	public void setDatePosted(GregorianCalendar datePosted) {
		this.datePosted = datePosted;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the assignedTo
	 */
	public String getAssignedTo() {
		return assignedTo;
	}

	/**
	 * @param assignedTo the assignedTo to set
	 */
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
}
