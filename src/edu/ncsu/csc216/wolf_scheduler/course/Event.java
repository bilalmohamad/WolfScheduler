/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Object containing all the information for an Event within the WolfScheduler program such as:
 * title, meetingDays, startTime, endTime, weeklyRepeat, and eventDetails.
 * 
 * @author Bilal Mohamad
 *
 */
public class Event extends Activity {

	/** Upper limit of weeks repeated */
	private static final int WEEK_REPEAT = 4;
	/** Array Length of Short Display */
	private static final int SHORT_DISPLAY = 4;
	/** Array Length of Long Display */
	private static final int LONG_DISPLAY = 7;
	/** Weekly Repeat of Event */
	private int weeklyRepeat;
	/** Details of Event*/
	private String eventDetails;
	
		
	/**
	 * Constructs an Event object with values for all fields.
	 * 
	 * @param title        title of Event
	 * @param meetingDays  meeting days for Event as series of chars
	 * @param startTime    start time for Event
	 * @param endTime      end time for Event
	 * @param weeklyRepeat weekly repeat of Event
	 * @param eventDetails details of Event
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, int weeklyRepeat, String eventDetails) {
		super(title, meetingDays, startTime, endTime);
		setWeeklyRepeat(weeklyRepeat);
		setEventDetails(eventDetails);
	}
	
	
	/**
	 * Returns the Event's weeklyRepeat.
	 * 
	 * @return the weeklyRepeat
	 */
	public int getWeeklyRepeat() {
		return weeklyRepeat;
	}
	
	
	/**
	 * Sets the Event's weeklyRepeat
	 * 
	 * @param weeklyRepeat the weeklyRepeat to set
	 */
	public void setWeeklyRepeat(int weeklyRepeat) {
		if (weeklyRepeat < 1 || weeklyRepeat > WEEK_REPEAT) {
			throw new IllegalArgumentException("Invalid weekly repeat");
		}
		this.weeklyRepeat = weeklyRepeat;
	}
	
	
	/**
	 * Returns the Event's eventDetails
	 * 
	 * @return the eventDetails
	 */
	public String getEventDetails() {
		return eventDetails;
	}
	
	
	/**
	 * Sets the Event's eventDetails
	 * 
	 * @param eventDetails the eventDetails to set
	 */
	public void setEventDetails(String eventDetails) {
		if (eventDetails == null) {
			throw new IllegalArgumentException("Invalid event details");
		}
		this.eventDetails = eventDetails;
	}




	/**
	 * Sets the Event's meeting string.
	 * 
	 * @param meetingDays the meeting days to set
	 */
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null || meetingDays.equals("")) {
			throw new IllegalArgumentException();
		}	
	
		for (int i = 0; i < meetingDays.length(); i++) {
			char letter = meetingDays.charAt(i);
			if (!(letter == 'M' || letter == 'T' || letter == 'W' || letter == 'H' || letter == 'F'
					 || letter == 'S' || letter == 'U')) {
				throw new IllegalArgumentException();
			}
	
		}
		super.setMeetingDays(meetingDays);
	}


	/**
	 * Returns the Event's meeting string.
	 * 
	 * @return the meeting string
	 */
	public String getMeetingString() {
		return super.getMeetingString() +  " (every " + weeklyRepeat + " weeks)";
	}


	/**
	 * Returns a comma separated value String of all Event fields.
	 * 
	 * @return String representation of Event
	 */
	public String toString() {
		return super.getTitle() + "," + super.getMeetingDays() + "," + super.getStartTime() + "," + super.getEndTime()
		 + "," + weeklyRepeat + "," + eventDetails;
	}


	/**
	 * Returns an array of length 4 containing an empty string, an empty string, title, and meeting days string.
	 * 
	 * @return String array of short Event information
	 */
	public String[] getShortDisplayArray() {
		String[] s = new String[SHORT_DISPLAY];
		s[0] = "";
		s[1] = "";
		s[2] = getTitle();
		s[3] = getMeetingString();
		return s;
	}

	
	/**
	 * Returns an array of length 7 containing an empty string, an empty string, title, an empty string, an empty string,
	 * the meeting days string, and the event details string.
	 * 
	 * @return String array of full Event information
	 */
	public String[] getLongDisplayArray() {
		String[] s = new String[LONG_DISPLAY];
		s[0] = "";
		s[1] = "";
		s[2] = getTitle();
		s[3] = "";
		s[4] = "";
		s[5] = getMeetingString();
		s[6] = getEventDetails();
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
		
		Event e = (Event) activity;
		if (getTitle().equals(e.getTitle())) {
			return true;
		}
		
		return false;
	}
}
