package kr.or.ddit.board.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

@WebServlet("/board/delete.do")
public class DeleteBoardController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 
		 IBoardService boardService = BoardServiceImpl.getInstance();
		 
		 int boardNo = Integer.parseInt(req.getParameter("boardNo"));
		 int levelChk = Integer.parseInt(req.getParameter("levelChk"));
		 int classBoardChk = 0;
		 if(req.getParameter("classBoardChk")!=null) {
				classBoardChk = Integer.parseInt(req.getParameter("classBoardChk"));
		 }
		 int cnt = boardService.deleteBoard(boardNo);
	    
		 // levelChk값에 따라 삭제 후 목록 이동
		 if(cnt>0) {
			 String msg = "정상적으로 삭제되었습니다.";
			 req.getSession().setAttribute("msg", msg);
			 if(levelChk == 0) resp.sendRedirect(req.getContextPath() + "/allBoard.do");
			 else if(levelChk == 1) resp.sendRedirect(req.getContextPath() + "/freeBoard.do");
			 else if(levelChk == 2) resp.sendRedirect(req.getContextPath() + "/studyBoard.do");
			 else if(levelChk == 3) resp.sendRedirect(req.getContextPath() + "/noticeBoard.do");
			 else if(levelChk > 300) {
				 if(classBoardChk == 1) {
					 resp.sendRedirect(req.getContextPath() + "/eachClassNotice.do?levelChk=" + levelChk);
				 } else if(classBoardChk == 2) {
					 resp.sendRedirect(req.getContextPath() + "/eachClassBoard.do?levelChk=" + levelChk);
				 }
			 }
		 }else {
			 String msg = "삭제 실패했습니다.";
			 req.getSession().setAttribute("msg", msg);
			 if(levelChk == 0) resp.sendRedirect(req.getContextPath() + "/allBoard.do");
			 else if(levelChk == 1) resp.sendRedirect(req.getContextPath() + "/freeBoard.do");
			 else if(levelChk == 2) resp.sendRedirect(req.getContextPath() + "/studyBoard.do");
			 else if(levelChk == 3) resp.sendRedirect(req.getContextPath() + "/noticeBoard.do");
			 else if(levelChk > 300) {
				 if(classBoardChk == 1) {
					 resp.sendRedirect(req.getContextPath() + "/eachClassNotice.do?levelChk=" + levelChk);
				 } else if(classBoardChk == 2) {
					 resp.sendRedirect(req.getContextPath() + "/eachClassBoard.do?levelChk=" + levelChk);
				 }
			 }
		 }
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 doGet(req, resp);
	}
}
