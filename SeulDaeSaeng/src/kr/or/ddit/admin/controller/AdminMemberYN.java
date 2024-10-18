package kr.or.ddit.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.admin.service.AdminServiceImpl;
import kr.or.ddit.admin.service.IAdminService;
import kr.or.ddit.util.MemberUtil;

@WebServlet("/admin/memberYN.do")
public class AdminMemberYN extends HttpServlet{


	private static final long serialVersionUID = 1L;
	IAdminService service = AdminServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int usersNoParam = Integer.parseInt(req.getParameter("usersNo"));
		
		int cnt = service.adminMemberYN(usersNoParam);
		
		if(cnt == 1) {
			MemberUtil.memberMessage(req, resp, "승인이 되었습니다.", "/views/adminPage.do");
		}else {
			MemberUtil.memberMessage(req, resp, "승인이 안됐습니다.", "/views/adminPage.do");
		}
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException {
		doGet(req, resp);
	}
}
