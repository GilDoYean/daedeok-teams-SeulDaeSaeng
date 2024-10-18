package kr.or.ddit.board.vo;

import java.time.LocalDate;
import java.util.Date;

public class BoardVO {

	private int boardNo;
	private int boardLevel;
	private String boardTitle;
	private String boardCon;
	private LocalDate boardAt;
	private String boardYn;
	private int boardHit;
	private int fileNo;
	private int usersNo;
	private String boardLevelKr; // 장현재 추가 => 보드분류를 한글로 변경해서 담기위해 추가
	
	public String getBoardLevelKr() {
		return boardLevelKr;
	}
	public void setBoardLevelKr(String boardLevelKr) {
		this.boardLevelKr = boardLevelKr;
	}
	public int getUsersNo() {
		return usersNo;
	}
	public void setUsersNo(int usersNo) {
		this.usersNo = usersNo;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getBoardLevel() {
		return boardLevel;
	}
	public void setBoardLevel(int boardLevel) {
		this.boardLevel = boardLevel;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardCon() {
		return boardCon;
	}
	public void setBoardCon(String boardCon) {
		this.boardCon = boardCon;
	}
	public LocalDate getBoardAt() {
		return boardAt;
	}
	public void setBoardAt(LocalDate boardAt) {
		this.boardAt = boardAt;
	}
	public String getBoardYn() {
		return boardYn;
	}
	public void setBoardYn(String boardYn) {
		this.boardYn = boardYn;
	}
	public int getBoardHit() {
		return boardHit;
	}
	public void setBoardHit(int boardHit) {
		this.boardHit = boardHit;
	}
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	@Override
	public String toString() {
		return "BoardVO [boardNo=" + boardNo + ", boardLevel=" + boardLevel + ", boardTitle=" + boardTitle
				+ ", boardCon=" + boardCon + ", boardAt=" + boardAt + ", boardYn=" + boardYn + ", boardHit=" + boardHit
				+ ", fileNo=" + fileNo + ", usersNo=" + usersNo + ", boardLevelKr=" + boardLevelKr + "]";
	}
	
	
}
