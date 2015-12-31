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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * UserbaseSaver is a class that enables saving a userbase object to a file and loading it back 
 * again. It saves and loads the userbase from a static location, but that location can be changed 
 * if necessary using the {@code changeSaveLocation} method. This class makes use of plain old Java
 * object serialization.
 * @author Jonathan Thomas
 *
 */
public class UserbaseSaver {
	
	private static File saveLocation = new File("userbasedata.dat");
	
	/**
	 * Save the given Userbase in the save file. 
	 * @param base Userbase to save.
	 */
	public void saveUserbase(Userbase base) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new DataOutputStream(new FileOutputStream(saveLocation)));
			
			out.writeObject(base);
			
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Load a Userbase from the save file.
	 * @return The stored Userbase object.
	 */
	public static Userbase loadUserbase() {
		Userbase toReturn = null;
		
			try {
				ObjectInputStream in = new ObjectInputStream(
						new DataInputStream(new FileInputStream(saveLocation)));
				
				toReturn = (Userbase) in.readObject();
				
				in.close();
			} catch (FileNotFoundException e) {
				System.out.println("ERROR!: The save file couldn't be found.");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("ERROR!: There was an error reading from the save file.");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				if (toReturn == null) {
					toReturn = new Userbase();
				}
			}
		
		return toReturn;
	}
	
	/**
	 * Change the location of the save file.
	 * @param location File to set as the new location.
	 */
	public static void changeSaveLocation(File location) {
		saveLocation = location;
	}
}
