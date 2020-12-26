/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * The interface used to define the conflict behavior. It will be used throughout the program for conflicts.
 * 
 * @author Bilal Mohamad
 */
public interface Conflict {

	/**
	 * Checks for any conflict within the Activity.
	 * 
	 * @param possibleConflictingActivity 	Activity being checked if it conflicts with another Activity.
	 * @throws ConflictException			Exception thrown when there is a conflict between Activities.
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
}
