package kr.or.ddit.board.vo;

import java.time.LocalDate;

public class FileDetailVO {
	
	private int fileNo;
	private String fileSavepath;
	private String fileOgname;
	private String fileSavednm;
	private long fileSize;
	private String fileExt;
	private LocalDate fileDt;
	private int fileLevel;
	
	
	public int getFileLevel() {
		return fileLevel;
	}
	public void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public String getFileSavepath() {
		return fileSavepath;
	}
	public void setFileSavepath(String fileSavepath) {
		this.fileSavepath = fileSavepath;
	}
	public String getFileOgname() {
		return fileOgname;
	}
	public void setFileOgname(String fileOgname) {
		this.fileOgname = fileOgname;
	}
	public String getFileSavednm() {
		return fileSavednm;
	}
	public void setFileSavednm(String fileSavednm) {
		this.fileSavednm = fileSavednm;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileExt() {
		return fileExt;
	}
	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}
	public LocalDate getFileDt() {
		return fileDt;
	}
	public void setFileDt(LocalDate fileDt) {
		this.fileDt = fileDt;
	}


}
