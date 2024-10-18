package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;



@WebServlet("/member/pwCheck.do")
public class MemberPwChk extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	IMemberService service = MemberServiceImpl.getInstance();
       
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String usersPass = (String)session.getAttribute("usersPass");
		req.setAttribute("usersPass", usersPass);
		
		req.getRequestDispatcher("/views/member/pwChk.jsp").forward(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
		
//		String usersPassChk = (String)req.getParameter("usersPass");
//		boolean result = false;
//		System.out.println("회원수정 비밀번호 체크 시작");
////		System.out.println("세션값 => " + usersPass);
//		System.out.println("ajax값 => " + usersPassChk);
//		
////		if(usersPass == usersPassChk) {
////			result = true;
////		}
//		System.out.println("결과값 => " + result);
//		
//		PrintWriter pw = resp.getWriter();
//		pw.println(result);
	}

}
