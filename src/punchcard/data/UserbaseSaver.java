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
