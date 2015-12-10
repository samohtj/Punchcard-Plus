/*
 * Punchcard-Plus tracks time spent working by multiple users.
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
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

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
	
	JTable tblWorkdays;
	
	ArrayList<Workday> workdays = new ArrayList<>();
	
	Workday workdayStaging = null;
	
	/**
	 * Create a new PuncherPanel.
	 */
	public PuncherPanel() {
		this.setLayout(new BorderLayout());
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(btnPunchIn);
		buttonsPanel.add(btnPunchOut);
		this.add(buttonsPanel, BorderLayout.SOUTH);
		
		tblWorkdays = new JTable(new WorkdayTableModel());
		tblWorkdays.setPreferredScrollableViewportSize(new Dimension(500, 70));
        tblWorkdays.setFillsViewportHeight(true);
		JScrollPane workdayPane = new JScrollPane(tblWorkdays);
		this.add(workdayPane, BorderLayout.CENTER);
		
		// Disable the punch out button. This assumes that you aren't currently punched in.
		btnPunchOut.setEnabled(false);
		
		btnPunchIn.addActionListener(new PunchInListener());
		btnPunchOut.addActionListener(new PunchOutListener());
	}
	
	/**
	 * Add a workday to the display table.
	 * @param workday
	 */
	public void addWorkday(Workday workday) {
		workdays.add(workday);
		WorkdayTableModel model = (WorkdayTableModel) tblWorkdays.getModel();
		
		String day 		= getDateString(workday.getBegin());
		String start 	= getTimeString(workday.getBegin());
		String end 		= getTimeString(workday.getEnd());
		String duration = getDurationString(workday.getBegin(), workday.getEnd());
		
		model.addRow(new Object[] {day, start, end, duration});
	}
	
	private String getDateString(Calendar date) {
		return date.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.US)
				+ " " + date.get(Calendar.DATE) + ", "
				+ date.get(Calendar.YEAR);
	}
	
	private String getTimeString(Calendar date) {
		return date.get(Calendar.HOUR_OF_DAY) + ":" + date.get(Calendar.MINUTE)
			+ ":" + date.get(Calendar.SECOND);
	}
	
	private String getDurationString(Calendar start, Calendar end) {
		long diff = end.getTime().getTime() - start.getTime().getTime();
		long seconds = diff / 1000 % 60;
		long minutes = diff / (1000 * 60) % 60;
		long hours = diff / (1000 * 60 * 60);
		
		return hours + ":" + minutes + ":" + seconds;
	}
	
	/**
	 * Custom model for the table displaying the workdays.
	 * @author Jonathan Thomas
	 *
	 */
	private class WorkdayTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 4060693810634100465L;

		/**
		 * Array of column names.
		 */
		private String[] columnNames = {"Date", "Start Time", "End Time", "Duration"};
		
		/**
		 * Array of data.
		 */
		private ArrayList<Object[]> data = new ArrayList<>();
		
		/**
		 * Return the number of columns in the model.
		 * @return int quantity of columns.
		 */
		public int getColumnCount() {
            return columnNames.length;
        }

		/**
		 * Return the number of rows displayed by the model.
		 * @return int quantity of rows.
		 */
        public int getRowCount() {
        	return data.size();
        }

        /**
         * Get the name of the specified column.
         * @return String name.
         */
        public String getColumnName(int col) {
            return columnNames[col];
        }

        /**
         * Get the value stored at a certain position in the model.
         * @return Object representing whatever it was.
         */
        public Object getValueAt(int row, int col) {
            return data.get(row)[col];
        }
        
        public void setValueAt(Object value, int row, int col) {
            //System.out.println("Setting the value at (" + row + ", " + col + ") to " + value);
        	data.get(row)[col] = value;
            fireTableCellUpdated(row, col);
        }
        
        public Class<?> getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
        
        public void addRow(Object[] row) {
        	data.add(row);
        	for(int i = 0; i < row.length; i++) {
        		setValueAt(row[i], data.size() - 1, i);
        	}
        }
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
			
			// Create a new workday, with right now as the starting point
			workdayStaging = new Workday();
			workdayStaging.setBegin(Calendar.getInstance());
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
			
			// If the workdayStaging object isn't empty, set the ending point to right now
			if (workdayStaging != null) {
				workdayStaging.setEnd(Calendar.getInstance());
				addWorkday(workdayStaging);
			}
			
			//printArray();
		}
	}
	
	/**
	 * Print the contents of {@code workdays} to the console.
	 */
	@SuppressWarnings("unused")
	private void printArray() {
		for (Workday day: workdays) {
			System.out.println(day.getBegin() + " - " + day.getEnd());
		}
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		frame.add(new PuncherPanel());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.pack();
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

}
