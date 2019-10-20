/**
 * This class creates the event object that's stored in the database table
 * @author Aymeric, Irene, Surbhi
 *
 */
public class Event {
	private String title;
	private String startTime;
	private String endTime;
	private int priority;
	private String info = "None.";
	private String date;
	private int id;

	public Event() {
		this.title = "";
		this.startTime = "";
		this.endTime = "";
		this.priority = 0;
		this.date="";
		
		
		


	}
	//constructor
	public Event(String title, String startTime, String endTime, int priority) {
		this.title = title;
		this.startTime = startTime;
		this.endTime = endTime;
		this.priority = priority;
		this.date="";

	}
	/**
	 * Sets ID
	 * @param id
	 */
	public void setID(int id) {
		this.id = id;
	}
	/**
	 * setsTitle
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * sets start time
	 * @param startTime
	 */
	public void setstartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * sets end time
	 * @param endTime
	 */
	public void setendTime(String endTime) {
		this.endTime = endTime;
	}
	/**
	 * Sets priority
	 * @param priority
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}
	/**
	 * sets description
	 * @param info
	 */
	public void setInfo(String info) {
		this.info = info;
	}
	/**
	 * sets date of event
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * gets ID
	 * @return Id
	 */
	public int getID() {
		return id;
	}
	/**
	 * gets date of event
	 * @return date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * gets event title or event name
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * gets start time
	 * @return start time
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * gets end time
	 * @return end time
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * gets description
	 * @return event description
	 */
	public String getDescription() {
		return info;
	}
	/**
	 * gets the event priority
	 * @return priority
	 */
	public int getPriority() {
		return priority;
	}
	/**
	 * For testing
	 * @param other
	 * @return
	 */
	public boolean equals(Event other) {
		if (other ==null)
			return false;
		if (other.getClass()==getClass()) {
			Event e = (Event) other;
			return (e.title.equals(title))&&
			(e.startTime.equals(startTime))&&(e.endTime.equals(endTime)&&(e.priority==priority));	
		}
		return false;
	}


}