package punchcard.gui;

import javax.swing.JFrame;

public class PunchcardPlus extends JFrame {
	
	private static final long serialVersionUID = -6547043843646536562L;

	public PunchcardPlus() {
		this.add(new PuncherPanel());
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.pack();
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		JFrame frame = new PunchcardPlus();

	}

}
