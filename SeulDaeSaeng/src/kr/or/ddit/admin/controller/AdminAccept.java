package kr.or.ddit.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.admin.service.AdminServiceImpl;
import kr.or.ddit.admin.service.IAdminService;
import kr.or.ddit.admin.vo.MemberReqVO;
import kr.or.ddit.util.MyBatisUtil;


@WebServlet("/views/adminaccept.do")
public class AdminAccept extends HttpServlet {
	
	IAdminService service = AdminServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberReqVO memreqVO = new MemberReqVO();
	
		req.setAttribute("memreqVO", memreqVO);
		req.getRequestDispatcher("/views/admin/adminPage.jsp").forward(req, resp);	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberReqVO memreqVO = new MemberReqVO();
		
		req.setAttribute("memreqVO", memreqVO);
		

	    // 사용자로부터 입력된 사용자 번호를 가져옴
	    int usersNoParam = Integer.parseInt(req.getParameter("usersNo"));
		int cnt = 0;
	
	    if (usersNoParam > 0) { // null 또는 빈 문자열인지 검사
	        try {
	            SqlSession session = MyBatisUtil.getSqlSession();
	            try {
	            	  // 매퍼를 호출하여 쿼리 실행
	            	cnt = session.update("adminAccept", usersNoParam);
	                if (cnt == 1) {
	                    // 회원 정보를 세션에 저장하거나 필요한 작업을 수행
	                    session.commit(); // 커밋
	                    resp.sendRedirect(req.getContextPath() + "/views/adminPage.do");
	                } else {
	                    // 선택한 회원이 없는 경우에 대한 처리
	                    resp.sendRedirect(req.getContextPath() + "/errorPage.jsp");
	                }
	            }catch (Exception e) {
	            	e.printStackTrace();
	            }finally {
	                session.close(); // 세션 닫기
	            }
	        } catch (NumberFormatException e) {
	            // 숫자로 변환할 수 없는 경우 예외 처리
	            resp.sendRedirect(req.getContextPath() + "/errorPage.jsp");
	        }
	    
	    }
        req.getRequestDispatcher("/views/admin/adminPage.jsp").forward(req, resp);

	    
	}
	    
	}










