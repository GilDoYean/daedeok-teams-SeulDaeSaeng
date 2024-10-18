package kr.or.ddit.admin.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.ddit.admin.service.AdminServiceImpl;
import kr.or.ddit.admin.service.IAdminService;
import kr.or.ddit.admin.vo.MemberReqVO;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.contact.service.ContactServiceImpl;
import kr.or.ddit.contact.service.IContactService;
import kr.or.ddit.contact.vo.ContactVO;
import kr.or.ddit.report.service.IReportService;
import kr.or.ddit.report.service.ReportServiceImpl;
import kr.or.ddit.util.MailUtil;

@WebServlet("/views/adminPage.do")
public class Admin extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
// 회원정보 목록 가져오기 
   private IAdminService adminservice = AdminServiceImpl.getInstance();
   
   private IContactService service = ContactServiceImpl.getInstance();
   
   private IReportService reportservice = ReportServiceImpl.getInstance();
   
   
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      
	   
	   // 회원정보 목록 가져오기 
	   List<MemberReqVO> memberList = adminservice.selectmemList();   // 회원가입 신청내역 리스트
	   req.setAttribute("memberList", memberList);
	  
		List<ContactVO> contactList = service.selectContactList();   //오류제보 리스트 
		req.setAttribute("contactList", contactList);
		
		
		List<BoardVO> reportList = reportservice.selectReportList();   //신고 게시글 리스트 
		req.setAttribute("reportList", reportList);
	 
		List<MemberReqVO> reportmember = reportservice.reportmemberList();  // 신고당한 회원 리스트 
		req.setAttribute("reportmember", reportmember);
		
	   req.getRequestDispatcher("/views/admin/adminPage.jsp").forward(req, resp);
   }
   
   
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	   
	   // 모달 
	   MemberReqVO memberReqVO = new MemberReqVO();
	   memberReqVO = adminservice.selectmember(req.getParameter("usersNo" ));
	    
	   Gson gson = new Gson();
	   String json = gson.toJson(memberReqVO);
	   
	   
	   resp.setContentType("application/json");
	   resp.setCharacterEncoding("UTF-8");
	   
	   PrintWriter wrt = resp.getWriter();
	   wrt.print(json);
	   
	   
	   
		
	}
    
   }

