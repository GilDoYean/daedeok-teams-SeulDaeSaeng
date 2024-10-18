package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.UsersVO;

@WebServlet("/classBoard.do")
public class ClassBoardListController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 
		 IBoardService service = BoardServiceImpl.getInstance();
		 HttpSession session = req.getSession();
		 int usersRole = (int)session.getAttribute("usersRole");
		 
         if(usersRole==1) {
		     req.getRequestDispatcher("/views/board/classTeacherBoardIntro.jsp").forward(req, resp);
         }else if(usersRole==2) {
        	 req.getRequestDispatcher("/views/board/classBoard.jsp").forward(req, resp);
         }
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 doGet(req, resp);
	}
}