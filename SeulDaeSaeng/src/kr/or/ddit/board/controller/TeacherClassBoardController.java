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
import kr.or.ddit.board.vo.FileDetailVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.UsersVO;

@WebServlet("/classTeacherBoard.do")
public class TeacherClassBoardController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		 IBoardService boardService = BoardServiceImpl.getInstance();
		 HttpSession session = req.getSession();
		 MemberVO memDetail = (MemberVO)session.getAttribute("memDetail");
		 int usersNo = (int)session.getAttribute("usersNo");
		 
		 int levelChk = Integer.parseInt(req.getParameter("levelChk"));
         List<BoardVO> noticeBoardList = boardService.getClassNoticeList(levelChk);
         List<BoardVO> classBoardList = boardService.selectClassBoardList(levelChk);
		 List<FileDetailVO> fileList = boardService.getFileList(levelChk);
		 req.setAttribute("noticeBoardList", noticeBoardList);
		 req.setAttribute("classBoardList", classBoardList);
		 req.setAttribute("fileList", fileList);
		 req.setAttribute("levelChk", levelChk);
		 req.setAttribute("usersNo", usersNo);
		 req.setAttribute("memDetail", memDetail);
		 
		 req.getRequestDispatcher("/views/board/classTeacherBoard.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 doGet(req, resp);
	}
}