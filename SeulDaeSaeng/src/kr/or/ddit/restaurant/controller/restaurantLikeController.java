package kr.or.ddit.restaurant.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.restaurant.service.IRestaurantService;
import kr.or.ddit.restaurant.service.RestaurantServiceImpl;


@WebServlet("/restaurant/like.do")
public class restaurantLikeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IRestaurantService service = RestaurantServiceImpl.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String bizNo = request.getParameter("bizNo");
		System.out.println(bizNo);
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		System.out.println(userNo);
		String insertYN = request.getParameter("insertYN");
		System.out.println(insertYN);
		String myLike = "";

		Map<String, Object> likeInfo = new HashMap<String, Object>();
		if(request.getParameter("myLike").equals("checked")) {
			myLike = "N";
			likeInfo.put("myLike", myLike);
		}else if(request.getParameter("myLike").equals("")) {
			myLike = "Y";
			likeInfo.put("myLike", myLike);
		}else if(request.getParameter("myLike")==null ||request.getParameter("myLike").isEmpty()) {} 
		System.out.println(myLike);

		likeInfo.put("restBizno", bizNo);
		likeInfo.put("userNo", userNo);
		
		int status = -1;

		if(insertYN.equals("insert")) {			
			status = service.insertMyLike(likeInfo);
		}else if(insertYN.equals("update")) {
			status = service.updateMyLike(likeInfo);
		}
		
		if(status > 0) { 	// 성공
			response.sendRedirect(request.getContextPath()+"/restaurant/view.do?no=" + bizNo);

		}else {				// 실패
			request.setAttribute("no", bizNo);
			request.getRequestDispatcher("/views/restaurant/restaurantView.jsp").forward(request, response);
		}

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
