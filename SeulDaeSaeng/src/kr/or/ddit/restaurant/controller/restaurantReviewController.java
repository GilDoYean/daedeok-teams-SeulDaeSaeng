package kr.or.ddit.restaurant.controller;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.restaurant.service.IRestaurantService;
import kr.or.ddit.restaurant.service.RestaurantServiceImpl;
import kr.or.ddit.restaurant.vo.RestaurantVO;


@WebServlet("/restaurant/review.do")
public class restaurantReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IRestaurantService service = RestaurantServiceImpl.getInstance();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String restBizno = request.getParameter("no");
		String order = request.getParameter("order");
		
		if (order == null) {
	        order = "latest"; // 기본 값 설정
	    }
		
		Map<String, Object> cls = new HashMap<String, Object>();
		
		cls.put("restBizno", restBizno);
		cls.put("order", order);
		
		RestaurantVO restDetails = service.selectRest(restBizno);
		List<RestaurantVO> restReviewList = service.selectRestReviewAll(cls);
		
		request.setAttribute("order", order);
		request.setAttribute("restDetails", restDetails);
		request.setAttribute("restReviewList", restReviewList);

		request.getRequestDispatcher("/views/restaurant/restaurantReview.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
