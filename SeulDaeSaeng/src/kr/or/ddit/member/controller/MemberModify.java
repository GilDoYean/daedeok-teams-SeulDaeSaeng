package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.AddressVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.UsersVO;
import kr.or.ddit.util.MemberUtil;

@WebServlet("/member/modify.do")
public class MemberModify extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	IMemberService service = MemberServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberUtil.memberSelectOne(req);
		UsersVO usersVo = (UsersVO)req.getAttribute("usersVo");
		AddressVO addrVo = (AddressVO)req.getAttribute("addrVo");
		MemberVO memberVo = (MemberVO)req.getAttribute("memberVo");
		memberVo.setMemTel(memberVo.getMemTel().replace("-", ""));
		
		if(usersVo != null && memberVo != null && addrVo != null) {
			req.getRequestDispatcher("/views/member/modify.jsp").forward(req, resp);
		}else resp.sendRedirect("/views/index.jsp");
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		String usersId = (String)session.getAttribute("usersId");
		UsersVO usersVo = service.selectUsers(usersId);
		
		int usersNo = usersVo.getUsersNo();
		MemberVO memVo = service.selectMember(usersNo);
		
		
		// address 데이터 넣기
		int addrNo = memVo.getAddrNo();
		int addrPost = Integer.parseInt(req.getParameter("postAddr")); 
		String addrBasic = req.getParameter("addr1");
		String addrDetail = req.getParameter("addr2");
		
		
		AddressVO addrVo = new AddressVO();
		addrVo.setAddrNo(addrNo);
		addrVo.setAddrPost(addrPost);
		addrVo.setAddrBasic(addrBasic);
		addrVo.setAddrDetail(addrDetail);
		System.out.println(addrVo.toString());
		int addrCnt = service.addrModify(addrVo);
		
		// member 데이터 넣기
		String memName = req.getParameter("name");
		String memNick = req.getParameter("nickname");
		String memTel = req.getParameter("tel");
		String memEmail = req.getParameter("email");
		String memBirth = req.getParameter("birthday");
		
		MemberVO memberVo = new MemberVO();
		memberVo.setUsersNo(usersNo);
		memberVo.setMemName(memName);
		memberVo.setMemNick(memNick);
		memberVo.setMemBirth(memBirth);
		memberVo.setMemEmail(memEmail);
		memberVo.setMemTel(memTel);
		System.out.println(memberVo.toString());
		int memberCnt = service.memberModify(memberVo);
	
		
		if( addrCnt == 1 && memberCnt == 1) {
			System.out.println("member modify 모두 정상 응답");
			String msg = "회원수정이 완료되었습니다.";
			String url = "/member/myPageHome.do";
			req.setAttribute("msg", msg);
			req.setAttribute("url", url);
			req.getRequestDispatcher("/views/alert.jsp").forward(req, resp);
		} else {
			System.out.println("join insert 응답 실패");
			String msg = "회원수정이 실패했습니다.";
			String url = "/member/myPageHome.do";
			req.setAttribute("msg", msg);
			req.setAttribute("url", url);
			req.getRequestDispatcher("/views/alert.jsp").forward(req, resp);
		}

	}
	
}
