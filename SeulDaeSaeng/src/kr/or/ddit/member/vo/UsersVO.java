package kr.or.ddit.member.vo;

public class UsersVO {
	
	private int usersNo; /*  */
	private int usersRole; /* 1.선생님 2.학생 3.관리자 */
	private String usersId; /*  */
	private String usersPass; /*  */
	private String usersYn; /*  */

	public int getUsersNo() {
		return usersNo;
	}

	public void setUsersNo(int usersNo) {
		this.usersNo = usersNo;
	}

	public int getUsersRole() {
		return usersRole;
	}

	public void setUsersRole(int usersRole) {
		this.usersRole = usersRole;
	}

	public String getUsersId() {
		return usersId;
	}

	public void setUsersId(String usersId) {
		this.usersId = usersId;
	}

	public String getUsersPass() {
		return usersPass;
	}

	public void setUsersPass(String usersPass) {
		this.usersPass = usersPass;
	}

	public String getUsersYn() {
		return usersYn;
	}

	public void setUsersYn(String usersYn) {
		this.usersYn = usersYn;
	}

	@Override
	public String toString() {
		return "UsersVO [usersNo=" + usersNo + ", usersRole=" + usersRole + ", usersId=" + usersId + ", usersPass="
				+ usersPass + ", usersYn=" + usersYn + "]";
	}
	

	
	
	
}
