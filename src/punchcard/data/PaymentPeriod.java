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

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedList;

public class PaymentPeriod implements Serializable {
	private static final long serialVersionUID = -5068081420841810501L;
	
	private Calendar startDate;
	private Calendar endDate;
	private long duration;
	
	private LinkedList<Workday> workdays = new LinkedList<>();
	
	public PaymentPeriod() {
		
	}
	
	/**
	 * Generate a new payment period from a list of workdays.
	 * @param workdays
	 */
	public PaymentPeriod(LinkedList<Workday> workdays) {
		this.workdays = new LinkedList<Workday>(workdays);
		
		if (workdays.size() > 0) {
			this.startDate = workdays.get(0).getBegin();
			this.endDate = workdays.get(workdays.size() - 1).getEnd();
			this.duration = getDistanceBetween(this.startDate, this.endDate);
		}
	}
	
	/**
	 * Calculate the difference in milliseconds between two dates.
	 * @param start
	 * @param end
	 * @return long Number of milliseconds between two dates.
	 */
	public static long getDistanceBetween(Calendar start, Calendar end) {
		return end.getTimeInMillis() - start.getTimeInMillis();
	}
	
	/**
	 * Add a workday to the list.
	 * @param day Workday to add.
	 */
	public void addWorkday(Workday day) {
		insertWorkday(day);
	}
	
	/**
	 * Add a workday to the list, and then sort it so the list is in chronological order.
	 * @param day Workday to add.
	 */
	private void insertWorkday(Workday day) {
		workdays.add(day);
		Collections.sort(workdays);
	}

	/**
	 * Get the starting date of the payment period.
	 * @return the startDate
	 */
	public Calendar getStartDate() {
		return startDate;
	}
	
	/**
	 * Set the starting date of the period.
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * Get the ending date of the period.
	 * @return Calendar containing end date.
	 */
	public Calendar getEndDate() {
		return endDate;
	}
	
	/**
	 * Set the ending date of the payment period.
	 * @param endDate Calendar representation of the endDate to set.
	 */
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * Get the duration of the payment period.
	 * @return long representing the number of milliseconds. 
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * Get a copy of the workday set.
	 * @return LinkedList containing all the workdays.
	 */
	public LinkedList<Workday> getWorkdays() {
		return new LinkedList<Workday>(workdays);
	}

	/**
	 * Set the workday set to a specific list of workdays.
	 * @param workdays the workdays to set
	 */
	public void setWorkdays(LinkedList<Workday> workdays) {
		this.workdays = workdays;
	}
	
	public void printAllWorkdays() {
		System.out.println("Workdays:");
		for (Workday day: workdays) {
			System.out.println(day.toString());
		}
	}
	
	public static void main(String[] args) {
		try {
			System.out.println("Adding workdays, please wait.");
			Workday day1 = new Workday(Calendar.getInstance(), Calendar.getInstance());
			Thread.sleep(2000);
			Workday day2 = new Workday(Calendar.getInstance(), Calendar.getInstance());
			Thread.sleep(2000);
			Workday day3 = new Workday(Calendar.getInstance(), Calendar.getInstance());
			System.out.println("Done adding workdays.");
			
			PaymentPeriod period = new PaymentPeriod();
			period.addWorkday(day1);
			period.addWorkday(day2);
			period.addWorkday(day3);
			period.printAllWorkdays();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
