/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Object containing all the information for a Course within the WolfScheduler program such as:
 * name, title, section, credits, instructorId, meetingDays, startTime, and endTime.
 * 
 * @author Bilal Mohamad
 *
 */
public class Course extends Activity {

	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Section Length */
	public static final int SECTION_LENGTH = 3;
	/** Max Name Length */
	public static final int MAX_NAME_LENGTH = 6;
	/** Min Name Length */
	public static final int MIN_NAME_LENGTH = 4;
	/** Max Credits */
	public static final int MAX_CREDITS = 5;
	/** Min Credits */
	public static final int MIN_CREDITS = 1;
	/** Array Length of Short Display */
	private static final int SHORT_DISPLAY = 4;
	/** Array Length of Long Display */
	private static final int LONG_DISPLAY = 7;
	
	
	/**
	 * Constructs a Course object with values for all fields.
	 * 
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays  meeting days for Course as series of chars
	 * @param startTime    start time for Course
	 * @param endTime      end time for Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
			int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId,
	 * and meetingDays for courses that are arranged.
	 * 
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays  meeting days for Course as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}

	/**
	 * Returns the Course's name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name. If the name is null, has a length less than 4 or
	 * greater than 6, an IllegalArgumentException is thrown.
	 * 
	 * @param name the name to set
	 * @throws IllegalArgumentException if name is null or length is less than 4 or
	 *                                  greater than 6
	 */
	private void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException();
		}
		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException();
		}
		this.name = name;
	}

	/**
	 * Returns the Course's section.
	 * 
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Course's section.
	 * 
	 * @param section the section to set
	 */
	public void setSection(String section) {
		if (section == null) {
			throw new IllegalArgumentException();
		}
		if (section.length() != 3) {
			throw new IllegalArgumentException();
		}

		this.section = section;
	}

	/**
	 * Returns the Course's credits.
	 * 
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the Course's credits.
	 * 
	 * @param credits the credits to set
	 */
	public void setCredits(int credits) {
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException();
		}
		this.credits = credits;
	}

	/**
	 * Returns the Course's instructor ID.
	 * 
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Course's instructor ID.
	 * 
	 * @param instructorId the instructorId to set
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId == null || instructorId.equals("")) {
			throw new IllegalArgumentException();
		}
		this.instructorId = instructorId;
	}

	
	/**
	 * Sets the Activity's meeting days.
	 * 
	 * @param meetingDays the meetingDays to set
	 */
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null || meetingDays.equals("")) {
			throw new IllegalArgumentException();
		}
	
		for (int i = 0; i < meetingDays.length(); i++) {
			char letter = meetingDays.charAt(i);
			if (!(letter == 'M' || letter == 'T' || letter == 'W' || letter == 'H' || letter == 'F'
					|| (letter == 'A' && meetingDays.length() == 1))) {
				throw new IllegalArgumentException();
			}
	
		}
		super.setMeetingDays(meetingDays);
	}
	

	/**
	 * Method containing an algorithm for making hashCode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	/**
	 * Method used for comparing equality between two objects
	 * 
	 * @param obj 		The object that is being compared with.
	 * @return true 	if the objects being compared are the same
	 * 			false	if the objects being compared are not the same
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	

	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Course
	 */
	public String toString() {
		if (getMeetingDays().equals("A")) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays() + ","
				+ getStartTime() + "," + getEndTime();
	}

	/**
	 * Returns an array of length 4 containing the Course name, section, title, and meeting days string
	 * 
	 * @return String array of Course information
	 */
	public String[] getShortDisplayArray() {
		String[] s = new String[SHORT_DISPLAY];
		s[0] = getName();
		s[1] = getSection();
		s[2] = getTitle();
		s[3] = super.getMeetingString();
		return s;
	}

	/**
	 * Returns an array of length 7 containing the Course name, section, title, credits, instructorId, meeting days string, 
	 * empty string (for a field that Event will have that Course does not).
	 * 
	 * @return String array of full Course information
	 */
	public String[] getLongDisplayArray() {
		String[] s = new String[LONG_DISPLAY];
		s[0] = getName();
		s[1] = getSection();
		s[2] = getTitle();
		s[3] = getCredits() + "";
		s[4] = getInstructorId();
		s[5] = super.getMeetingString();
		s[6] = "";
		return s;
	}
	
	
	/**
	 * Checks if the activity is a duplicate
	 * 
	 * @param activity	the activity being checked if it is a duplicate
	 * 
	 * @return true if the activity is a duplicate
	 * 			false if the activity is not a duplicate
	 */
	public boolean isDuplicate (Activity activity) {
		if (this == activity) {
			return true;
		}
		if (getClass() != activity.getClass()) {
			return false;
		}
		
		Course c = (Course) activity;
		if (getName().equals(c.getName())) {
			return true;
		}
		return false;
	}

}
