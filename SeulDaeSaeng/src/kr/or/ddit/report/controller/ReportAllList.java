package kr.or.ddit.report.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.report.service.IReportService;
import kr.or.ddit.report.service.ReportServiceImpl;

@WebServlet("/views/reportAllList.do")
public class ReportAllList extends HttpServlet{
	
	IReportService service = ReportServiceImpl.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<BoardVO> repallList = service.selectallList();
		
		req.setAttribute("repallList", repallList);
		
		req.getRequestDispatcher("/views/admin/reportAllList.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
