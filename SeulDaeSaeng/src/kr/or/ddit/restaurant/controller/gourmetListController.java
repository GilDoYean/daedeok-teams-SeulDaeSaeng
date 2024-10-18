package kr.or.ddit.restaurant.controller;

import java.io.*;

import javax.lang.model.util.Elements;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.If;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.w3c.dom.NodeList;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.restaurant.service.IRestaurantService;
import kr.or.ddit.restaurant.service.RestaurantServiceImpl;
import kr.or.ddit.restaurant.vo.RestaurantVO;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@WebServlet("/gourmet/list.do")
public class gourmetListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IRestaurantService service = RestaurantServiceImpl.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<MemberVO> likeMemList = service.getLikeMemList();
		request.setAttribute("likeMemList", likeMemList);
		request.getRequestDispatcher("/views/restaurant/gourmetList.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
	}
}
