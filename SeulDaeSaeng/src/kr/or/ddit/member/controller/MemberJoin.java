package kr.or.ddit.member.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.FileDetailVO;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.AddressVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.UsersVO;
import kr.or.ddit.util.MemberUtil;

@WebServlet("/member/join.do")
public class MemberJoin extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	IMemberService service = MemberServiceImpl.getInstance();
	IBoardService boardService = BoardServiceImpl.getInstance();
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/member/join.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// users 데이터 넣기
		int usersRole = Integer.parseInt(req.getParameter("classify")); 
		String usersId = req.getParameter("id");
		String usersPass = req.getParameter("pw");
		String usersYn = "S";
		
		UsersVO usersVo = new UsersVO();
		usersVo.setUsersRole(usersRole);
		usersVo.setUsersId(usersId);
		usersVo.setUsersPass(usersPass);
		usersVo.setUsersYn(usersYn);
		System.out.println(usersVo.toString());
		int usersCnt = service.usersJoin(usersVo);
		
		// address 데이터 넣기
		int addrPost = Integer.parseInt(req.getParameter("postAddr")); 
		String addrBasic = req.getParameter("addr1");
		String addrDetail = req.getParameter("addr2");
		int addrLevel = 1; 
		
		AddressVO addrVo = new AddressVO();
		addrVo.setAddrPost(addrPost);
		addrVo.setAddrBasic(addrBasic);
		addrVo.setAddrDetail(addrDetail);
		addrVo.setAddrLevel(addrLevel);
		System.out.println(addrVo.toString());
		int addrCnt = service.addrJoin(addrVo);
		
		// member 데이터 넣기
		String memName = req.getParameter("name");
		String memNick = req.getParameter("nickname");
		String memBirth = req.getParameter("birthday");
		String memEmail = req.getParameter("email");
		String memTel = req.getParameter("tel");
		String memClass = req.getParameter("validationCustom04");
		
		List<MemberVO> memList = service.getAllMember();
		List<AddressVO> addrList = service.getAllAddr();
		
		MemberVO memberVo = new MemberVO();
//		memberVo.setUsersNo(memList.get(0).getUsersNo());
		memberVo.setMemName(memName);
		memberVo.setMemNick(memNick);
		memberVo.setMemBirth(memBirth);
		memberVo.setMemEmail(memEmail);
		memberVo.setMemTel(memTel);
		memberVo.setMemClass(memClass);
//		memberVo.setAddrNo(addrList.get(0).getAddrNo());
		System.out.println(memberVo.toString());
		int memberCnt = service.memberJoin(memberVo);
		
		/*
		 * /////////////////////////////////////////////////// Part filePart =
		 * req.getPart("file"); int levelChk = 5555; if(filePart != null &&
		 * filePart.getSize() != 0) { int fileStatus =
		 * boardService.insertBoardFile(filePart,levelChk); List<FileDetailVO> fileList
		 * = boardService.getFileList(levelChk); req.setAttribute("fileList", fileList);
		 * }
		 * 
		 * /////////////////////////////////////////////////
		 */		
		if(usersCnt == 1 && addrCnt == 1 && memberCnt == 1) {
			System.out.println("join insert 모두 정상 응답");
			String msg = "회원가입이 완료되었습니다.";
			String url = "/main.do";
			req.setAttribute("msg", msg);
			req.setAttribute("url", url);
			req.getRequestDispatcher("/views/alert.jsp").forward(req, resp);
		} else System.out.println("join insert 응답 실패");
		
//		System.out.println("classify => " + req.getParameter("classify"));
//		System.out.println("id => " + req.getParameter("id"));
//		System.out.println("pw => " + req.getParameter("pw"));
//		System.out.println("postAddr => " + req.getParameter("postAddr"));
//		System.out.println("addr1 => " + req.getParameter("addr1"));
//		System.out.println("addr2 => " + req.getParameter("addr2"));
//		System.out.println("name => " + req.getParameter("name"));
//		System.out.println("nickname => " + req.getParameter("nickname"));
//		System.out.println("birthday => " + req.getParameter("birthday"));
//		System.out.println("email => " + req.getParameter("email"));
//		System.out.println("tel => " + req.getParameter("tel"));
//		System.out.println("validationCustom04 => " + req.getParameter("validationCustom04"));
		
	}
}
