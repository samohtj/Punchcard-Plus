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

package punchcard.gui;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import punchcard.data.Password;
import punchcard.data.UserProfile;
import punchcard.data.Workday;

/**
 * Panel containing the punch in / punch out buttons, and a list of workdays in this payment period.
 * @author Jonathan Thomas
 * 
 */
public class PuncherPanel extends JPanel {
	private static final long serialVersionUID = -3577936101409734832L;

	private JButton btnPunchIn = new JButton("Punch In");
	private JButton btnPunchOut = new JButton("Punch Out");
	
	WorkdayTablePane tablePane = new WorkdayTablePane();	
	Workday workdayStaging = null;
	UserProfile activeUser = new UserProfile();
	
	private boolean punchedIn = false;
	
	/**
	 * Create a new PuncherPanel.
	 */
	public PuncherPanel(UserProfile user) {
		this.activeUser = user;
		this.setLayout(new BorderLayout());
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(btnPunchIn);
		buttonsPanel.add(btnPunchOut);
		this.add(buttonsPanel, BorderLayout.SOUTH);
		
		this.add(tablePane.getPane(), BorderLayout.CENTER);
		
		// Enable or disable the punch-in and punch-out buttons, depending on whether the user is 
		// currently punched in.
		btnPunchOut.setEnabled(punchedIn);
		btnPunchIn.setEnabled(!punchedIn);
		
		btnPunchIn.addActionListener(new PunchInListener());
		btnPunchOut.addActionListener(new PunchOutListener());
	}
	
		/**
	 * Whether the user is currently punched in in this frame.
	 * @return boolean is punched in / isn't punched in.
	 */
	public boolean isPunchedIn() {
		return punchedIn;
	}
	
	/**
	 * PunchInListener is an action listener for the punch in button.
	 * @author Jonathan Thomas
	 *
	 */
	private class PunchInListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//System.out.println("Punching in!");
			// Once this button has been pressed, disable it and enable the opposite button
			btnPunchIn.setEnabled(false);
			btnPunchOut.setEnabled(true);
			
			punchedIn = true;
			
			// Create a new workday, with right now as the starting point
			workdayStaging = new Workday(activeUser, Calendar.getInstance());
		}
	}
	
	/**
	 * PunchOutListener is an action listener for the punch out button.
	 * @author Jonathan Thomas
	 *
	 */
	private class PunchOutListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//System.out.println("Punchin out!");
			
			// Once this button has been pressed, disable it and enable the opposite button
			btnPunchIn.setEnabled(true);
			btnPunchOut.setEnabled(false);
			
			punchedIn = false;
			
			// If the workdayStaging object isn't empty, set the ending point to right now
			if (workdayStaging != null) {
				workdayStaging.setEnd(Calendar.getInstance());
				tablePane.addWorkday(workdayStaging);
			}
			
			//printArray();
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		frame.add(new PuncherPanel(new UserProfile("Jimmy Johnson", new Password("securepassword"))));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.pack();
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

}
