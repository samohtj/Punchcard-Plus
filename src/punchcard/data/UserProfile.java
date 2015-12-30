/*
 * Punchcard Plus
 * A timekeeping program for employers and emplyees. Mostly just a fun project.
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
import java.util.ArrayList;

public class UserProfile implements Serializable {
	private static final long serialVersionUID = -4938056830808518961L;

	private String username = "<no username>";
	private Password password;

	private ArrayList<PaymentPeriod> periods;

	public UserProfile() {

	}

	/**
	 * Create a new user profile with the given infomraiton.
	 * @param username String username to add.
	 * @param password Password object to add.
	 */
	public UserProfile(String username, Password password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	/**
	 * Add a payment period to the list.
	 * @param period PaymentPeriod to add.
	 */
	public void addPaymentPeriod(PaymentPeriod period) {
		periods.add(period);
	}

	/**
	 * Return a copy of the list of paymentPeriods.
	 * @return ArrayList containing all the payment periods.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<PaymentPeriod> getAllPaymentPeriods() {
		return (ArrayList<PaymentPeriod>) periods.clone();
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public Password getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(Password password) {
		this.password = password;
	}

}
