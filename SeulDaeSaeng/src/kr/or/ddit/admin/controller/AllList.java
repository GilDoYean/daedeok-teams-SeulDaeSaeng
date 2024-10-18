package kr.or.ddit.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.admin.service.AdminServiceImpl;
import kr.or.ddit.admin.service.IAdminService;
import kr.or.ddit.admin.vo.MemberReqVO;


@WebServlet("/views/allList.do")
public class AllList extends HttpServlet {
	
	IAdminService service = AdminServiceImpl.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 // 회원가입된 회원 전체조회 
	      List<MemberReqVO> memberallList = service.selectallList();

	      req.setAttribute("memberallList", memberallList);
	      
	      req.getRequestDispatcher("/views/admin/allList.jsp").forward(req, resp);
	   }

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	
	}
}
