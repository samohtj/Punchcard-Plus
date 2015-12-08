package punchcard;

import java.util.Date;

/**
 * The Workday class stores a continuous instance of work, with a defined start and end time.
 * The beginning and end times can be retured as Date objects.
 * @author Jonathan Thomas
 *
 */
public class Workday {
	private Date begin;
	private Date end;
	
	/**
	 * Create a new Workday with the given beginning and ending times.
	 * @param begin Time the workday started.
	 * @param end Time the workday ended.
	 */
	public Workday(Date begin, Date end) {
		this.begin = begin;
		this.end = end;
	}
	
	/**
	 * Create an empty workday.
	 */
	public Workday() {
	}

	/**
	 * Get the beginning time of the workday.
	 * @return Date when the workday started.
	 */
	public Date getBegin() {
		return begin;
	}
	
	/**
	 * Set the start point for the workday.
	 * @param begin Date when the workday started.
	 */
	public void setBegin(Date begin) {
		this.begin = begin;
	}
	
	/**
	 * Get the ending time of the workday.
	 * @return Date when the workday ended.
	 */
	public Date getEnd() {
		return end;
	}
	
	/**
	 * Set the end point for the workday.
	 * @param end Date when the workday ended.
	 */
	public void setEnd(Date end) {
		this.end = end;
	}
}
