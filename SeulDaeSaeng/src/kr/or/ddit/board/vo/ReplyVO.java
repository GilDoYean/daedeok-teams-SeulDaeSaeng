package kr.or.ddit.board.vo;

import java.time.LocalDate;

public class ReplyVO {
	 private int replyNo;
	 private String replyCon;
	 private String replyYn;
	 private int boardNo;
	 private int usersNo;
	 private LocalDate replyDt;
	 
	 
	public int getUsersNo() {
		return usersNo;
	}
	public void setUsersNo(int usersNo) {
		this.usersNo = usersNo;
	}
	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}
	public String getReplyCon() {
		return replyCon;
	}
	public void setReplyCon(String replyCon) {
		this.replyCon = replyCon;
	}
	public String getReplyYn() {
		return replyYn;
	}
	public void setReplyYn(String replyYn) {
		this.replyYn = replyYn;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public LocalDate getReplyDt() {
		return replyDt;
	}
	public void setReplyDt(LocalDate replyDt) {
		this.replyDt = replyDt;
	}
	 
}
