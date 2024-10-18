package kr.or.ddit.board.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.HomeworkVO;

@WebServlet("/homework/upload.do")
public class UploadHomeworkController extends HttpServlet{

	private IBoardService service = BoardServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int levelChk = Integer.parseInt(req.getParameter("classNo"));
		req.setAttribute("levelChk", levelChk);
		req.getRequestDispatcher("/views/board/write.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		int levelChk = Integer.parseInt(req.getParameter("levelChk"));
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		Date startDate = java.sql.Date.valueOf(req.getParameter("startDate"));
		Date endDate = java.sql.Date.valueOf(req.getParameter("endDate"));

	    Map<String,Object> parameter = new HashMap<>();
	    parameter.put("hwTitle",title);
	    parameter.put("hwCon",content);
	    parameter.put("hwStart",startDate);
	    parameter.put("hwEnd",endDate);
	    parameter.put("hwClass",levelChk);
	    parameter.put("usersNo",3);

		int status = service.insertHomework(parameter);
		List<HomeworkVO> hw = service.getHwList(levelChk);
		HomeworkVO recentHw = hw.get(0);
				
		if(status > 0) { 	// 성공
			resp.sendRedirect(req.getContextPath() + "/homework/detail.do?hwNo=" + recentHw.getHwNo());
		}else {				// 실패
			req.getRequestDispatcher("/views/board/write.jsp").forward(req, resp);
		}
	}
}
