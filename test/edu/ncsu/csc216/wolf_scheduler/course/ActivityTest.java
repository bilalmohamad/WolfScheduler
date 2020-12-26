/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test the checkConflict() method in the Activity class.
 * Other methods were tested in other tests such as EventTest and CourseTest
 * 
 * @author Bilal Mohamad
 */
public class ActivityTest {

	/**
	 * Tests the checkConflict() method
	 */
	@Test
	public void testCheckConflict() {
	    Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "TH", 1330, 1445);
	    
	    try {
	        a1.checkConflict(a2);
	        a2.checkConflict(a1);
	        assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
	    } 
	    catch (ConflictException e) {
	        fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
	    }
	    
	    
	    //Updates a1 with the same meeting days and a start time that overlaps the end time of a2
	    a1.setMeetingDays("TH");
	    a1.setActivityTime(1445, 1530);
	    
	    try {
	        a1.checkConflict(a2);
	        a2.checkConflict(a1);
	        fail(); //ConflictException should have been thrown, but was not.
	    } 
	    catch (ConflictException e) {
	        //Check that the internal state didn't change during method call.
	        assertEquals("TH 2:45PM-3:30PM", a1.getMeetingString());
	        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
	    }
	    
	    
	    
	    //Changes startTime between the checked range
	    a1.setActivityTime(1400, 1500);
	    
	    try {
	        a1.checkConflict(a2);
	        a2.checkConflict(a1);
	        fail(); //ConflictException should have been thrown, but was not.
	    } 
	    catch (ConflictException e) {
	        assertEquals("TH 2:00PM-3:00PM", a1.getMeetingString());
	        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
	    }
	    
	    
	    //Changes endTime between the checked range
	    a1.setActivityTime(1200, 1400);
	    
	    try {
	        a1.checkConflict(a2);
	        a2.checkConflict(a1);
	        fail(); //ConflictException should have been thrown, but was not.
	    } 
	    catch (ConflictException e) {
	        assertEquals("TH 12:00PM-2:00PM", a1.getMeetingString());
	        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
	    }
	    
	    
	    //Checks for conflict on a single day
	    a1.setMeetingDays("T");
	    
	    try {
	        a1.checkConflict(a2);
	        a2.checkConflict(a1);
	        fail(); //ConflictException should have been thrown, but was not.
	    } 
	    catch (ConflictException e) {
	        assertEquals("T 12:00PM-2:00PM", a1.getMeetingString());
	        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
	    }
	    
	    
	    //Meeting days are arranged
	    a1.setMeetingDays("A");
	    
	    try {
	        a1.checkConflict(a2);
	        a2.checkConflict(a1);
	        assertEquals("Arranged", a1.getMeetingString());
	        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
	    } 
	    catch (ConflictException e) {
	        fail();
	    }
	    
	    a1.setMeetingDays("T");
	    a2.setMeetingDays("A");
	    
	    try {
	        a1.checkConflict(a2);
	        a2.checkConflict(a1);
	        assertEquals("T 12:00PM-2:00PM", a1.getMeetingString());
	        assertEquals("Arranged", a2.getMeetingString());
	    } 
	    catch (ConflictException e) {
	        fail();
	    }
	}
}
