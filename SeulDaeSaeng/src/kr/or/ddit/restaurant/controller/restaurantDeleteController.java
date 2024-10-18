package kr.or.ddit.restaurant.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.restaurant.service.IRestaurantService;
import kr.or.ddit.restaurant.service.RestaurantServiceImpl;

@WebServlet("/restaurant/reviewDelete.do")
public class restaurantDeleteController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private IRestaurantService service = RestaurantServiceImpl.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 
		 String restNo = req.getParameter("no");
		 int reviewNo = Integer.parseInt(req.getParameter("reviewNo"));
		 
		 int cnt = service.deleteReview(reviewNo);
		 req.setAttribute("no", restNo );
		 if(cnt>0) {
			 resp.sendRedirect(req.getContextPath() + "/restaurant/view.do?no="+restNo);
		 }
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 doGet(req, resp);
	}
}
