package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.MemberUtil;

@WebServlet("/member/memberMyBoardList.do")
public class MemberMyBoardList extends HttpServlet {

	private static final long serialVersionUID = 1L;
	IBoardService service = BoardServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<BoardVO> myBoardList = service.myBoardList((int)req.getSession().getAttribute("usersNo"));
		
		if(myBoardList != null) {
			MemberUtil.boardLevelKr(myBoardList);
			req.setAttribute("myBoardList", myBoardList);
			req.getRequestDispatcher("/views/member/myBoardList.jsp").forward(req, resp);
		}else {
			MemberUtil.memberMessage(req, resp, "게시글이 없습니다.", "/member/myPageHome.do");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
