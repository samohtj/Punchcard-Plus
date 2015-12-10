package punchcard.data;

import java.util.ArrayList;

public class UserProfile {
	
	private String username;
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
