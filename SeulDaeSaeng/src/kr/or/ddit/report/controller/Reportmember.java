package kr.or.ddit.report.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MyBatisUtil;



public class Reportmember extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/*
		 * SqlSession session = MyBatisUtil.getSqlSession();
		 * 
		 * try { // 매퍼를 호출하여 쿼리 실행 int boardNo =
		 * Integer.parseInt(req.getParameter("boardNo")); session.update("reportNo",
		 * boardNo); session.commit(); // 커밋
		 * 
		 * resp.sendRedirect(req.getContextPath() + "/views/adminPage.do");
		 * }catch(Exception e) { e.printStackTrace(); }finally { session.close(); // 세션
		 * 닫기 }
		 */
	}
}
