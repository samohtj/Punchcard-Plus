package punchcard.data;

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
