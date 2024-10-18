package kr.or.ddit.admin.vo;

import java.util.Date;

public class MemberReqVO {	 
	 private int usersNo; 
	 private String memName; 
	 private String memNick; 
	 private Date memBirth; 
	 private String memEmail;
	 private String memTel; 
	 private String memClass; 
	 private Date memRegdt; 
	 private int addrNo;
	 private int reportNo;
	 
	 
	public int getReportNo() {
		return reportNo;
	}

	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}

	public MemberReqVO() {
	}
	
	public int getUsersNo() {
		return usersNo;
	}
	public void setUsersNo(int usersNo) {
		this.usersNo = usersNo;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemNick() {
		return memNick;
	}
	public void setMemNick(String memNick) {
		this.memNick = memNick;
	}
	public Date getMemBirth() {
		return memBirth;
	}
	public void setMemBirth(Date memBirth) {
		this.memBirth = memBirth;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public String getMemTel() {
		return memTel;
	}
	public void setMemTel(String memTel) {
		this.memTel = memTel;
	}
	public String getMemClass() {
		return memClass;
	}
	public void setMemClass(String memClass) {
		this.memClass = memClass;
	}
	public Date getMemRegdt() {
		return memRegdt;
	}
	public void setMemRegdt(Date memRegdt) {
		this.memRegdt = memRegdt;
	}
	public int getAddrNo() {
		return addrNo;
	}
	public void setAddrNo(int addrNo) {
		this.addrNo = addrNo;
	}
	@Override
	public String toString() {
		return "memberReqVO [usersNo=" + usersNo + ", memName=" + memName + ", memNick=" + memNick + ", memBirth="
				+ memBirth + ", memEmail=" + memEmail + ", memTel=" + memTel + ", memClass=" + memClass + ", memRegdt="
				+ memRegdt + ", addrNo=" + addrNo + "]";
	} 

	 
	 
	
	 
}
