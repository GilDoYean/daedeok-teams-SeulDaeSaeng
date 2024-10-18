package kr.or.ddit.restaurant.vo;


public class restLikeVO {
	private String restBizno;
	private int userNo;
	private String likeYn;
	public String getRestBizno() {
		return restBizno;
	}
	public void setRestBizno(String restBizno) {
		this.restBizno = restBizno;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getLikeYn() {
		return likeYn;
	}
	public void setLikeYn(String likeYn) {
		this.likeYn = likeYn;
	}
	@Override
	public String toString() {
		return "restLikeVO [restBizno=" + restBizno + ", userNo=" + userNo + ", likeYn=" + likeYn + "]";
	}
	
}
