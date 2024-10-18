package kr.or.ddit.util;

import kr.or.ddit.member.vo.MemberVO;

public class SessionUsersInfo {

	private static SessionUsersInfo instance = null;

	private SessionUsersInfo() {
	}

	public static SessionUsersInfo getInstance() {
		if (instance == null)
			instance = new SessionUsersInfo();
		return instance;
	}
	
	public MemberVO getMemberVO(String usersId) {
		// TODO Auto-generated method stub
		return null;
	}
}