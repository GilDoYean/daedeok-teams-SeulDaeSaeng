package kr.or.ddit.report.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.admin.vo.MemberReqVO;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.MyBatisUtil;

public class ReportDaoImpl implements IReportDao {

	private static ReportDaoImpl instance = null;
	private ReportDaoImpl() {}
	public static ReportDaoImpl getInstance() {
		if(instance == null) {
			instance = new ReportDaoImpl();
		}
		return instance;
	}
	
	
	@Override
	public List<BoardVO> selectReportList() {
		SqlSession session = null;
		List<BoardVO> reportList = null;
		
		try {
			session = MyBatisUtil.getSqlSession(true);
			reportList = session.selectList("report.selectReportList");   // xml 
			for(BoardVO vo : reportList) {
				System.out.println(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return reportList;
	}
	
	
	@Override
	public List<BoardVO> selectallList() {
		SqlSession session = null;
		List<BoardVO> allList = null;
		
		try {
			session = MyBatisUtil.getSqlSession(true);	
			allList = session.selectList("report.selectallList");   // xml 
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return allList;
	
	
	}
	@Override
	public List<MemberReqVO> reportmemberList() {
		SqlSession session = null;
		List<MemberReqVO> reportmemtList = null;
		
		try {
			session = MyBatisUtil.getSqlSession(true);
			reportmemtList = session.selectList("report.reportmemberList");   // xml 
			for(MemberReqVO vo : reportmemtList) {
				System.out.println(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return reportmemtList;
	}
	
	
	
}
