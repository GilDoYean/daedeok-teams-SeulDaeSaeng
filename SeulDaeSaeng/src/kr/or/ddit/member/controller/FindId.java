package kr.or.ddit.member.controller;

import java.io.IOException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.JsonObject;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

@WebServlet("/findId.do")
public class FindId extends HttpServlet {
	
    private static final long serialVersionUID = 1L;

    private IMemberService memberService = MemberServiceImpl.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/member/findId.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      
    	String memName = req.getParameter("memName");
        String memTel = req.getParameter("memTel");
        
        System.out.println(memName);
        System.out.println(memTel);
        
        MemberVO memberVO = new MemberVO();
        memberVO.setMemName(memName);
        memberVO.setMemTel(memTel);
        
        // DB에 이름, 휴대폰이 일치하는 계쩡이 있는지 확인
        String memberId = memberService.checkFindId(memberVO); 
        
  //    String memChk = memberService.checking(memName);
        
		/*
		 * if(memChk!=null) { System.out.println("회원이 있습니다"); }
		 */
        
//        if (member != null) {
//        	System.out.println("회원이 있습니다");
//        }else if(member==null) {
//        	System.out.println("회원이 없습니다.");
//        }
        
        JsonObject jsonResponse = new JsonObject();
  

        if (memberId != null) {
            jsonResponse.addProperty("status", "success");
            jsonResponse.addProperty("message", "회원님의 아이디는 " + memberId + "입니다");
            resp.setContentType("application/json");
            resp.getWriter().write(jsonResponse.toString());
        } else {
            jsonResponse.addProperty("status", "fail");
            jsonResponse.addProperty("message", "이름과 핸드폰 번호가 일치하지 않습니다.");
            resp.setContentType("application/json");
            resp.getWriter().write(jsonResponse.toString());
        }

    }
}


