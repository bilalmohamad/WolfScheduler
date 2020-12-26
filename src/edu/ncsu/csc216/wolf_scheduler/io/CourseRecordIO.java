package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_scheduler.course.Course;

/**
 * Reads Course records from text files.  
 * 
 * @author Sarah Heckman, Bilal Mohamad
 */
public class CourseRecordIO {

    /**
     * Reads course records from a file and generates a list of valid Courses.  Any invalid
     * Courses are ignored.  If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName file to read Course records from
     * @return a list of valid Courses
     * @throws FileNotFoundException if the file cannot be found or read
     */
	public static ArrayList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
	    Scanner fileReader = new Scanner(new FileInputStream(fileName));
	    ArrayList<Course> courses = new ArrayList<Course>();
	    while (fileReader.hasNextLine()) {
	        try {
	            Course course = readCourse(fileReader.nextLine());
	            boolean duplicate = false;
	            for (int i = 0; i < courses.size(); i++) {
	                Course c = courses.get(i);
	                if (course.getName().equals(c.getName()) &&
	                        course.getSection().equals(c.getSection())) {
	                    //it's a duplicate
	                    duplicate = true;
	                }
	            }
	            if (!duplicate) {
	                courses.add(course);
	            }
	        } 
	        catch (IllegalArgumentException e) {
	            //skip the line
	        }
	    }
	    fileReader.close();
	    return courses;
	}

	
    /**
     * Helper method for readCourseRecords to ignore any invalid Courses.
     * @param fileName file to read Course records from
     * @return null to ignore invalid Courses
     * @throws FileNotFoundException if the file cannot be found or read
     */
	private static Course readCourse(String currentLine) throws FileNotFoundException {
    	Scanner line = new Scanner(currentLine);
    	Course c = null;
    	line.useDelimiter(",");
    	
    	while(line.hasNext()) {
    		try {
    			String name = line.next();
    			String title = line.next();
    			String section = line.next();
    			int credits = line.nextInt();
    			String instructorId = line.next();
    			String meetingDays = line.next();
    			
    			if (meetingDays.contains("A") && !line.hasNextInt()) {
    				c = new Course (name, title, section, credits, instructorId, meetingDays);
    			}
    			else {
    				int startTime = line.nextInt();
    				int endTime = line.nextInt();
    				c = new Course (name, title, section, credits, instructorId, meetingDays, startTime, endTime);
    			}
    			
    			line.close();
    			return c;
			}
    		catch (NoSuchElementException e) {
    			line.close();
    			throw new IllegalArgumentException();
    		}
    	}
    	line.close();
    	return c;
	}

}