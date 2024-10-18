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





@WebServlet("/findPass.do")
public class FindPass extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private IMemberService memberService = MemberServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/member/findPass.jsp").forward(req, resp);
    
		// 페이지이동 코드 넣어놓으셈
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    	String usersId = req.getParameter("usersId");
        String memEmail = req.getParameter("memEmail");
        
        MemberVO memberVO = new MemberVO();
        memberVO.setUsersId(usersId);
        memberVO.setMemEmail(memEmail);
        
       
        
        // DB에 아이디. 이메일 일치하는 계정이 있는지 확인
        String usersPw = memberService.checkFindPw(memberVO);

        JsonObject jsonResponse = new JsonObject();
        if (memberVO != null) {
        	
        	MailUtil sendMail = new MailUtil();
			sendMail.sendMail("찾으신 비밀번호입니다", "비밀번호 : " + usersPw, memEmail);
        	
            jsonResponse.addProperty("status", "success");
            jsonResponse.addProperty("message", "회원님의 비밀번호를 이메일로 전송했습니다");
            
            // 이메일 보내는 코드 넣어야함
            
            resp.setContentType("application/json");
            resp.getWriter().write(jsonResponse.toString());
        } else{
            jsonResponse.addProperty("status", "fail");
            jsonResponse.addProperty("message", "아이디와 이메일이 일치하지 않습니다.");
            resp.setContentType("application/json");
            resp.getWriter().write(jsonResponse.toString());
        }
	}
}