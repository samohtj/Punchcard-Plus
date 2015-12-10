package punchcard.data;

public class Password implements Comparable<Password>{
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
