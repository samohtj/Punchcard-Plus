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

/**
 * A collection of all users tracked by the program, broken into different groups.
 * This class tracks all the users that are registered in the program, and breaks
 * them into two groups: workers and employers. Employers have admin privileges
 * and can add and remove users and edit their work history.
 * @author Jonathan Thomas
 *
 */
public class Userbase {
	
	private UserGroup groupNormalUsers = new UserGroup("Workers");
	private UserGroup groupAdminUsers = new UserGroup("Employers");
	
	public Userbase() {}
	
	/**
	 * Add a user to the worker group.
	 * @param user
	 */
	public void addWorker(UserProfile user) {
		groupNormalUsers.addUser(user);
	}
	
	/**
	 * Add a user to the employer group.
	 * @param user
	 */
	public void addEmployer(UserProfile user) {
		groupAdminUsers.addUser(user);
	}
	
	/**
	 * Remove a user from the worker group.
	 * @param user User to remove.
	 * @return True if the user was found in that group.
	 */
	public boolean removeWorker(UserProfile user) {
		return groupNormalUsers.removeUser(user);
	}
	
	/**
	 * Remove a user from the employer group.
	 * @param user User to remove.
	 * @return True if the user was found in that group.
	 */
	public boolean removeEmployer(UserProfile user) {
		return groupAdminUsers.removeUser(user);
	}
	
	/**
	 * Tell whether a user is in the worker group.
	 * @param user User to check.
	 * @return True if the user is in that group.
	 */
	public boolean isWorker(UserProfile user) {
		return groupNormalUsers.contains(user);
	}
	
	/**
	 * Tell whether a user is in the employer group.
	 * @param user User to check.
	 * @return True if the user is in that group.
	 */
	public boolean isEmployer(UserProfile user) {
		return groupAdminUsers.contains(user);
	}

}
