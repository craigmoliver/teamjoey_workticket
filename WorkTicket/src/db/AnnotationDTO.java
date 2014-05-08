/**
 * 
 */
package db;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author TeamJoey
 *
 */
public class AnnotationDTO {
	private int annotationId;
	private int ticketId;
	private String authorUsername;
	private String text;
	private GregorianCalendar datePosted;
	
	/**
	 * 
	 */
	public AnnotationDTO() {
		setAnnotationId(0);
		setTicketId(0);
		setAuthorUsername("");
		setText("");
		setDatePosted(new GregorianCalendar());
	}
	
	/**
	 * 
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
	 * Returns datePosted as java.util.Date
	 * @return 
	 */
	public java.util.Date getDatePostedAsDate() {
		return this.datePosted.getTime();
	}
	
	/**
	 * @return the annotationId
	 */
	public int getAnnotationId() {
		return annotationId;
	}
	/**
	 * @param ticketId the ticketId to set
	 */
	public void setAnnotationId(int annotationId) {
		this.annotationId = annotationId;
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
	 * @return the authorName
	 */
	public String getAuthorUsername() {
		return authorUsername;
	}

	/**
	 * @param authorName the authorName to set
	 */
	public void setAuthorUsername(String authorUsername) {
		this.authorUsername = authorUsername;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
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
	
	
}
