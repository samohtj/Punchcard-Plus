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

/**
 * A simple user account class. Stores a username and password.
 * @author Jonathan
 *
 */
public class UserProfile implements Serializable {
	private static final long serialVersionUID = -4938056830808518961L;

	private String username = "<no username>";
	private Password password;

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
