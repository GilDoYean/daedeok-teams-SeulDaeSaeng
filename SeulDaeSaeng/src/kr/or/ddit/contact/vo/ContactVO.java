package kr.or.ddit.contact.vo;

import java.util.Date;

public class ContactVO {

	private int qNO ;
	private String qTitle; 
	private String qCon; 
	private Date qAt; 
	private int usersNo;
	private String qEmail; 
	private String qYn;
	
	
	
	public String getqYn() {
		return qYn;
	}
	public void setqYn(String qYn) {
		this.qYn = qYn;
	}
	public int getqNO() {
		return qNO;
	}
	public void setqNO(int qNO) {
		this.qNO = qNO;
	}
	public String getqTitle() {
		return qTitle;
	}
	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}
	public String getqCon() {
		return qCon;
	}
	public void setqCon(String qCon) {
		this.qCon = qCon;
	}
	public Date getqAt() {
		return qAt;
	}
	public void setqAt(Date qAt) {
		this.qAt = qAt;
	}
	public int getUsersNo() {
		return usersNo;
	}
	public void setUsersNo(int usersNo) {
		this.usersNo = usersNo;
	}
	public String getqEmail() {
		return qEmail;
	}
	public void setqEmail(String qEmail) {
		this.qEmail = qEmail;
	}
	@Override
	public String toString() {
		return "ContactVO [qNO=" + qNO + ", qTitle=" + qTitle + ", qCon=" + qCon + ", qAt=" + qAt + ", usersNo="
				+ usersNo + ", qEmail=" + qEmail + "]";
	}
	
	
	
	
	
	
	
}
