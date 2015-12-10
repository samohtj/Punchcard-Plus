package punchcard.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class UserDatabase implements Serializable {
	
	private static final long serialVersionUID = 5162598507212097174L;
	
	private static final String dbLocation = "/data/userdb.dat";
	
	private ArrayList<UserProfile> users = new ArrayList<>();
	
	public UserDatabase() {
		
	}
	
	/**
	 * Create a new user profile database from the given list of users.
	 * @param users List of users to add.
	 */
	public UserDatabase(ArrayList<UserProfile> users) {
		for (UserProfile user: users) {
			this.users.add(user);
		}
	}
	
	/**
	 * Add a user to the list.
	 * @param user UserProfile to add.
	 */
	public void addUser(UserProfile user) {
		users.add(user);
	}
	
	/**
	 * Return an ArrayList containing all the users in the database.
	 * @return ArrayList containing users.
	 */
	public ArrayList<UserProfile> getAllUsers() {
		ArrayList<UserProfile> toReturn = new ArrayList<>();
		for (UserProfile user: users) {
			toReturn.add(user);
		}
		
		return toReturn;
	}

	/**
	 * Save a UserDatabase object to a file.
	 * @param toSave the database object to save.
	 * @return True if successful, false if unsuccessful.
	 */
	public static boolean save(UserDatabase toSave) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(dbLocation)));
			out.writeObject(toSave);
			out.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Load a UserDatabase object from a file.
	 * @return A new UserDatabase object.
	 */
	public static UserDatabase load() {
		UserDatabase toReturn = null;
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(dbLocation)));
			toReturn = (UserDatabase) in.readObject();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (toReturn == null) {
				toReturn = new UserDatabase();
			}
			
		}
		return toReturn;
	}

}
