package kr.or.ddit.contact.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.contact.service.ContactServiceImpl;
import kr.or.ddit.contact.service.IContactService;
import kr.or.ddit.contact.vo.ContactVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.MailUtil;
import kr.or.ddit.util.MyBatisUtil;

@WebServlet("/views/contactList.do")
public class ContactAllList extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	IContactService service = ContactServiceImpl.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		 // 회원가입된 회원 전체조회 
	      List<ContactVO> contactList = service.selectallList();

	      req.setAttribute("contactList", contactList);
	      
	      req.getRequestDispatcher("/views/admin/contactList.jsp").forward(req, resp);
	   }
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 메일 전송
		String title = req.getParameter("title");  // 제목
		String body = req.getParameter("body");   // 내용
		String memEmail = req.getParameter("memEmail");  // 주소
		int qNO = Integer.parseInt(req.getParameter("qNO"));  // 질문 번호
	
		MemberVO memberVO = new MemberVO();
		memberVO.setMemEmail(memEmail);
		    
		MailUtil mailutil = new MailUtil();
		mailutil.sendMail(title, body, memEmail);
		
		resp.setContentType("text/html;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");

		SqlSession session = MyBatisUtil.getSqlSession();
		
	        try {
	            int rowsUpdated = session.update("contactup", qNO);
	            if (rowsUpdated > 0) {
	                session.commit();
	            } else {
	                session.rollback();
	            }
	        } finally {
	            session.close();
	        }
		


	    resp.sendRedirect(req.getContextPath() + "/views/contactList.do");
			    
	}
	
}
