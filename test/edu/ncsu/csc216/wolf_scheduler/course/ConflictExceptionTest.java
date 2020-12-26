/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the ConflictException class to ensure the custom exception functions properly
 *
 *@author Bilal Mohamad
 */
public class ConflictExceptionTest {

	/**
	 * Test method for a custom thrown message
	 */
	@Test
	public void testConflictExceptionString() {
	    ConflictException ce = new ConflictException("Custom exception message");
	    assertEquals("Custom exception message", ce.getMessage());
	}

	/**
	 * Test method for the default thrown message
	 */
	@Test
	public void testConflictException() {
		ConflictException ce = new ConflictException();
		assertEquals("Schedule conflict.", ce.getMessage());
	}
}
