package kr.or.ddit.restaurant.controller;

import java.io.IOException;
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


@WebServlet("/restaurant/find.do")
public class restaurantFindController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IRestaurantService service = RestaurantServiceImpl.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, Object> cls = new HashMap<String, Object>();
		String mcls = request.getParameter("mcls");
		String scls = request.getParameter("scls");
		String order = request.getParameter("order");
		String page = request.getParameter("nowPage");
		
		if(mcls == null) mcls ="";
		if(scls == null) scls ="";
		if(order == null) order ="";
		if(page == null) page ="1";
		
		
		cls.put("mcls", mcls);
		cls.put("scls", scls);
		cls.put("order", order);
		
		String mclsName = "";
		String sclsName = "";
		
		if(!scls.equals("") && !scls.equals("all")) { 
			sclsName = service.selectSclsName(cls);
			mclsName = service.selectMclsName(cls);
		} else if(!mcls.equals("") && !mcls.equals("all")) {
			mclsName = service.selectMclsName(cls);
			List<RestaurantVO> sclsList = service.selectSclsList(mcls);
			request.setAttribute("sclsList", sclsList);
		}else {
			List<RestaurantVO> mclsList = service.selectMclsList();
			request.setAttribute("mclsList", mclsList);
		}	
		
		// 1. 보여주고 싶은 게시글 수
	  	int postperpage = 12;
	  	
	 	// 2. 보여주고 싶은 블럭 수
	  	int pageperblock = 5;
	 	
	 	// 3. 현재 페이지
	 	int nowPage = Integer.parseInt(page);
		
		// 4. 전체 게시물 수
		int totalpost = service.selectRestaurantTypeTotal(cls);
	  	
	 	// 5. 전체 페이지 수
	  	int totalpage = (int) Math.ceil((double) totalpost / postperpage);
	 	
	 	// 6. 현재 페이지의 첫번째 게시물의 번째 수
	  	int firstpost = (nowPage - 1) * postperpage;

	  	// 7. 전체 블록수
	  	int totalblock = (int) Math.ceil((double)totalpage / pageperblock);

	  	// 8. 현재 페이지가 포함된 블록 번호
	  	int block = (int) Math.ceil((double)nowPage / pageperblock);

	  	// 9. 현재 블록번호의 첫번째 페이지 번째 수
	  	int firstpage = (block - 1) * pageperblock + 1;  	
	  	

		cls.put("firstpost", firstpost+1);
		cls.put("postperpage", firstpost+postperpage);
	  	
		List<RestaurantVO> restList = service.selectRestaurantType(cls);
		
		request.setAttribute("totalpost", totalpost);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("totalblock", totalblock);
		request.setAttribute("block", block);
		request.setAttribute("firstpage", firstpage);
		request.setAttribute("pageperblock", pageperblock);
		request.setAttribute("nowPage", nowPage);
		
		if(mclsName == null) mclsName ="";
		if(sclsName == null) sclsName ="";		
		request.setAttribute("mclsName", mclsName);
		request.setAttribute("sclsName", sclsName);
		request.setAttribute("mcls", mcls);
		request.setAttribute("scls", scls);
		request.setAttribute("order", order);
		
		request.setAttribute("restList", restList);

		request.getRequestDispatcher("/views/restaurant/restaurantFind.jsp").forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
