package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.restaurant.service.IRestaurantService;
import kr.or.ddit.restaurant.service.RestaurantServiceImpl;
import kr.or.ddit.restaurant.vo.RestaurantVO;
import kr.or.ddit.util.MemberUtil;

@WebServlet("/member/myReview.do")
public class MyReview extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	IRestaurantService service = RestaurantServiceImpl.getInstance();
	IMemberService memberService = MemberServiceImpl.getInstance();

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
		int usersNo = -1;
		usersNo = (int) req.getSession().getAttribute("usersNo");
		String usersId = (String) req.getSession().getAttribute("usersId");
		
        if (usersNo != -1 ) {
            List<RestaurantVO> myReviewList = service.getReviewsByUserId(usersNo);
            MemberVO memberDetail = memberService.getMemDetail(usersId);
            req.setAttribute("myReviewList", myReviewList);
            req.setAttribute("memberDetail", memberDetail);
            req.getRequestDispatcher("/views/member/myReviewList.jsp").forward(req, resp);
        } else {
            MemberUtil.memberMessage(req, resp, "오류가 발생했습니다.", "/main.do");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
