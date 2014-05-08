/**
 * 
 */
package workticket;

import java.util.ArrayList;

import db.TicketDTO;
import db.AnnotationDTO;
import db.WorkTicketDAO;

/**
 * @author 
 *
 */
public class TicketHelper {
	private TicketDTO ticket;
	private AnnotationDTO latestAnnotation;
	private ArrayList<AnnotationDTO> annotations;
	private Boolean hasAnnotations;
	
	/**
	 * 
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
	}
	
	/**
	 * 
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
	 * 
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
	 * @return the ticket
	 */
	public TicketDTO getTicket() {
		return ticket;
	}
	/**
	 * @param ticket the ticket to set
	 */
	public void setTicket(TicketDTO ticket) {
		this.ticket = ticket;
	}
	/**
	 * @return the lastestAnnotation
	 */
	public AnnotationDTO getLatestAnnotation() {
		return latestAnnotation;
	}
	/**
	 * @param latestAnnotation the lastestAnnotation to set
	 */
	public void setLastestAnnotation(AnnotationDTO latestAnnotation) {
		this.latestAnnotation = latestAnnotation;
	}
	/**
	 * @return the annotations
	 */
	public ArrayList<AnnotationDTO> getAnnotations() {
		return annotations;
	}
	/**
	 * @param annotations the annotations to set
	 */
	public void setAnnotations(ArrayList<AnnotationDTO> annotations) {
		this.annotations = annotations;
	}

	/**
	 * @return the hasAnnotations
	 */
	public Boolean getHasAnnotations() {
		return hasAnnotations;
	}

	/**
	 * @param hasAnnotations the hasAnnotations to set
	 */
	public void setHasAnnotations(Boolean hasAnnotations) {
		this.hasAnnotations = hasAnnotations;
	}
	
	
}
