/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Implements a custom Exception that can be thrown.
 * If there is a message to be displayed, then it will display the message;
 * otherwise, it will display the default message.
 * 
 * @author Bilal Mohamad
 *
 */
public class ConflictException extends Exception {

	/** ID used for serialization */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Exception when the conflict thrown contains message
	 * @param message Message to be displayed when the conflict is thrown
	 */
	public ConflictException(String message) {
		super(message);
	}

	/**
	 * Default exception when the conflict thrown contains no message
	 */
	public ConflictException() {
		super("Schedule conflict.");
	}

}
