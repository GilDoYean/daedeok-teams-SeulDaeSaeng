package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;



@WebServlet("/member/idCheck.do")
public class MemberIdChk extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	IMemberService service = MemberServiceImpl.getInstance();
       
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String usersId = (String)req.getParameter("usersId");
		
		int cnt = service.memberIdChk(usersId);
		PrintWriter pw = resp.getWriter();
		pw.println(cnt);
	}

}
