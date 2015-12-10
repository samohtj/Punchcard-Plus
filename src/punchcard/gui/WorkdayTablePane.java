package punchcard.gui;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import punchcard.data.Workday;

public class WorkdayTablePane {

	JTable tblWorkdays;
	JScrollPane tablePane;
	ArrayList<Workday> workdays = new ArrayList<>();
	
	public WorkdayTablePane() {
		tblWorkdays = new JTable(new WorkdayTableModel());
		tblWorkdays.setPreferredScrollableViewportSize(new Dimension(500, 70));
		tblWorkdays.setFillsViewportHeight(true);
		tablePane = new JScrollPane(tblWorkdays);	
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
	
	/**
	 * Get the JScrollPane displaying the data.
	 * @return JSCrollPane the active pane.
	 */
	public JScrollPane getPane() {
		return tablePane;
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
	 * Print the contents of {@code workdays} to the console.
	 */
	@SuppressWarnings("unused")
	private void printArray() {
		for (Workday day: workdays) {
			System.out.println(day.getBegin() + " - " + day.getEnd());
		}
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
}
