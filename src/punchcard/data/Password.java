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

package punchcard.data;

import java.io.Serializable;

public class Password implements Comparable<Password>, Serializable {
	private static final long serialVersionUID = -4089526119855291717L;

	private char[] text;

	public Password(String text) {
		this.text = text.toCharArray();
	}

	public char[] get() {
		return text;
	}

	/**
	 * Destroy the password so it is not viewable in memory.
	 */
	public void destroy() {
		for (int i = 0; i < text.length; i++) {
			text[i] = '\0';
		}
	}

	/**
	 * Compare this password with another.
	 * @param o Password to compare.
	 * @return 0 if passwords are equal, -1 if they are not.
	 */
	@Override
	public int compareTo(Password o) {

		if (text.length != o.get().length) {
			return -1;
		}

		for (int i = 0; i < text.length; i++) {
			if (text[i] != o.get()[i]) {
				return -1;
			}
		}
		return 0;
	}
}
