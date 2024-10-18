package kr.or.ddit.board.vo;

import java.time.LocalDate;
import java.util.Date;

public class HomeworkVO {

	 private int hwNo;
	 private String hwTitle;
	 private String hwCon;
	 private LocalDate hwStart;
	 private LocalDate hwEnd;
	 private int usersNo;
	 private int hwClass;

	 
	    
	public int getHwClass() {
		return hwClass;
	}
	public void setHwClass(int hwClass) {
		this.hwClass = hwClass;
	}
	public int getHwNo() {
		return hwNo;
	}
	public void setHwNo(int hwNo) {
		this.hwNo = hwNo;
	}
	public String getHwTitle() {
		return hwTitle;
	}
	public void setHwTitle(String hwTitle) {
		this.hwTitle = hwTitle;
	}
	public String getHwCon() {
		return hwCon;
	}
	public void setHwCon(String hwCon) {
		this.hwCon = hwCon;
	}
	public LocalDate getHwStart() {
		return hwStart;
	}
	public void setHwStart(LocalDate hwStart) {
		this.hwStart = hwStart;
	}
	public LocalDate getHwEnd() {
		return hwEnd;
	}
	public void setHwEnd(LocalDate hwEnd) {
		this.hwEnd = hwEnd;
	}
	public int getUsersNo() {
		return usersNo;
	}
	public void setUsersNo(int usersNo) {
		this.usersNo = usersNo;
	}
	 
	
}
