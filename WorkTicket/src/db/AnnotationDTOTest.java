package db;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Calendar;

import org.junit.Test;
/**
 * Test Annotation class - Tests the creation of an annotation object with an id, 
 * ticket ID Author name, and annotation text
 * @author TeamJoey
 *
 */

public class AnnotationDTOTest {

	/** 
	 * Test to see if all parts of the Annotation constructor are working
	 */
	
	@Test
	public void testAnnotationDTO() {
		/** Test defaults */
		AnnotationDTO instance1 = new AnnotationDTO();
		assertEquals("Annotation ID",0,instance1.getAnnotationId());
		assertEquals("Annotation's Ticket ID",0,instance1.getTicketId());
		assertEquals("Annotation Author Name","",instance1.getAuthorUsername());
		assertEquals("Annotation's Text","",instance1.getText());
		//System.out.println("Post Date is " + instance1.getDatePosted());//bug testing
		assertTrue(instance1.getDatePosted() !=null);

		
		/** Test all parameters being set */
			// Need an SQL date for date parameter
			Date tempDate = new Date();
			long longTime = tempDate.getTime();
			java.sql.Date instDatePost = new java.sql.Date(longTime);
			//End of temp parameter creation
		AnnotationDTO instance2 = new AnnotationDTO(143, 33, "Bill Williams", "Lord help us, we have a Macbook Air on fire", instDatePost);
		assertEquals("Annotation ID",143,instance2.getAnnotationId());
		assertEquals("Annotation's Ticket ID",33,instance2.getTicketId());
		assertEquals("Annotation Author Name","Bill Williams",instance2.getAuthorUsername());
		assertEquals("Annotation's Text","Lord help us, we have a Macbook Air on fire",instance2.getText());
		GregorianCalendar testDatePosted = instance2.getDatePosted();
		Date endDate = testDatePosted.getTime();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
		//System.out.println("Post Date is " + testDatePosted.getTime());//bug testing
		//System.out.println("Post Date is " + ft.format(endDate));//bug testing
		assertEquals("Annotation's Date Posted","2014-05-10",ft.format(endDate));//Put in current date for it to pass uses the Temp param above

	}

	/**Test that the set annotation ID method works */
	public void testSetAnnotationId() {
		AnnotationDTO instance3 = new AnnotationDTO();
		instance3.setAnnotationId(12);
		assertEquals("Annotation ID",12,instance3.getAnnotationId());
	}

	/**Test that the set Ticket ID method works */
	public void testSetTicketId() {
		AnnotationDTO instance4 = new AnnotationDTO();
		instance4.setTicketId(54);
		assertEquals("Annotation's Ticket ID",54,instance4.getTicketId());
	}

	/**Test that the set Author method works */
	public void testSetAuthorName() {
		AnnotationDTO instance5 = new AnnotationDTO();
		instance5.setAuthorUsername("Paul Butter");
		assertEquals("Annotation Author Name","Paul Butter",instance5.getAuthorUsername());
	}

	/**Test that the set text method works */
	public void testSetText() {
		AnnotationDTO instance6 = new AnnotationDTO();
		instance6.setText("Why won't the computer turn on?");
		assertEquals("Annotation's Text","Why won't the computer turn on?",instance6.getText());
	}

	/**Test that the set date posted works works */
	public void TestSetDatePosted(){
		AnnotationDTO instance7 = new AnnotationDTO();
		instance7.setDatePosted(new GregorianCalendar(2012, 05, 13));
		GregorianCalendar testDatePosted = instance7.getDatePosted();
		Date endDate = testDatePosted.getTime();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
		//System.out.println("Post Date is " + ft.format(endDate));//bug testing
		assertEquals("Annotation's Date Posted","2012-05-13",ft.format(endDate));
	}
}
