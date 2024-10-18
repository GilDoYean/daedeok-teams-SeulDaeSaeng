package kr.or.ddit.main.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.StoryVO;
import kr.or.ddit.restaurant.service.IRestaurantService;
import kr.or.ddit.restaurant.service.RestaurantServiceImpl;
import kr.or.ddit.restaurant.vo.RestaurantVO;
import kr.or.ddit.restaurant.vo.ReviewVO;

@WebServlet("/main.do")
public class MainController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private IRestaurantService restService = RestaurantServiceImpl.getInstance();
	private IBoardService boardService = BoardServiceImpl.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<RestaurantVO> sclsList = restService.selectSclsList();
		req.setAttribute("sclsList", sclsList);
		Map<String, Object> cls = new HashMap<String, Object>();
		cls.put("mcls", "");
		cls.put("scls", "");
		
		cls.put("order", "likeUp");
		cls.put("firstpost", 1);
		cls.put("postperpage", 4);
		List<RestaurantVO> restLikeList = restService.selectRestaurantType(cls);
		req.setAttribute("restLikeList", restLikeList);
		
		List<RestaurantVO> restReviewList = restService.restReviewList();
		req.setAttribute("restReviewList", restReviewList);
		
	    List<StoryVO> storyList = boardService.allStoryList();
	    req.setAttribute("storyList", storyList);
		
		System.out.println("메인 페이지 접속");
		
		req.getRequestDispatcher("/views/index.jsp").forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 doGet(req, resp);
	}
}