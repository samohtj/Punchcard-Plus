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

import javax.swing.JFrame;

import punchcard.data.Password;
import punchcard.data.UserProfile;

public class PunchcardPlus extends JFrame {
	
	private static final long serialVersionUID = -6547043843646536562L;

	public PunchcardPlus() {
		this.add(new PuncherPanel(new UserProfile("Jimmy Johnson", new Password("securepassword"))));
		
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
