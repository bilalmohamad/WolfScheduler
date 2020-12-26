package edu.ncsu.csc216.wolf_scheduler.course;


/**
 * Abstract class containing shared methods and information needed for the Course class and the Event class.
 * 
 * @author Bilal Mohamad
 *
 */
public abstract class Activity implements Conflict {

	/** Activity's title. */
	private String title;
	/** Activity's meeting days */
	private String meetingDays;
	/** Activity's starting time */
	private int startTime;
	/** Activity's ending time */
	private int endTime;
	/** Upper time */
	public static final int UPPER_TIME = 2359;
	/** Upper hour */
	public static final int UPPER_HOUR = 59;
	/** Hour Converter */
	public static final int HOUR_CONVERTER = 100;
	/** Afternoon Converter */
	public static final int AFTERNOON = 1259;
	/** Afternoon Cut-Off */
	public static final int AFTERNOON_CUTOFF = 1200;

	
	/**
	 * Constructs an Activity object with values for all fields.
	 * 
	 * @param title			title of Activity
	 * @param meetingDays	meeting days for Activity
	 * @param startTime		starting time for Activity
	 * @param endTime		ending time for Activity
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		setTitle(title);
		setMeetingDays(meetingDays);
		setActivityTime(startTime, endTime);
	}

	
	/**
	 * Returns the Activity's title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Activity's title.
	 * 
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		if (title == null || title.equals("")) {
			throw new IllegalArgumentException();
		}
		this.title = title;
	}

	/**
	 * Returns the Activity's meeting days.
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Sets the Activity's meeting days.
	 * 
	 * @param meetingDays the meetingDays to set
	 */
	public void setMeetingDays(String meetingDays) {
		this.meetingDays = meetingDays;
	}

	/**
	 * Returns the Activity's start time.
	 * 
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Activity's end time.
	 * 
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets the Activity's start and end time
	 * 
	 * @param startTime the startTime to set
	 * @param endTime   the endTime to set
	 */
	public void setActivityTime(int startTime, int endTime) {
		if (startTime < 0 || startTime > UPPER_TIME) {
			throw new IllegalArgumentException();
		}
		if (endTime < 0 || endTime > UPPER_TIME) {
			throw new IllegalArgumentException();
		}
		if (endTime < startTime) {
			throw new IllegalArgumentException();
		}
		if (meetingDays.charAt(0) == 'A' && (startTime != 0 || endTime != 0)) {
			throw new IllegalArgumentException();
		}
		if (startTime % HOUR_CONVERTER > UPPER_HOUR) {
			throw new IllegalArgumentException();
		}
		if (endTime % HOUR_CONVERTER > UPPER_HOUR) {
			throw new IllegalArgumentException();
		}
	
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Returns the Activity's meeting string.
	 * 
	 * @return the meeting string
	 */
	public String getMeetingString() {
		if (meetingDays.charAt(0) == 'A') {
			return "Arranged";
		}
	
		boolean isStartAfternoon = false;
		boolean isEndAfternoon = false;
		String s = meetingDays + " ";
	
		if (startTime >= AFTERNOON_CUTOFF) {
			isStartAfternoon = true;
		}
		if (startTime > AFTERNOON) {
			s += (startTime - AFTERNOON_CUTOFF) / HOUR_CONVERTER;
		} else {
			s += startTime / HOUR_CONVERTER;
		}
	
		s += ":";
		s += startTime % HOUR_CONVERTER;
	
		if (startTime % HOUR_CONVERTER == 0) {
			s += "0";
		}
	
		if (isStartAfternoon) {
			s += "PM";
		} else {
			s += "AM";
		}
	
		s += "-";
	
		if (endTime >= AFTERNOON_CUTOFF) {
			isEndAfternoon = true;
		}
		if (endTime > AFTERNOON) {
			s += (endTime - AFTERNOON_CUTOFF) / HOUR_CONVERTER;
		} else {
			s += endTime / HOUR_CONVERTER;
		}
	
		s += ":";
		s += endTime % HOUR_CONVERTER;
	
		if (endTime % HOUR_CONVERTER == 0) {
			s += "0";
		}
	
		if (isEndAfternoon) {
			s += "PM";
		} else {
			s += "AM";
		}
		return s;
	}
	
	
	/**
	 * Gives the short display of the array
	 * 
	 * @return String array of short Activity information
	 */
	public abstract String[] getShortDisplayArray();
	
	/**
	 * Gives the long display of the array
	 * 
	 * @return String array of full Activity information
	 */
	public abstract String[] getLongDisplayArray();
	
	/**
	 * Checks if the activity is a duplicate
	 * 
	 * @param 	activity the activity being checked
	 * @return 	true if the activity is a duplicate
	 * 			false if the activity is not a duplicate
	 */
	public abstract boolean isDuplicate(Activity activity);




	/**
	 * Checks for any conflict within the Activity.
	 * 
	 * @param possibleConflictingActivity 	Activity being checked if it conflicts with another Activity.
	 * @throws ConflictException			Exception thrown when there is a conflict between Activities.
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		
		for (int i = 0; i < this.getMeetingDays().length(); i++) {
			
			char thisCurrent = this.getMeetingDays().charAt(i);
			
			for(int j = 0; j < possibleConflictingActivity.getMeetingDays().length(); j++) {
				
				char otherCurrent = possibleConflictingActivity.getMeetingDays().charAt(j);
				
				if ((thisCurrent == otherCurrent) && (thisCurrent != 'A' || otherCurrent != 'A')
						&& ((possibleConflictingActivity.getStartTime() >= this.getStartTime() 
						&& possibleConflictingActivity.getStartTime() <= this.getEndTime())
						|| possibleConflictingActivity.getEndTime() >= this.getStartTime() 
						&& possibleConflictingActivity.getEndTime() <= this.getEndTime()
						|| ((this.getStartTime() >= possibleConflictingActivity.getStartTime()
						&& this.getStartTime() <= possibleConflictingActivity.getEndTime())
						|| this.getEndTime() >= possibleConflictingActivity.getStartTime()
						&& this.getEndTime() <= possibleConflictingActivity.getEndTime()))) {
							throw new ConflictException();
				}
			}
		}
	}


	/**
	 * Method containing an algorithm for making hashCode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}





	/**
	 * Method used for comparing equality between two objects
	 * 
	 * @param obj 		The object that is being compared with.
	 * @return true 	if the objects being compared are the same
	 * 			false	if the objects being compared are not the same
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	

}