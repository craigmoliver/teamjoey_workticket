package db;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Annotation class - Creates an annotation object with an id, 
 * ticket ID Author name, and annotation text
 * @author TeamJoey
 */

/** 
 * Initializes all the basics of an annotation.
 */
public class AnnotationDTO {
	private int annotationId;
	private int ticketId;
	private String authorUsername;
	private String text;
	private GregorianCalendar datePosted;
	
	/**
	 * Creates a default annotation object
	 * and set the parameters to the defaults.
	 */
	public AnnotationDTO() {
		setAnnotationId(0);
		setTicketId(0);
		setAuthorUsername("");
		setText("");
		setDatePosted(new GregorianCalendar());
	}
	
	/**
	 * Creates an annotation object.
	 * @param annotationId
	 */
	public AnnotationDTO(int annotationId, int ticketId, String authorName, String text, Date datePosted) {
		setAnnotationId(annotationId);
		setTicketId(ticketId);
		setAuthorUsername(authorName);
		setText(text);
		// setup calendar for datePosted
		GregorianCalendar calDatePosted = new GregorianCalendar();
		calDatePosted.setTime(datePosted);
		setDatePosted(calDatePosted);
	}
	
	/**
	 * Retrieves the annotation ID.
	 * @return the annotationId
	 */
	public int getAnnotationId() {
		return annotationId;
	}
	/**
	 * Sets the annotation ID.
	 * @param ticketId the ticketId to set
	 */
	public void setAnnotationId(int annotationId) {
		this.annotationId = annotationId;
	}

	/**
	 * Retrieves the Ticket ID.
	 * @return the ticketId
	 */
	public int getTicketId() {
		return ticketId;
	}

	/**
	 * Sets the ticketID of the annotation 
	 * to the ticket it is associated with.
	 * @param ticketId the ticketId to set
	 */
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	/**
	 * Retrieves the name of the user that is 
	 * creating the annotation.
	 * @return the authorName
	 */
	public String getAuthorUsername() {
		return authorUsername;
	}

	/**
	 * Sets the author name of the annotation object to the
	 * provided author name.
	 * @param authorName the authorName to set
	 */
	public void setAuthorUsername(String authorUsername) {
		this.authorUsername = authorUsername;
	}

	/**
	 * Retrieves the current text entered.
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text field to the text provided.
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Returns the posted date.
	 * @return the datePosted
	 */
	public GregorianCalendar getDatePosted() {
		return datePosted;
	}

	/**
	 * Sets the posted date.
	 * @param datePosted the datePosted to set
	 */
	public void setDatePosted(GregorianCalendar datePosted) {
		this.datePosted = datePosted;
	}
	
	
}
