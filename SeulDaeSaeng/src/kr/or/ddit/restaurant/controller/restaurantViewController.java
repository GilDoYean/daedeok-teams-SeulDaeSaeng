package kr.or.ddit.restaurant.controller;

import java.io.*;

import javax.lang.model.util.Elements;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.If;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.w3c.dom.NodeList;

import kr.or.ddit.restaurant.service.IRestaurantService;
import kr.or.ddit.restaurant.service.RestaurantServiceImpl;
import kr.or.ddit.restaurant.vo.RestaurantVO;
import kr.or.ddit.restaurant.vo.restLikeVO;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@WebServlet("/restaurant/view.do")
public class restaurantViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IRestaurantService service = RestaurantServiceImpl.getInstance();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String restBizno = request.getParameter("no");
		
		RestaurantVO restDetails = service.selectRest(restBizno);
		List<RestaurantVO> restReviewList = service.selectRestReview(restBizno);
		
		List<restLikeVO> restLikeList = service.restLikeList(restBizno);
		request.setAttribute("restLikeList", restLikeList);
		
		request.setAttribute("restDetails", restDetails);
		request.setAttribute("restReviewList", restReviewList);
		
		String restName = restDetails.getName();
		String dongName = extractDongName(restDetails.getAddrBasic());
		
		String clientId = "1ztHLrOOajdmUf0Qbfma"; //애플리케이션 클라이언트 아이디
        String clientSecret = "mhSye7LFfH"; //애플리케이션 클라이언트 시크릿

        String text = null;
        try {
            text = URLEncoder.encode("대전 "+dongName+"동 "+restName+"", "UTF-8");
        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException("검색어 인코딩 실패",e);
        }
        
        String blogURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text+"&display=12"; // XML 결과(Blog)
        String imgURL = "https://openapi.naver.com/v1/search/image.xml?query="+ text+"&display=12&sort=sim"; // XML 결과(Img)

        
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        
        String responseBlog = get(blogURL,requestHeaders);
        String responseImg = get(imgURL,requestHeaders);

		request.setAttribute("responseBlog", responseBlog);
		request.setAttribute("responseImg", responseImg);
		
		try {			
        	Document doc = Jsoup.connect("https://m.search.naver.com/search.naver?sm=mtp_hty.top&where=m&query="+text).get();
        	org.jsoup.select.Elements place = doc.select("#place-main-section-root");
        	if(place != null && !place.isEmpty()) {
            	
	        	String menuName1 = "";
	        	String menuName2 = "";
	        	String menuName3 = "";
	        	String menuName4 = "";
	        	
	        	String menuPrice1 = "";
	        	String menuPrice2 = "";
	        	String menuPrice3 = "";
	        	String menuPrice4 = "";

            	org.jsoup.select.Elements menuNameType1 = doc.select(".VQvNX");
            	org.jsoup.select.Elements menuPriceType1 = doc.select(".gl2cc em");

            	org.jsoup.select.Elements menuNameType2 = doc.select(".JLkY7 .y0EHb .A_cdD");
            	org.jsoup.select.Elements menuPriceType2 = doc.select(".JLkY7 .CLSES em");

            	org.jsoup.select.Elements menuNameType3 = doc.select(".ihmWt");
            	org.jsoup.select.Elements menuPriceType3 = doc.select(".mkBm3 em");
            	
            	if (menuNameType1 != null && !menuNameType1.isEmpty()) {
            	    menuName1 = menuNameType1.size() > 0 ? menuNameType1.get(0).text() : "";
            	    menuName2 = menuNameType1.size() > 1 ? menuNameType1.get(1).text() : "";
            	    menuName3 = menuNameType1.size() > 2 ? menuNameType1.get(2).text() : "";
            	    menuName4 = menuNameType1.size() > 3 ? menuNameType1.get(3).text() : "";
            	    
            	    menuPrice1 = menuPriceType1.size() > 0 ? menuPriceType1.get(0).text() : "";
            	    menuPrice2 = menuPriceType1.size() > 1 ? menuPriceType1.get(1).text() : "";
            	    menuPrice3 = menuPriceType1.size() > 2 ? menuPriceType1.get(2).text() : "";
            	    menuPrice4 = menuPriceType1.size() > 3 ? menuPriceType1.get(3).text() : "";
            	} else if(menuNameType2 != null && !menuNameType2.isEmpty()) {
            	    menuName1 = menuNameType2.size() > 0 ? menuNameType2.get(0).text() : "";
            	    menuName2 = menuNameType2.size() > 1 ? menuNameType2.get(1).text() : "";
            	    menuName3 = menuNameType2.size() > 2 ? menuNameType2.get(2).text() : "";
            	    menuName4 = menuNameType2.size() > 3 ? menuNameType2.get(3).text() : "";
            	    
            	    menuPrice1 = menuPriceType2.size() > 0 ? menuPriceType2.get(0).text() : "";
            	    menuPrice2 = menuPriceType2.size() > 1 ? menuPriceType2.get(1).text() : "";
            	    menuPrice3 = menuPriceType2.size() > 2 ? menuPriceType2.get(2).text() : "";
            	    menuPrice4 = menuPriceType2.size() > 3 ? menuPriceType2.get(3).text() : "";
            	} else if(menuNameType3 != null && !menuNameType3.isEmpty()) {
            	    menuName1 = menuNameType3.size() > 0 ? menuNameType3.get(0).text() : "";
            	    menuName2 = menuNameType3.size() > 1 ? menuNameType3.get(1).text() : "";
            	    menuName3 = menuNameType3.size() > 2 ? menuNameType3.get(2).text() : "";
            	    menuName4 = menuNameType3.size() > 3 ? menuNameType3.get(3).text() : "";
            	    
            	    menuPrice1 = menuPriceType3.size() > 0 ? menuPriceType3.get(0).text() : "";
            	    menuPrice2 = menuPriceType3.size() > 1 ? menuPriceType3.get(1).text() : "";
            	    menuPrice3 = menuPriceType3.size() > 2 ? menuPriceType3.get(2).text() : "";
            	    menuPrice4 = menuPriceType3.size() > 3 ? menuPriceType3.get(3).text() : "";
            	}
    	        
				request.setAttribute("menuName1", menuName1);
				request.setAttribute("menuName2", menuName2);
				request.setAttribute("menuName3", menuName3);
				request.setAttribute("menuName4", menuName4);
				
				request.setAttribute("menuPrice1", menuPrice1);
				request.setAttribute("menuPrice2", menuPrice2);
				request.setAttribute("menuPrice3", menuPrice3);
				request.setAttribute("menuPrice4", menuPrice4); 
				
				org.jsoup.select.Elements addr = doc.getElementsByAttributeValue("class", "LDgIH");
				org.jsoup.select.Elements naverMap = doc.getElementsByAttributeValue("class", "S8peq");
            	org.jsoup.select.Elements nowSales = doc.select(".y6tNq .A_cdD");
            	org.jsoup.select.Elements phoneNum = doc.getElementsByAttributeValue("class", "xlx7Q");
            	org.jsoup.select.Elements addInfo = doc.getElementsByAttributeValue("class", "xPvPE");
            	
            	if(addr!=null && !addr.isEmpty()) {
            		String restAddr = addr.get(0)!=null ? addr.get(0).text() : "";
    	    		request.setAttribute("restAddr", restAddr);
            	}
            	if(naverMap!=null && !naverMap.isEmpty()) {
            		String restNaverMap = naverMap.get(0)!=null ? naverMap.get(0).select("a").attr("href"): "";
    	    		request.setAttribute("restNaverMap", restNaverMap);
            	}
            	if(nowSales!=null && !nowSales.isEmpty()) {
            		String restNowSales = nowSales.get(0)!=null ? nowSales.get(0).select("em").text() : "";
            		String restSalesTime = nowSales.get(0)!=null ? nowSales.get(0).select("time").text() : "";
    	    		request.setAttribute("restNowSales", restNowSales);
    	    		request.setAttribute("restSalesTime", restSalesTime);
            	}
            	if(phoneNum!=null && !phoneNum.isEmpty()) {
            		String restPhoneNum = phoneNum.get(0)!=null ? phoneNum.get(0).text() : "";
    	    		request.setAttribute("restPhoneNum", restPhoneNum);
            	}
            	if(addInfo!=null && !addInfo.isEmpty()) {
            		String restAddInfo = addInfo.get(0)!=null ? addInfo.get(0).text() : "";
    	    		request.setAttribute("restAddInfo", restAddInfo);     
            	}
        	} 
        	
		} catch (IOException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("/views/restaurant/restaurantView.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 오류 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000); // 5초 타임아웃 설정
            connection.setReadTimeout(5000); // 5초 타임아웃 설정
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }
            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }
    
    public static String extractDongName(String address) {
        // 정규 표현식 패턴 생성
        Pattern pattern = Pattern.compile("(?<=대전광역시 중구 ).+?(?=동)");

        // 주소 문자열이 null인지 체크
        if (address == null) {
            // null일 경우 빈 문자열 반환
            return "";
        }

        // 주소 문자열과 패턴 매칭
        Matcher matcher = pattern.matcher(address);

        // 매칭된 결과가 있다면
        if (matcher.find()) {
            // 동 이름 추출
            return matcher.group();
        } else {
            // 추출 실패 시 빈 문자열 반환
            return "";
        }
    }
}
