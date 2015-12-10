/*
 * Punchcard-Plus tracks time spent working by multiple users.
 * Copyright (C) 2015 Jonathan Thomas
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package punchcard.data;

import java.util.Calendar;

/**
 * The Workday class stores a continuous instance of work, with a defined start and end time.
 * The beginning and end times can be retured as Date objects.
 * @author Jonathan Thomas
 *
 */
public class Workday implements Comparable<Workday>{
	private Calendar begin;
	private Calendar end;
	
	/**
	 * Create a new Workday with the given beginning and ending times.
	 * @param begin Time the workday started.
	 * @param end Time the workday ended.
	 */
	public Workday(Calendar begin, Calendar end) {
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
	 * @return Calendar when the workday started.
	 */
	public Calendar getBegin() {
		return begin;
	}
	
	/**
	 * Set the start point for the workday.
	 * @param begin Calendar when the workday started.
	 */
	public void setBegin(Calendar begin) {
		this.begin = begin;
	}
	
	/**
	 * Get the ending time of the workday.
	 * @return Calendar when the workday ended.
	 */
	public Calendar getEnd() {
		return end;
	}
	
	/**
	 * Set the end point for the workday.
	 * @param end Calendar when the workday ended.
	 */
	public void setEnd(Calendar end) {
		this.end = end;
	}
	
	/**
	 * Generate a String representation of this workday.
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Workday:\n\t");
		builder.append(begin.getTimeInMillis() + "\n\t");
		builder.append(end.getTimeInMillis());
		
		return builder.toString();
	}
	
	/**
	 * Create an exact copy of this object.
	 */
	public Workday clone() {
		return new Workday(this.begin, this.end);
	}

	/**
	 * Compare this object to another.
	 * @param Object to compare to.
	 * @return Positive if greater than, negative if less than, zero if equal.
	 */
	@Override
	public int compareTo(Workday o) {
		return (int) (this.begin.getTimeInMillis() - o.getBegin().getTimeInMillis());
	}
}
