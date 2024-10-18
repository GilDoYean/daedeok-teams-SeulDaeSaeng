package kr.or.ddit.board.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.FileDetailVO;
import kr.or.ddit.board.vo.ReplyVO;
import kr.or.ddit.member.vo.MemberVO;

@WebServlet("/story/detail.do")
public class StoryDetailController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	IBoardService service = BoardServiceImpl.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		 int storyNo = Integer.parseInt(req.getParameter("storyNo"));
		 req.setAttribute("storyNo", storyNo);
		 req.getRequestDispatcher("/views/board/storyView.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	     doGet(req, resp);
	}
}