/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.ConflictException;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;

/**
 *  WolfScheduler reads in and stores as a list all of the Course records stored in a file.
 *  Additionally, WolfScheduler creates a schedule for the current user (a student) and provides
 *  functionality for naming the schedule, adding a Course to the schedule,
 *  removing a Course from the schedule, resetting the schedule.
 *  
 * @author Bilal Mohamad
 *
 */
public class WolfScheduler {
	
	/** Current title for the schedule */
	private String title;
	/** ArrayList for all the courses */
	private ArrayList<Course> courseCatalog;
	/** ArrayList for all the courses currently in the schedule*/
	private ArrayList<Activity> scheduleCatalog;

	
	/**
	 * Constructor for WolfScheduler
	 * @param fileName name of file
	 * @throws IllegalArgumentException if the file is not found
	 */
	public WolfScheduler(String fileName) {
		try {
			courseCatalog = CourseRecordIO.readCourseRecords(fileName);
		}
		catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Cannot find file.");
		}
		
		scheduleCatalog = new ArrayList<Activity>();
		title = "My Schedule";
	}

	
	/**
	 * Retrieves a course from course catalog by using name and section to check if it is a valid course.
	 * @param name  string for the name of the course
	 * @param section string for the section of the course
	 * @return the course if the course is valid otherwise,
	 * 			null
	 */
	public Course getCourseFromCatalog(String name, String section) {
		
		for (int i = 0; i < courseCatalog.size(); i++) {
			Course c = courseCatalog.get(i);
			if (c.getName().equals(name) && c.getSection().equals(section)) {
				return c;
			}
		}
		return null;
		
	}

	
	/**
	 * Adds a course to the schedule catalog if it is a valid course.
	 * 
	 * @param name  string for the name of the course
	 * @param section string for the section of the course
	 * @return false - if the course is null otherwise,
	 * 			true
	 * @throws IllegalArgumentException the user is already enrolled in the course or there is a conflict
	 */
	public boolean addCourse(String name, String section) {
		
		Course courseAdded =  getCourseFromCatalog(name, section);
		
		for (int i = 0; i < scheduleCatalog.size(); i++) {
			if (scheduleCatalog.get(i).isDuplicate(courseAdded)) {
				throw new IllegalArgumentException("You are already enrolled in " + courseAdded.getName());
			}
			
			try {
				courseAdded.checkConflict(scheduleCatalog.get(i));
			}
			catch (ConflictException e) {
				throw new IllegalArgumentException("The course cannot be added due to a conflict.");
			}
		}
		
		if (courseAdded == null) {
			return false;
		}
		
		scheduleCatalog.add(courseAdded);
		return true;
	}
	
	
	/**
	 * Adds an Event to the schedule catalog if it is a valid event.
	 * 
	 * @param title  		string for the title of the event
	 * @param meetingDays 	string for the meetingDays of the event
	 * @param startTime 	int value for the startTime of the event
	 * @param endTime 		int value for the endTime of the event
	 * @param weeklyRepeat 	int value for the weeklyRepeat of the event
	 * @param details 		string for the details of the event
	 * 
	 * @throws IllegalArgumentException the user is already enrolled in the course or there is a conflict
	 */
	public void addEvent(String title, String meetingDays, int startTime, int endTime, int weeklyRepeat, String details) {
		
		Event eventAdded = new Event(title, meetingDays, startTime, endTime, weeklyRepeat, details);
		
		for (int i = 0; i < scheduleCatalog.size(); i++) {
			if (scheduleCatalog.get(i).isDuplicate(eventAdded) || eventAdded == null) {
				throw new IllegalArgumentException("You have already created an event called " + eventAdded.getTitle());
			}
				
			try {
				eventAdded.checkConflict(scheduleCatalog.get(i));
			}
			catch (ConflictException e) {
				throw new IllegalArgumentException("The event cannot be added due to a conflict.");
			}
		}
		
		scheduleCatalog.add(eventAdded);
	}

	
	/**
	 * Removes a course from the schedule catalog.
	 * 
	 * @param idx int value of the index of the activity
	 * 
	 * @return true - if the activity exists in the schedule catalog and can be removed.
	 * 			Otherwise, false
	 */
	public boolean removeActivity(int idx) {
		
		if(scheduleCatalog.size() > idx)
		{
			scheduleCatalog.remove(idx);
			return true;
		}
		return false;
	}

	
	/**
	 * Resets the schedule.
	 */
	public void resetSchedule() {
		ArrayList<Activity> reset = new ArrayList<Activity>();
		scheduleCatalog = reset;
		
	}

	
	/**
	 * Retrieves a 2D array of the course catalog.
	 * @return A 2D array of the course catalog
	 */
	public String[][] getCourseCatalog() {
		String[][] catalog = new String [courseCatalog.size()][4];
		
		for (int i = 0; i < courseCatalog.size(); i++) {
			Activity a = courseCatalog.get(i);
			catalog[i][0] = a.getShortDisplayArray()[0];
			catalog[i][1] = a.getShortDisplayArray()[1];
			catalog[i][2] = a.getShortDisplayArray()[2];
			catalog[i][3] = a.getShortDisplayArray()[3];
		}
		
		return catalog;
	}

	
	/**
	 * Retrieves a 2D array of the scheduled activities
	 * @return A 2D array of the scheduled activities
	 */
	public String[][] getScheduledActivities() {
		String[][] scheduledCourses = new String [scheduleCatalog.size()][4];
		for (int i = 0; i < scheduleCatalog.size(); i++) {
			Activity a = scheduleCatalog.get(i);
			scheduledCourses[i][0] = a.getShortDisplayArray()[0];
			scheduledCourses[i][1] = a.getShortDisplayArray()[1];
			scheduledCourses[i][2] = a.getShortDisplayArray()[2];
			scheduledCourses[i][3] = a.getShortDisplayArray()[3];
			
		}
		return scheduledCourses;
	}

	
	/**
	 * Retrieves a 2D array of the full list of scheduled activities.
	 * @return A 2D array of the full list of scheduled activities
	 */
	public String[][] getFullScheduledActivities() {
		
		String[][] full = new String [scheduleCatalog.size()][7];
		
		for (int i = 0; i < scheduleCatalog.size(); i++) {
			Activity a = scheduleCatalog.get(i);
			full[i][0] = a.getLongDisplayArray()[0];
			full[i][1] = a.getLongDisplayArray()[1];
			full[i][2] = a.getLongDisplayArray()[2];
			full[i][3] = a.getLongDisplayArray()[3];
			full[i][4] = a.getLongDisplayArray()[4];
			full[i][5] = a.getLongDisplayArray()[5];
			full[i][6] = a.getLongDisplayArray()[6];
			
		}
		
		return full;
	}

	/**
	 * Sets the title of the schedule.
	 * @param title string for the title of the schedule
	 * @throws IllegalArgumentException if the title is null
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		
		this.title = title;		
	}

	/**
	 * Retrieves the title of the schedule.
	 * @return the title of the schedule
	 */
	public String getTitle() {
		return title;
	}

	
	/**
	 * Exports the schedule to save the user's schedule.
	 * @param fileName string for the name of the file
	 * @throws IllegalArgumentException if the file cannot be saved
	 */
	public void exportSchedule(String fileName) {
		try {
			ActivityRecordIO.writeActivityRecords(fileName, scheduleCatalog);
		}
		catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
	}	
	
}
