package kr.or.ddit.report.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.MyBatisUtil;


@WebServlet("/views/reportDetail.do")
public class ReportDetail extends HttpServlet {
	
	private IBoardService service = BoardServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 int boardNo = Integer.parseInt(req.getParameter("boardNo"));
		 BoardVO boardDetail = service.getBoardDetail(boardNo);
//		 MemberVO WrDetail = service.writerDetail(boardNo);
		 
		 int usersNo = boardDetail.getUsersNo();
		 Map<String,Object> parameter = new HashMap<>();
		 parameter.put("usersNo",usersNo);
		 parameter.put("boardNo",boardNo);
		 MemberVO WrDetail = service.getwriterDetail(parameter);
		 
		 
		 String boardTitle = boardDetail.getBoardTitle();
		 String boardCon = boardDetail.getBoardCon();
		 int boardLevel = boardDetail.getBoardLevel();
		 int boardHit = boardDetail.getBoardHit();
		 LocalDate boardDt = boardDetail.getBoardAt();
		 
		 
		 req.setAttribute("WrDetail", WrDetail);
		 req.setAttribute("boardDetail", boardDetail);
	
		
		req.getRequestDispatcher("/views/admin/reportDetail.jsp").forward(req, resp);
	}	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 블라인드 처리하기 
		SqlSession session = MyBatisUtil.getSqlSession();
		
		  try {
	            // 매퍼를 호출하여 쿼리 실행
	            int boardNo = Integer.parseInt(req.getParameter("boardNo"));
	            session.update("updateBoardYn", boardNo);
	            session.commit(); // 커밋
	           
	            resp.sendRedirect(req.getContextPath() + "/views/adminPage.do");
	        } finally {
	        	session.close(); // 세션 닫기
	        }
	    }
	
	}

