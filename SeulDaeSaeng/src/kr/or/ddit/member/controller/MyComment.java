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

@WebServlet("/member/myComment.do")
public class MyComment extends HttpServlet {

	private static final long serialVersionUID = 1L;
	IBoardService service = BoardServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<BoardVO> myCommentList = service.myCommentList((int)req.getSession().getAttribute("usersNo"));
		
		if(myCommentList != null) {
			MemberUtil.boardLevelKr(myCommentList);
			req.setAttribute("myCommentList", myCommentList);
			req.getRequestDispatcher("/views/member/myComment.jsp").forward(req, resp);
		}else {
			MemberUtil.memberMessage(req, resp, "나의 댓글이 있는 게시글이 없습니다.", "/member/myPageHome.do");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}