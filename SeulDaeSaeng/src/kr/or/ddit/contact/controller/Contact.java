package kr.or.ddit.contact.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.contact.service.ContactServiceImpl;
import kr.or.ddit.contact.service.IContactService;
import kr.or.ddit.contact.vo.ContactVO;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.UsersVO;

@WebServlet("/contact.do")
public class Contact extends HttpServlet {

	private IContactService service = ContactServiceImpl.getInstance();
	private IMemberService memberservice = MemberServiceImpl.getInstance(); 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/contact.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		req.setCharacterEncoding("UTF-8");
		
		String usersId = (String)session.getAttribute("usersId");
		UsersVO usersVo = memberservice.selectUsers(usersId);
		int usersNo = usersVo.getUsersNo();

	 
		String email = req.getParameter("email");
		String subject = req.getParameter("subject");
		String message = req.getParameter("message");
		
		ContactVO contactVO = new ContactVO();
	
		contactVO.setqTitle(subject);
		contactVO.setqCon(message);
		contactVO.setUsersNo(usersNo);
		contactVO.setqEmail(email);
		
	
		int status = service.insertContact(contactVO);
		
		// 데이터 수정 삭제 등록 등등  redirect
		if(status > 0) { //성공
			resp.sendRedirect(req.getContextPath() + "/main.do");
		}else {	//실패
			req.getRequestDispatcher("/views/contact.jsp").forward(req, resp);
		}
		
	}
}
