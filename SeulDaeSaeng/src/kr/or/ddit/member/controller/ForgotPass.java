package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.MailUtil;





@WebServlet("/controller/forgotpass.do")
public class ForgotPass extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String usersId = req.getParameter("usersId");
		String memEmail = req.getParameter("memEmail");
		IMemberService service = MemberServiceImpl.getInstance();

		MemberVO memberVO = new MemberVO();
		memberVO.setUsersId(usersId);
		memberVO.setMemEmail(memEmail);

		String usersPass = service.forgotPass(memberVO);
		
		if (usersPass != null) {

			MailUtil sendMail = new MailUtil();
			sendMail.sendMail("찾으신 비밀번호입니다", "비밀번호 : " + usersPass, memEmail);
			
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("isSuccess", "ok");
			String jsonsStr = new Gson().toJson(jsonObject);
			resp.setContentType("application/json");
			resp.getWriter().write(jsonsStr);
			
		} else {

			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("isSuccess", "fail");
			String jsonsStr = new Gson().toJson(jsonObject);
			resp.setContentType("application/json");
			resp.getWriter().write(jsonsStr);
		}
	}
}