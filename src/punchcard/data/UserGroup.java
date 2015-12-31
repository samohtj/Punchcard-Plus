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

/**
 * A groupd of UserProfiles. Users can be added and removed, and the whole group can be serialized 
 * to a file. The group has a name, which can't be edited once the object is created.
 * @author Jonathan Thomas
 *
 */
public class UserGroup implements Serializable {

	private static final long serialVersionUID = 1948603943168708609L;
	private ArrayList<UserProfile> users = new ArrayList<>();
	private String name = "";
	
	/**
	 * Create a new group with the given name.
	 * @param name Name to use.
	 */
	public UserGroup(String name) {
		this.name = name;
	}
	
	/**
	 * Create a new user group with the given name and set of users.
	 * @param name Name of the group.
	 * @param users ArrayList of user profiles to add.
	 */
	public UserGroup(String name, ArrayList<UserProfile> users) {
		this.name = name;
		
		for(UserProfile user: users) {
			this.users.add(user);
		}
	}
	
	/**
	 * Add a user to this list.
	 * @param user
	 */
	public void addUser(UserProfile user) {
		users.add(user);
	}
	
	/**
	 * Remove the specified UserProfile from this group.
	 * @param user User to remove.
	 * @return True if the user was found in the list.
	 */
	public boolean removeUser(UserProfile user) {
		return users.remove(user);
	}
	
	/**
	 * Get the name of this user group.
	 * @return A String copy of the name.
	 */
	public String getName() {
		return new String(name);
	}
	
	/**
	 * Tell whether or not the user is in this group.
	 * @param user User to check.
	 * @return True if that user is in this list.
	 */
	public boolean contains(UserProfile user) {
		return users.contains(user);
	}
	
	/**
	 * Return a copy of the user list.
	 * @return ArrayList containing UserProfile objects.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<UserProfile> getUserList() {
		return (ArrayList<UserProfile>) users.clone();
	}

}
