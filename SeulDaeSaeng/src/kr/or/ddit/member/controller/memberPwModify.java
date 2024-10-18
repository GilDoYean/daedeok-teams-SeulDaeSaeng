package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.MemberUtil;

@WebServlet("/member/pwModify.do")
public class memberPwModify extends HttpServlet{

	private static final long serialVersionUID = 1L;
	IMemberService service = MemberServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String usersPass = (String)req.getSession().getAttribute("usersPass");
		req.setAttribute("usersPass", usersPass);
		req.getRequestDispatcher("/views/member/pwModify.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String usersId = (String)req.getSession().getAttribute("usersId");
		
		String usersPassChn = (String)req.getParameter("pwChn");
		
		Map<String, String> pwModifyMap = new HashMap<String, String>();
		pwModifyMap.put("usersId", usersId);
		pwModifyMap.put("usersPass", usersPassChn);
		
		int cnt = service.pwModify(pwModifyMap);
		
		if( cnt == 1 ) {
			HttpSession session = req.getSession();
			session.removeAttribute("usersPass");
			session.setAttribute("usersPass", usersPassChn);
			String msg = "비밀번호 변경이 완료되었습니다.";
			String url = "/member/myPageHome.do";
			req.setAttribute("msg", msg);
			req.setAttribute("url", url);
			req.getRequestDispatcher("/views/alert.jsp").forward(req, resp);
		} else {
			String msg = "비밀번호 변경이 실패했습니다.";
			String url = "/member/myPageHome.do";
			req.setAttribute("msg", msg);
			req.setAttribute("url", url);
			req.getRequestDispatcher("/views/alert.jsp").forward(req, resp);
		}
		
	}
}
