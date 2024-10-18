package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

import com.google.gson.JsonObject;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.UsersVO;
import kr.or.ddit.restaurant.vo.restLikeVO;
import kr.or.ddit.util.MemberUtil;

@WebServlet("/login.do")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/member/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String usersId = req.getParameter("id");
		String usersPass = req.getParameter("pw");
		Boolean isMemberLogin = Boolean.parseBoolean(req.getParameter("memberLogin"));

		IMemberService loginService = MemberServiceImpl.getInstance();
		MemberVO memberVO = new MemberVO();
		memberVO.setUsersId(usersId);
		memberVO.setUsersPass(usersPass);

		boolean isSuccess = loginService.loginCheck(memberVO, isMemberLogin);

		
		if (isSuccess) {
			System.out.println("로그인성공");
			HttpSession session = req.getSession();
			session.setMaxInactiveInterval(0);
			
			UsersVO usersDetail = loginService.getUsersDetail(usersId);
			session.setAttribute("usersId", usersId);
			session.setAttribute("usersPass", usersPass);
			session.setAttribute("usersDetail", usersDetail);
			session.setAttribute("usersRole", usersDetail.getUsersRole());
			session.setAttribute("usersNo", usersDetail.getUsersNo());
			
			
			if(usersDetail.getUsersRole() == 3) {
				req.getSession().setAttribute("isAdminOk", "OK");
				resp.sendRedirect(req.getContextPath() + "/views/adminPage.do");
//				req.getRequestDispatcher(req.getContextPath() + "/views/adminPage.do").forward(req, resp);
			}
			else {
				MemberVO memDetail = loginService.getMemDetail(usersId);
				req.getSession().setAttribute("memDetail", memDetail);
				resp.sendRedirect(req.getContextPath() + "/main.do");
			}
		} else {
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("isSuccess", "fail");
			MemberUtil.memberMessage(req, resp, "아이디 또는 비밀번호가 올바르지 않습니다.", "/login.do");
		}
	}
}