package kr.or.ddit.board.vo;

import java.time.LocalDate;

public class StoryVO {
	
	 private int storyNo;
	 private String storyCon;
	 private String storyYn;
	 private LocalDate storyAt;
	 private int fileNo;
	 private int usersNo;
	 
	public int getStoryNo() {
		return storyNo;
	}
	public void setStoryNo(int storyNo) {
		this.storyNo = storyNo;
	}
	public String getStoryCon() {
		return storyCon;
	}
	public void setStoryCon(String storyCon) {
		this.storyCon = storyCon;
	}
	public String getStoryYn() {
		return storyYn;
	}
	public void setStoryYn(String storyYn) {
		this.storyYn = storyYn;
	}
	public LocalDate getStoryAt() {
		return storyAt;
	}
	public void setStoryAt(LocalDate storyAt) {
		this.storyAt = storyAt;
	}
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public int getUsersNo() {
		return usersNo;
	}
	public void setUsersNo(int usersNo) {
		this.usersNo = usersNo;
	} 
}
