package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;

@WebServlet("/member/memberUnregister.do")
public class MemberUnregister extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	IMemberService service = MemberServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		String usersId = (String)session.getAttribute("usersId");
		int unregCnt = service.memberUnregister(usersId);
		
		if(unregCnt == 1) {
			session.invalidate();
			System.out.println("회원탈퇴 성공");
			String msg = "회원탈퇴 완료했습니다.";
			String url = "/main.do";
			req.setAttribute("msg", msg);
			req.setAttribute("url", url);
			req.getRequestDispatcher("/views/alert.jsp").forward(req, resp);
		} else {
			System.out.println("회원탈퇴 응답 실패");
			String msg = "회원탈퇴에 실패했습니다. 관리자에게 문의해주세요.";
			String url = "/main.do";
			req.setAttribute("msg", msg);
			req.setAttribute("url", url);
			req.getRequestDispatcher("/views/alert.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
