package workticket;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;
/**
 * Test of the TicketHelper object.
 * @author Team Joey
 *
 */

public class TicketHelperTest extends TestCase {
	
	public void testTicketHelper(){
		/** Test Defaults */
		TicketHelper instance = new TicketHelper(0);
		assertEquals("Ticket ID",0,instance.getTicket());
		assertEquals("Annotations",0,instance.getAnnotations());
		assertEquals("Has Annotations",0,instance.getHasAnnotations());
		assertEquals("Latest Annotations","",instance.getLatestAnnotation());
		
		TicketHelper instance1 = new TicketHelper(1,3,4,1);
		assertEquals("Ticket ID",1,instance.getTicket());
		assertEquals("Annotations",3,instance.getAnnotations());
		assertEquals("Has Annotations",4,instance.getHasAnnotations());
		assertEquals("Latest Annotations",1,instance.getLatestAnnotation());
	}
	
	public void testSaveNewticket(){
		TicketHelper instance2 = new TicketHelper(0);
		TicketHelper.saveNewTicket("Broken Macbook", "Customer's Macbook is on fire");
		assertEquals("Ticket Title","Broken Macbook",instance2.getTicket());
		
	}
}
