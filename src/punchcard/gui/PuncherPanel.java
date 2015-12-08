package punchcard.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import punchcard.Workday;

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
	
	Workday workdayStaging = null;
	
	/**
	 * Create a new PuncherPanel.
	 */
	public PuncherPanel() {

		add(btnPunchIn);
		add(btnPunchOut);
		tblWorkdays = new JTable(new WorkdayTableModel());
		JScrollPane workdayPane = new JScrollPane();
		workdayPane.add(tblWorkdays);
		add(workdayPane);
		
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
		DefaultTableModel model = (DefaultTableModel) tblWorkdays.getModel();
		String day = "date";
		String start = "starttime";
		String end = "endtime";
		model.addRow(new Object[] {day, start, end});
	}
	
	/**
	 * Custom model for the table displaying the workdays.
	 * @author Jonathan Thomas
	 *
	 */
	private class WorkdayTableModel extends DefaultTableModel {
		private static final long serialVersionUID = 4060693810634100465L;

		/**
		 * Array of column names.
		 */
		private String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};
		
		/**
		 * Array of data.
		 */
private Object[][] data = {
{"Kathy", "Smith",
"Snowboarding", new Integer(5), new Boolean(false)},
{"John", "Doe",
"Rowing", new Integer(3), new Boolean(true)},
{"Sue", "Black",
"Knitting", new Integer(2), new Boolean(false)},
{"Jane", "White",
"Speed reading", new Integer(20), new Boolean(true)},
{"Joe", "Brown",
"Pool", new Integer(10), new Boolean(false)}
};
		
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
        	return data.length;
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
            return data[row][col];
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
			System.out.println("Punching in!");
			// Once this button has been pressed, disable it and enable the opposite button
			btnPunchIn.setEnabled(false);
			btnPunchOut.setEnabled(true);
			
			// Create a new workday, with right now as the starting point
			workdayStaging = new Workday();
			workdayStaging.setBegin(new Date());
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
			System.out.println("Punchin out!");
			// Once this button has been pressed, disable it and enable the opposite button
			btnPunchIn.setEnabled(true);
			btnPunchOut.setEnabled(false);
			
			// If the workdayStaging object isn't empty, set the ending point to right now
			if (workdayStaging != null) {
				workdayStaging.setEnd(new Date());
				addWorkday(workdayStaging);
			}
		}
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		frame.add(new PuncherPanel());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

}
