package kr.or.ddit.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;

@WebServlet("/homework/delete.do")
public class DeleteHomeworkControlloer extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		IBoardService boardService = BoardServiceImpl.getInstance();
		 
		 int hwNo = Integer.parseInt(req.getParameter("hwNo"));
		 int levelChk = Integer.parseInt(req.getParameter("levelChk"));
		 
		 int cnt = boardService.deleteHomework(hwNo);
	    
		 // levelChk값에 따라 삭제 후 목록 이동
		 if(cnt>0) {
			 String msg = "정상적으로 삭제되었습니다.";
			 req.getSession().setAttribute("msg", msg);
			 resp.sendRedirect(req.getContextPath() + "/classTeacherBoard.do?levelChk=" + levelChk);
		 }else {
			 String msg = "삭제 실패했습니다.";
			 req.getSession().setAttribute("msg", msg);
			 req.getRequestDispatcher("/classTeacherBoard.do?levelChk=" + levelChk).forward(req, resp);
		 }
	}	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
