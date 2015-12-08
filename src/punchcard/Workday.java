package punchcard;

import java.util.Date;

public class Workday {
	private Date begin;
	private Date end;
	
	public Workday(Date begin, Date end) {
		this.begin = begin;
		this.end = end;
	}
	
	public Workday() {
	}

	public Date getBegin() {
		return begin;
	}
	public void setBegin(Date begin) {
		this.begin = begin;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
}
