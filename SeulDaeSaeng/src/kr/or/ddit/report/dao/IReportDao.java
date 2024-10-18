package kr.or.ddit.report.dao;

import java.util.List;

import kr.or.ddit.admin.vo.MemberReqVO;
import kr.or.ddit.board.vo.BoardVO;

public interface IReportDao {

	public List<BoardVO> selectReportList();

	public List<BoardVO> selectallList();

	public List<MemberReqVO> reportmemberList();

}
