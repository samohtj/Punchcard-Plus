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

public class PuncherPanel extends JPanel {
	private static final long serialVersionUID = -3577936101409734832L;

	JButton btnPunchIn = new JButton("Punch In");
	JButton btnPunchOut = new JButton("Punch Out");
	
	JTable tblWorkdays;
	
	Workday workdayStaging = null;
	
	public PuncherPanel() {

		add(btnPunchIn);
		add(btnPunchOut);
		tblWorkdays = new JTable(new WorkdayTableModel());
		JScrollPane workdayPane = new JScrollPane();
		workdayPane.add(tblWorkdays);
		add(workdayPane);
		
		btnPunchOut.setEnabled(false);
		
		btnPunchIn.addActionListener(new PunchInListener());
		btnPunchOut.addActionListener(new PunchOutListener());
	}
	
	public void addWorkday(Workday workday) {
		DefaultTableModel model = (DefaultTableModel) tblWorkdays.getModel();
		String day = "date";
		String start = "starttime";
		String end = "endtime";
		model.addRow(new Object[] {day, start, end});
	}
	
	private class WorkdayTableModel extends DefaultTableModel {
		private static final long serialVersionUID = 4060693810634100465L;

		private String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};
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
		
		public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
        	return 1;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }
	}
	
	private class PunchInListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Punching in!");
			btnPunchIn.setEnabled(false);
			btnPunchOut.setEnabled(true);
			
			workdayStaging = new Workday();
			workdayStaging.setBegin(new Date());
		}
	}
	
	private class PunchOutListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Punchin out!");
			btnPunchIn.setEnabled(true);
			btnPunchOut.setEnabled(false);
			
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
