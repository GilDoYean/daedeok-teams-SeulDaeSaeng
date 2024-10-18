package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

@WebServlet("/member/boardReply")
public class MemberBoardReply extends HttpServlet{

	private static final long serialVersionUID = 1L;
	IBoardService service = BoardServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<BoardVO> memberBoardReplyList = new ArrayList<BoardVO>();
		memberBoardReplyList = service.getMemberBoardReply((int)req.getSession().getAttribute("usersNo"));
		req.setAttribute("memberBoardList", memberBoardReplyList);
		
		if(!memberBoardReplyList.isEmpty()) {
			req.getRequestDispatcher("/member/myPageHome.do").forward(req, resp);
		}{
			String msg = "댓글 쓴 게시글 목록 불러오는대 실패했습니다.";
			String url = "/main.do";
			req.setAttribute("msg", msg);
			req.setAttribute("url", url);
			req.getRequestDispatcher("/views/alert.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	
}
