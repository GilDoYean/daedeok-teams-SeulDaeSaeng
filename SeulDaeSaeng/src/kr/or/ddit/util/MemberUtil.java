package kr.or.ddit.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.AddressVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.UsersVO;

public class MemberUtil {
	
	static IMemberService service = MemberServiceImpl.getInstance();

	/**
	 * 아이디, 회원정보, 주소 Vo를 request에 담아줌
	 * @param req Vo를 담을 객체
	 */
	public static void memberSelectOne(HttpServletRequest req) {
		HttpSession session = req.getSession();
		
		String usersId = (String)session.getAttribute("usersId");
		UsersVO usersVo = service.selectUsers(usersId);
		
		int usersNo = usersVo.getUsersNo();
		MemberVO memberVo = service.selectMember(usersNo);
		memberVo.setMemTel(memberVo.getMemTel().substring(0, 3) + "-"
						 + memberVo.getMemTel().substring(3, 7) + "-"
						 + memberVo.getMemTel().substring(7, 11));
		memberVo.setMemBirth(memberVo.getMemBirth().substring(0,10));
		
		int addrNo = memberVo.getAddrNo();
		AddressVO addrVo = service.selectAddr(addrNo);
		
		req.setAttribute("usersVo", usersVo);
		req.setAttribute("memberVo", memberVo);
		req.setAttribute("addrVo", addrVo);
	}
	
	public static void memberMessage(HttpServletRequest req, HttpServletResponse resp, int cnt, String successMsg, String successUrl, String failMsg, String failUrl) throws ServletException, IOException {
		if(cnt == 1) {
			String msg = successMsg;
			String url = successUrl;
			req.setAttribute("msg", msg);
			req.setAttribute("url", url);
			req.getRequestDispatcher("/views/alert.jsp").forward(req, resp);
		} else {
			String msg = failMsg;
			String url = failUrl;
			req.setAttribute("msg", msg);
			req.setAttribute("url", url);
			req.getRequestDispatcher("/views/alert.jsp").forward(req, resp);
		}
		
	}
	
	public static void memberMessage(HttpServletRequest req, HttpServletResponse resp, String msg, String url) throws ServletException, IOException {
			req.setAttribute("msg", msg);
			req.setAttribute("url", url);
			req.getRequestDispatcher("/views/alert.jsp").forward(req, resp);
	}
	
	public static void boardLevelKr(BoardVO boardVo) {
		
		int level = boardVo.getBoardLevel();
		
		if(level == 1) boardVo.setBoardLevelKr("자유");
		if(level == 2) boardVo.setBoardLevelKr("공부");
		if(level == 3) boardVo.setBoardLevelKr("공지");
		if(level == 301) boardVo.setBoardLevelKr("301호");
		if(level == 302) boardVo.setBoardLevelKr("302호");
		if(level == 303) boardVo.setBoardLevelKr("303호");
		if(level == 304) boardVo.setBoardLevelKr("304호");
		if(level == 305) boardVo.setBoardLevelKr("305호");
		
	}
	
	public static void boardLevelKr(List<BoardVO> boardList) {
		
		int cnt = 0;
		for(BoardVO boardVo : boardList) {
			
			int level = boardVo.getBoardLevel();
			
			if(level == 1) boardVo.setBoardLevelKr("자유");
			if(level == 2) boardVo.setBoardLevelKr("공부");
			if(level == 3) boardVo.setBoardLevelKr("공지");
			if(level == 301) boardVo.setBoardLevelKr("301호");
			if(level == 302) boardVo.setBoardLevelKr("302호");
			if(level == 303) boardVo.setBoardLevelKr("303호");
			if(level == 304) boardVo.setBoardLevelKr("304호");
			if(level == 305) boardVo.setBoardLevelKr("305호");
			boardList.set(cnt++, boardVo);
		}
	}
	
}
