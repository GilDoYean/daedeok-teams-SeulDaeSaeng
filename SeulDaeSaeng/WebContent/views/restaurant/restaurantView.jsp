<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="kr.or.ddit.restaurant.vo.RestaurantVO"%>
<%@page import="kr.or.ddit.restaurant.vo.restLikeVO"%>
<%@ page import="javax.xml.parsers.*" %>
<%@ page import="java.io.ByteArrayInputStream" %>
<%@ page import="org.w3c.dom.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.net.URL" %>

<%@include file="/header.jsp" %>
<%
	RestaurantVO restDetails = (RestaurantVO) request.getAttribute("restDetails");
	List<RestaurantVO> restReviewList = (List<RestaurantVO>) request.getAttribute("restReviewList");
	
	MemberVO memDetail = (MemberVO) session.getAttribute("memDetail") != null ? (MemberVO) session.getAttribute("memDetail") : new MemberVO();
  	UsersVO usersDetail = (UsersVO) session.getAttribute("usersDetail") != null ? (UsersVO) session.getAttribute("usersDetail") : new UsersVO();
  	
  	List<restLikeVO> restLikeList = (List<restLikeVO>) request.getAttribute("restLikeList")!= null ? (List<restLikeVO>) request.getAttribute("restLikeList") : new ArrayList<restLikeVO>();
	String insertYN = "insert";
  	String myLike = "";
	String myLikeBtn = "outline-";
	

	System.out.println(restLikeList.size());
	System.out.println(usersDetail.getUsersNo());
	
  	for (int i = 0; i < restLikeList.size(); i++) {
  		restLikeVO likeVo = (restLikeVO) restLikeList.get(i);
  		int likeUserNo = (int) likeVo.getUserNo();
  		
  		System.out.println(likeUserNo);
  		
  		if(likeUserNo == usersDetail.getUsersNo()){
  	  		System.out.println(likeUserNo);
  	  		System.out.println(usersDetail.getUsersNo());
  			insertYN = "update";
  			if(likeVo.getLikeYn().equals("Y")){
  	  	  		System.out.println(0000);
  	  	  		
  				myLike="checked";
  				myLikeBtn="";
  			}
  		}
  	}

	String responseBlog = (String) request.getAttribute("responseBlog");
	String responseImg = (String) request.getAttribute("responseImg");
	
	String menuName1 = request.getAttribute("menuName1")!=null ? (String) request.getAttribute("menuName1") : "";
	String menuName2 = request.getAttribute("menuName2")!=null ? (String) request.getAttribute("menuName2") : "";
	String menuName3 = request.getAttribute("menuName3")!=null ? (String) request.getAttribute("menuName3") : "";
	String menuName4 = request.getAttribute("menuName4")!=null ? (String) request.getAttribute("menuName4") : "";
	
	String menuPrice1 = request.getAttribute("menuPrice1")!=null ? (String) request.getAttribute("menuPrice1") : "";
	String menuPrice2 = request.getAttribute("menuPrice2")!=null ? (String) request.getAttribute("menuPrice2") : "";
	String menuPrice3 = request.getAttribute("menuPrice3")!=null ? (String) request.getAttribute("menuPrice3") : "";
	String menuPrice4 = request.getAttribute("menuPrice4")!=null ? (String) request.getAttribute("menuPrice4") : "";

	String restAddr = request.getAttribute("restAddr")!=null ? (String) request.getAttribute("restAddr") : "";
	
	String restNaverMap = request.getAttribute("restNaverMap")!=null ? (String) request.getAttribute("restNaverMap"): "";
	String restNowSales = request.getAttribute("restNowSales")!=null ? (String) request.getAttribute("restNowSales") : "";
	String restSalesTime = request.getAttribute("restSalesTime")!=null ? (String) request.getAttribute("restSalesTime") : "";
	String restPhoneNum = request.getAttribute("restPhoneNum")!=null ? (String) request.getAttribute("restPhoneNum") : "";
	String restAddInfo = request.getAttribute("restAddInfo")!=null ? (String) request.getAttribute("restAddInfo") : "";	
	
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	DocumentBuilder builder = null;
	try {
        builder = factory.newDocumentBuilder();
    } catch (Exception e) {
        e.printStackTrace();
    }

    Document blogDocument = null;
    Document imageDocument = null;
    if (responseBlog != null && responseImg != null) {
        try {
            blogDocument = builder.parse(new ByteArrayInputStream(responseBlog.getBytes()));
            imageDocument = builder.parse(new ByteArrayInputStream(responseImg.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Element blogRootElement = null;
    Element imageRootElement = null;
    if (blogDocument != null) {
        blogRootElement = blogDocument.getDocumentElement();
    }
    if (imageDocument != null) {
        imageRootElement = imageDocument.getDocumentElement();
    }

%>

    <main data-aos="fade" data-aos-delay="700">
      <!-- ======= End Page Header ======= -->
      <div class="page-header restaurant d-flex align-items-center">
        <div class="container position-relative">
          <div class="row d-flex justify-content-center">
            <div>
              <nav style="--bs-breadcrumb-divider: '|'; " aria-label="breadcrumb">
                <ol class="breadcrumb">
                  <li class="breadcrumb-item">
                    <a href="<%=request.getContextPath() %>/restaurant/find.do?mcls=all">식당찾기</a>
                  </li>
                  <li class="breadcrumb-item">
                    <a href="<%=request.getContextPath() %>/restaurant/find.do?mcls=<%=restDetails.getMcls()%>"><%=restDetails.getMclsName()%></a>
                  </li>
                  <li class="breadcrumb-item">
                    <a href="<%=request.getContextPath() %>/restaurant/find.do?mcls=<%=restDetails.getMcls()%>&scls=<%=restDetails.getScls()%>"><%=restDetails.getSclsName()%></a>
                  </li>
                  <li class="breadcrumb-item active" aria-current="page"><%=restDetails.getName()%></li>
                </ol>
              </nav>
              <h2 class="pb-2"><%=restDetails.getName()%></h2>
              <div class="d-flex justify-content-between align-items-center">
                <p class="stars fs-4">
                <%
                String score = (int) restDetails.getAvgReviewStar()!=-1 ? ""+ (int) restDetails.getAvgReviewStar() : "리뷰없음";
                if(score.equals("10")){
                	score = "5";
                %>
                	<i class="bi bi-star-fill"></i> <i class="bi bi-star-fill"></i> <i class="bi bi-star-fill"></i> <i class="bi bi-star-fill"></i> <i class="bi bi-star-fill"></i>
                <%
                }else if(score.equals("9")){
                	score = "4.5";
                %> 
                	<i class="bi bi-star-fill"></i> <i class="bi bi-star-fill"></i> <i class="bi bi-star-fill"></i> <i class="bi bi-star-fill"></i> <i class="bi bi-star-half"></i>
                <%
                }else if(score.equals("8")){
                	score = "4";
                %> 
                	<i class="bi bi-star-fill"></i> <i class="bi bi-star-fill"></i> <i class="bi bi-star-fill"></i> <i class="bi bi-star-fill"></i> <i class="bi bi-star"></i>
                <%
                }else if(score.equals("7")){
                	score = "3.5";
                %> 
                	<i class="bi bi-star-fill"></i> <i class="bi bi-star-fill"></i> <i class="bi bi-star-fill"></i> <i class="bi bi-star-half"></i> <i class="bi bi-star"></i>
                <%
                }else if(score.equals("6")){
                	score = "3";
                %> 
                	<i class="bi bi-star-fill"></i> <i class="bi bi-star-fill"></i> <i class="bi bi-star-fill"></i> <i class="bi bi-star"></i> <i class="bi bi-star"></i>
                <%
                }else if(score.equals("5")){
                	score = "2.5";
                %> 
                	<i class="bi bi-star-fill"></i> <i class="bi bi-star-fill"></i> <i class="bi bi-star-half"></i> <i class="bi bi-star"></i> <i class="bi bi-star"></i>
                <%
                }else if(score.equals("4")){
                	score = "2";
                %> 
                	<i class="bi bi-star-fill"></i> <i class="bi bi-star-fill"></i> <i class="bi bi-star"></i> <i class="bi bi-star"></i> <i class="bi bi-star"></i>
                <%
                }else if(score.equals("3")){
                	score = "1.5";
                %> 
                	<i class="bi bi-star-fill"></i> <i class="bi bi-star-half"></i> <i class="bi bi-star"></i> <i class="bi bi-star"></i> <i class="bi bi-star"></i>
                <%
                }else if(score.equals("2")){
                	score = "1";
                %> 
                	<i class="bi bi-star-fill"></i> <i class="bi bi-star"></i> <i class="bi bi-star"></i> <i class="bi bi-star"></i> <i class="bi bi-star"></i>
                <%
                }else if(score.equals("1")){
                	score = "0.5";
                %> 
                	<i class="bi bi-star-half"></i> <i class="bi bi-star"></i> <i class="bi bi-star"></i> <i class="bi bi-star"></i> <i class="bi bi-star"></i>
                <%
                }else if(score.equals("0")){
                	score = "0";
                %>
                	<i class="bi bi-star"></i> <i class="bi bi-star"></i> <i class="bi bi-star"></i> <i class="bi bi-star"></i> <i class="bi bi-star"></i>
                <%}else{%> <%} %> 
                  <b>(<%=score %>) <span>(<%=restDetails.getTotalReview()%>명의 평가)</span>
                  </b>
                </p>
                <p class="like d-flex align-items-center gap-3">
                  <span style="color: var(--bs-red)">
                    <i class="bi bi-heart-fill"></i> <%=restDetails.getLikeCount() %>명 좋아하는 중 
                  </span>
                  <% if(id!=null && !id.isEmpty() && usersDetail != null && usersDetail.getUsersNo()!=0){ %>                	  
                  <input type="checkbox" id="likeBtn" <%=myLike%>>
                  <a href="<%=request.getContextPath() %>/restaurant/like.do?bizNo=<%=restDetails.getRestBizno()%>&userNo=<%=usersDetail.getUsersNo()%>&insertYN=<%=insertYN%>&myLike=<%=myLike %>" class="btn btn-<%=myLikeBtn%>danger d-flex align-items-center gap-2" for="btn-check">
                    <span class="true"> <i class="bi bi-heart-fill"></i> 좋아요 </span>
                    <span class="false"> <i class="bi bi-heart"></i> 취소 </span>
                  </a>
                  <% } %>
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- End Page Header -->
      <section id="restaurantView" class="pricing about d-flex flex-column gap-4">
        <div class="container content">
          <div class="row gap-3">
            <div class="col-lg-6">
			   	<script>
				   	function initMap() {
				   	  const map = new google.maps.Map(document.getElementById("map"), {
				   	    center: { lat: <%=restDetails.getLat()%>, lng: <%=restDetails.getLon()%> },
				   	    zoom: 18,
				   	    mapId: "4504f8b37365c3d0",
				   	  });
				   	  const priceTag = document.createElement("div");
	
				   	  priceTag.className = "price-tag";
				   	  priceTag.textContent = "<%=restDetails.getName()%>";
	
				   	  const markerView = new google.maps.marker.AdvancedMarkerView({  map,
				   	    position: { lat: <%=restDetails.getLat()%>, lng: <%=restDetails.getLon()%> },
				   	    content: priceTag,
				   	  });
				   	}
	
				   	window.initMap = initMap;
			    </script>
              	<div id="map" class="w-100 h-100"></div>
            </div>
            <div class="col-lg-5">
              <ul>
              	<% if(!restAddr.equals("")){ %>
                <li>
                  <i class="bi bi-geo-alt-fill"></i>
                  <strong>주소 </strong>
                  <span>
                  	<%=restAddr %>
              		<% if(!restNaverMap.equals("")){ %>
              			<a href="<%=restNaverMap%>">네이버지도</a>
              		<% } %>
                  </span>
                </li>              	
              	<% } else { %>
                <li>
                  <i class="bi bi-geo-alt-fill"></i>
                  <strong>주소 </strong>
                  <span>
                  	<%=restDetails.getAddrBasic()%>
              		<% if(!restNaverMap.equals("")){ %>
              			<a href="<%=restNaverMap%>">네이버지도</a>
              		<% } %>
                  </span>
                </li>             		
              	<% } %>
       			<% if(!restPhoneNum.equals("")){ %>
                <li>
                  <i class="bi bi-telephone-fill"></i>
                  <strong>전화번호 </strong>
                  <a href="tel:+82<%=restPhoneNum %>"><%=restPhoneNum %></a>
                </li>       				
       			<% } %>
          		<% if(!restNowSales.equals("")){ %>
                <li>
                  <i class="bi bi-calendar2-check-fill"></i>
                  <strong><%=restNowSales %> </strong>
          		  <%if(!restSalesTime.equals("")){%>
          			  <span><%=restSalesTime%></span>
          		  <%}%>
                </li>          			
          		<% } %>
   				<%if(!restAddInfo.equals("")){%>
                <li>
                  <i class="bi bi-plus-square-fill"></i>
                  <strong>추가정보 </strong>
                  <span><%=restAddInfo %></span>
                </li>	
   				<%}%>
              </ul>
            </div>
          </div>
        </div>
        <% if(!menuName1.equals("")){ %>
        <div class="container">
          <div class="section-header">
            <p><span>네이버제공</span> 메뉴</p>
          </div>
          <div class="row gy-4 gx-lg-5">
            <div class="col-lg-6">
              <div class="pricing-item d-flex justify-content-between">
                <h3><%=menuName1 %></h3>
                <h4><%=menuPrice1 %></h4>
              </div>
            </div>
            <% if(!menuName2.equals("")){ %>
            <div class="col-lg-6">
              <div class="pricing-item d-flex justify-content-between">
                <h3><%=menuName2 %></h3>
                <h4><%=menuPrice2 %></h4>
              </div>
            </div>
            <% } %>
            <% if(!menuName3.equals("")){ %>
            <div class="col-lg-6">
              <div class="pricing-item d-flex justify-content-between">
                <h3><%=menuName3 %></h3>
                <h4><%=menuPrice3 %></h4>
              </div>
            </div>
            <% } %>
            <% if(!menuName4.equals("")){ %>            
            <div class="col-lg-6">
              <div class="pricing-item d-flex justify-content-between">
                <h3><%=menuName4 %></h3>
                <h4><%=menuPrice4 %></h4>
              </div>
            </div>
            <% } %>
          </div>
        </div>
        <% } %>
        
        <div class="container">
          <div class="section-header d-flex justify-content-between align-items-center">
            <p>
            	<span>슬대생리뷰</span> 
	            <b><%=restDetails.getTotalReview()%>건 평균 </b>
	            <span class="stars">
	              <i class="bi bi-star-fill"></i>
	              <b><%=score %></b>
	            </span>
            </p>
          </div>
          <hr class="my-0 pb-4">
          <% 
			int noReview = 0;
			for(int i = 0; i < restReviewList.size(); i++){
				RestaurantVO restVo = restReviewList.get(i);
				if( restVo.getUsersNo() == usersDetail.getUsersNo()){
					noReview = 1;
				}
			}
          if(id!=null && !id.isEmpty() && noReview==0){ 
          %>
          <div class="d-flex justify-content-between align-items-center py-3 mb-3 food-list new-review">
            <p class="mb-0 d-flex align-items-center gap-3">
              <!-- <img src="/img/testimonials/testimonials-2.jpg" class="testimonial-img" alt=""> -->
              <span> <b><%=memDetail.getMemNick() %></b>님 아직 리뷰를 작성하지 않으셨군요! 당신의 리뷰를 기다리고 있습니다. <br> ※홍보 및 비방 등 부적절한 평가는 평점 산정에서 제외 될 수 있습니다. </span>
            </p>
            <a href="<%=request.getContextPath() %>/restaurant/reviewWrite.do?no=<%=restDetails.getRestBizno() %>" class="btn btn-outline-warning">리뷰쓰기</a>
          </div>
          <% } %>
          <div class="row gy-4 gx-4">
          	<%	
          	if(restReviewList == null || restReviewList.size() == 0){ %>
				<div class="col-xl-12 col-lg-12 col-md-12 col-12">
					<p class="card-text ">리뷰가 존재하지 않습니다.</p>
				</div>
			<% } else {
				for (int i = 0; i < restReviewList.size(); i++) {
				  		RestaurantVO restVo = restReviewList.get(i);
		          	%>
		            <div class="col-xxl-4 col-lg-6">
		              <div class="food-list ">
		                <h6 class="mb-3 d-flex justify-content-between align-items-center">
		                  <span>
		                    <!-- <img src="/img/testimonials/testimonials-2.jpg" class="testimonial-img" alt=""> -->
		                    <div class="d-flex flex-column gap-2">
		                      <small><%=restVo.getNickName() %></small>
		                      <small class="stars mb-0 d-flex gap-2">
		                        <span class="gap-0">
		                        <%if(restVo.getReviewStar() == 10 ){%>
				                	<i class="bi bi-star-fill"></i> <i class="bi bi-star-fill"></i> <i class="bi bi-star-fill"></i> <i class="bi bi-star-fill"></i> <i class="bi bi-star-fill"></i>
				                <%}else if(restVo.getReviewStar() == 9){%> 
				                	<i class="bi bi-star-fill"></i> <i class="bi bi-star-fill"></i> <i class="bi bi-star-fill"></i> <i class="bi bi-star-fill"></i> <i class="bi bi-star-half"></i>
				                <%}else if(restVo.getReviewStar() == 8){%> 
				                	<i class="bi bi-star-fill"></i> <i class="bi bi-star-fill"></i> <i class="bi bi-star-fill"></i> <i class="bi bi-star-fill"></i> <i class="bi bi-star"></i>
				                <%}else if(restVo.getReviewStar() == 7){%> 
				                	<i class="bi bi-star-fill"></i> <i class="bi bi-star-fill"></i> <i class="bi bi-star-fill"></i> <i class="bi bi-star-half"></i> <i class="bi bi-star"></i>
				                <%}else if(restVo.getReviewStar() == 6){%> 
				                	<i class="bi bi-star-fill"></i> <i class="bi bi-star-fill"></i> <i class="bi bi-star-fill"></i> <i class="bi bi-star"></i> <i class="bi bi-star"></i>
				                <%}else if(restVo.getReviewStar() == 5){%> 
				                	<i class="bi bi-star-fill"></i> <i class="bi bi-star-fill"></i> <i class="bi bi-star-half"></i> <i class="bi bi-star"></i> <i class="bi bi-star"></i>
				                <%}else if(restVo.getReviewStar() == 4){%> 
				                	<i class="bi bi-star-fill"></i> <i class="bi bi-star-fill"></i> <i class="bi bi-star"></i> <i class="bi bi-star"></i> <i class="bi bi-star"></i>
				                <%}else if(restVo.getReviewStar() == 3){%> 
				                	<i class="bi bi-star-fill"></i> <i class="bi bi-star-half"></i> <i class="bi bi-star"></i> <i class="bi bi-star"></i> <i class="bi bi-star"></i>
				                <%}else if(restVo.getReviewStar() == 2){%> 
				                	<i class="bi bi-star-fill"></i> <i class="bi bi-star"></i> <i class="bi bi-star"></i> <i class="bi bi-star"></i> <i class="bi bi-star"></i>
				                <%}else if(restVo.getReviewStar() == 1){%> 
				                	<i class="bi bi-star-half"></i> <i class="bi bi-star"></i> <i class="bi bi-star"></i> <i class="bi bi-star"></i> <i class="bi bi-star"></i>
				                <%}else if(restVo.getReviewStar() == 0){%>
				                	<i class="bi bi-star"></i> <i class="bi bi-star"></i> <i class="bi bi-star"></i> <i class="bi bi-star"></i> <i class="bi bi-star"></i>
				                <%}else{%> <%} %>
		                        </span>
		                        <span class="font-200"><%=restVo.getReviewStar() / 2 %></span>
		                      </small>
		                    </div>
		                  </span>
		                  <small class="day"><%=restVo.getReviewDate() %></small>
		                </h6>
		                <p> <%=restVo.getReviewText() %> </p>
		                <%
		                if( restVo.getFileNo() > 0 ){
		                %>
		                <div class="d-flex justify-content-between overflow-x-scroll">
		                  <div class="col p-1">
		                    <img src="<%=restVo.getFileSavepath() %>" class="img-fluid" alt="<%=restVo.getNickName() %>의 <%=restDetails.getName()%> 대한 리뷰" onError="this.onerror=null; this.src='https://i.imgur.com/BFfnYMT.jpeg';" >
		                  </div>
		                </div>
		                <% } %>
		                <hr>
		                <div class="d-flex justify-content-between">
			                <a href="<%=request.getContextPath()%>/gourmet/view.do?usersNo=<%=restVo.getUsersNo() %>&memNick=<%=restVo.getNickName()%>" class="btn btn-secondary btn-sm">
			                  <span><%=restVo.getNickName() %>님 좋아요</span> 전체보기 
			                </a>
			                <% if(restVo.getUsersNo() == usersDetail.getUsersNo()){ %>
			                <a href="<%=request.getContextPath() %>/restaurant/reviewDelete.do?no=<%=restDetails.getRestBizno() %>&reviewNo=<%=restVo.getReviewNo() %>" class="btn btn-danger btn-sm">
			                  	삭제 
			                </a>
			                <% } %>
		                </div>
		              </div>
		            </div>
					<% } %>
		            <div class="col-xl-12 col-lg-12 col-md-12 text-center">
		              <a class="cta-btn text-center" href="<%=request.getContextPath() %>/restaurant/review.do?no=<%=restDetails.getRestBizno() %>&order=latest">더보기</a>
		            </div>
				<% } %>
          </div>
        </div>
		<%
       	NodeList itemList = blogRootElement.getElementsByTagName("item");
       	if(itemList.getLength()>0){ 
       	%>
		<div class="container gallery">
          <div class="section-header">
            <p><span>네이버제공</span> 블로그</p>
          </div>
          <ul class="row gy-4 justify-content-start ps-0">
         <% for (int i = 0; i < itemList.getLength(); i++) { %>
             <li class="card col-xl-3 col-lg-4 col-md-6">
                 <% 
                     Element itemElement = (Element) itemList.item(i);
                     String title = itemElement.getElementsByTagName("title").item(0).getTextContent();
                     String link = itemElement.getElementsByTagName("link").item(0).getTextContent();
                     String description = itemElement.getElementsByTagName("description").item(0).getTextContent();
                     String postdate = itemElement.getElementsByTagName("postdate").item(0).getTextContent();
                     String bloggername = itemElement.getElementsByTagName("bloggername").item(0).getTextContent();
                 %>
                 <a href="<%=link %>" target="_blank" >
                   <div class="card-body">
                     <small class="badge bg-body-secondary mb-1">작성일 :<%=postdate %></small>
                     <small class="badge bg-body-secondary mb-1">작성자 :<%=bloggername %></small>
                    <h5 class="card-title text-truncate pt-2"><%=title %></h5>
                     <p class="card-text "><%=description %></p>
                   </div>
                 </a>
             </li>
           <% } %>
         </ul>
        </div>
        <% } %>
        <% 
        NodeList itemNodeList = imageRootElement.getElementsByTagName("item");
        if(itemNodeList.getLength()>0){
        %>
        <div class="container gallery">
          <div class="section-header">
            <p><span>네이버제공</span> 이미지</p>
          </div>
          <div class="row gy-4 justify-content-start">
            <% for (int i = 0; i < itemNodeList.getLength(); i++) { %>
	             <div class="col-xl-3 col-lg-4 col-md-6">
	                <% 
	                   Element itemElement = (Element) itemNodeList.item(i);
	                   String title = itemElement.getElementsByTagName("title").item(0).getTextContent();
	                   String link = itemElement.getElementsByTagName("link").item(0).getTextContent();
	                   String thumbnail = itemElement.getElementsByTagName("thumbnail").item(0).getTextContent();
	               %>
	               <div class="h-m-180 overflow-hidden" title="<%=title%>">
	                  <a href="<%=link%>" target="_blank" class="w-100 h-100 d-flex" >
                  		<img src="<%=link%>" class="card-img" alt="<%=title%>" onError="this.onerror=null; this.src='<%=thumbnail%>';" />                  		
	                 </a>
	               </div>
	             </div>
            <% } %>
          </div>
        </div>
        <% } %>
      </section>
    </main>
<%@include file="/footer.jsp" %>