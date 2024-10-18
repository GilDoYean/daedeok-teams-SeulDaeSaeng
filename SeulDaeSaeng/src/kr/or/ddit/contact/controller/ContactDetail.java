package kr.or.ddit.contact.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.contact.service.ContactServiceImpl;
import kr.or.ddit.contact.service.IContactService;
import kr.or.ddit.contact.vo.ContactVO;

@WebServlet("/views/contactDetail.do")
public class ContactDetail extends HttpServlet{
	
	private IContactService service = ContactServiceImpl.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String qNO = req.getParameter("qNO");
		ContactVO contactVO = service.detailList(Integer.parseInt(qNO));
		
//		데이터를 전달하는 방법
		req.setAttribute("contactVO", contactVO);
		
		req.getRequestDispatcher("/views/admin/contactDetail.jsp").forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}
