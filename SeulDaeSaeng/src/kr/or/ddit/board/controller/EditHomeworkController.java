package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.HomeworkVO;

@WebServlet("/homework/edit.do")
public class EditHomeworkController extends HttpServlet {
	
	private IBoardService boardService = BoardServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int hwNo = Integer.parseInt(req.getParameter("hwNo"));
		int levelChk = Integer.parseInt(req.getParameter("levelChk"));
		IBoardService boardService = BoardServiceImpl.getInstance();
		HomeworkVO hwDetail = boardService.getHwDetail(hwNo);
		
		req.setAttribute("levelChk", levelChk);
		req.setAttribute("hwDetail", hwDetail);
		 
		req.getRequestDispatcher("/views/board/hwEdit.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		int levelChk = Integer.parseInt(req.getParameter("levelChk"));
		int hwNo = Integer.parseInt(req.getParameter("hwNo"));
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		Date startDate = java.sql.Date.valueOf(req.getParameter("startDate"));
		Date endDate = java.sql.Date.valueOf(req.getParameter("endDate"));

	    Map<String,Object> parameter = new HashMap<>();
	    parameter.put("hwTitle",title);
	    parameter.put("hwCon",content);
	    parameter.put("hwStart",startDate);
	    parameter.put("hwEnd",endDate);
	    parameter.put("hwNo",hwNo);

		int status = boardService.editHomework(parameter);
				
		if(status > 0) { 	// 성공
			String msg = "정상적으로 수정되었습니다.";
			req.getSession().setAttribute("msg", msg);
			resp.sendRedirect("/homework/detail.do?hwNo=" + hwNo +"&levelChk=" + levelChk);
		}else {				// 실패
			String msg = "수정 실패했습니다.";
			req.getSession().setAttribute("msg", msg);
			req.getRequestDispatcher("/classTeacherBoard.do?levelChk=" + levelChk).forward(req, resp);
		}
	}
}
