package kr.or.ddit.restaurant.vo;

import java.time.LocalDate;

public class ReviewVO {
	private int reviewNo;
	private int usersNo;
	private String restBizno;
	private int reviewStar;
	private String reviewText;
	private String reviewYn;
	private int fileNo;
	private LocalDate reviewDate;
	
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public int getUsersNo() {
		return usersNo;
	}
	public void setUsersNo(int usersNo) {
		this.usersNo = usersNo;
	}
	public String getRestBizno() {
		return restBizno;
	}
	public void setRestBizno(String restBizno) {
		this.restBizno = restBizno;
	}
	public int getReviewStar() {
		return reviewStar;
	}
	public void setReviewStar(int reviewStar) {
		this.reviewStar = reviewStar;
	}
	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	public String getReviewYn() {
		return reviewYn;
	}
	public void setReviewYn(String reviewYn) {
		this.reviewYn = reviewYn;
	}
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public LocalDate getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(LocalDate reviewDate) {
		this.reviewDate = reviewDate;
	}
	@Override
	public String toString() {
		return "ReviewVO [reviewNo=" + reviewNo + ", usersNo=" + usersNo + ", restBizno=" + restBizno + ", reviewStar="
				+ reviewStar + ", reviewText=" + reviewText + ", reviewYn=" + reviewYn + ", fileNo=" + fileNo
				+ ", reviewDate=" + reviewDate + "]";
	}
	
	
}
