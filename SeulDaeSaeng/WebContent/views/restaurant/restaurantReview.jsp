<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.restaurant.vo.RestaurantVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/header.jsp" %>
<%	
	String url = request.getAttribute("javax.servlet.forward.request_uri") + "?" + request.getQueryString();
	RestaurantVO restDetails = (RestaurantVO) request.getAttribute("restDetails");
	String order = (String) request.getAttribute("order");

	List<RestaurantVO> restReviewList = (List<RestaurantVO>) request.getAttribute("restReviewList");
	MemberVO memDetail = (MemberVO) session.getAttribute("memDetail") != null ? (MemberVO) session.getAttribute("memDetail") : new MemberVO();
	UsersVO usersDetail = (UsersVO) session.getAttribute("usersDetail") != null ? (UsersVO) session.getAttribute("usersDetail") : new UsersVO();
%>

    <main data-aos="fade" data-aos-delay="700">
      <!-- ======= End Page Header ======= -->
      <div class="page-header restaurant d-flex align-items-center">
        <div class="container position-relative">
          <div class="row d-flex justify-content-center">
            <div>
              <nav style="--bs-breadcrumb-divider: '|';" aria-label="breadcrumb">
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
                  <li class="breadcrumb-item" aria-current="page">
                    <a href="<%=request.getContextPath() %>/restaurant/view.do?no=<%=restDetails.getRestBizno()%>"><%=restDetails.getName()%></a>
                  </li>
                  <li class="breadcrumb-item active" aria-current="page">모든리뷰</li>
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
                  <% if(id!=null && !id.isEmpty()){ %>                	  
                  <input type="checkbox" class="btn-check" id="btn-check" autocomplete="off">
                  <label class="btn btn-danger d-flex align-items-center gap-2" for="btn-check">
                    <span>
                      <i class="bi bi-heart-fill"></i> 좋아요 </span>
                    <span>
                      <i class="bi bi-heart"></i> 취소 </span>
                  </label>
                  <% } %>
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- End Page Header -->
      <section id="restaurantView" class="pricing about d-flex flex-column gap-4">
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
	            <button class="btn filter" type="button" data-bs-toggle="dropdown" aria-expanded="false">
					<i class="bi bi-funnel-fill"></i> 
					<% if(order.equals("latest") || order.equals("") || order == null ){%>
						최신순
					<% } else if(order.equals("late")){%>
						오래된순
					<% } else if(order.equals("starUp")){%>
						별점높은순
					<% } else if(order.equals("starDonw")){%>
						별점낮은순
					<% } %>
				</button>
				<ul class="dropdown-menu">
				<%
					String[] tempUrl = url.split("&order=");
            	    tempUrl = tempUrl[0].split("&nowPage=");
            		String filterUrl = tempUrl[0];
				%>
					<li><a class="dropdown-item" href='<%=filterUrl%>&order=latest'>최신순</a></li>
					<li><a class="dropdown-item" href='<%=filterUrl%>&order=late'>오래된순</a></li>
					<li><a class="dropdown-item" href='<%=filterUrl%>&order=starUp'>별점높은순</a></li>
					<li><a class="dropdown-item" href='<%=filterUrl%>&order=starDonw'>별점낮은순</a></li>			
				</ul>
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
		              <img src="/img/testimonials/testimonials-2.jpg" class="testimonial-img" alt="" onError="this.onerror=null; this.src='https://i.imgur.com/BFfnYMT.jpeg';" >
		              <span> <%=memDetail.getMemNick() %>님 아직 리뷰를 작성하지 않으셨군요! 당신의 리뷰를 기다리고 있습니다. <br> ※홍보 및 비방 등 부적절한 평가는 평점 산정에서 제외 될 수 있습니다. </span>
		            </p>
		            <a href="<%=request.getContextPath() %>/restaurant/reviewWrite.do?no=<%=restDetails.getRestBizno() %>" class="btn btn-outline-warning">리뷰쓰기</a>
		          </div>
		          <% } %>
	          <div class="row gy-4 gx-4">
	          
	            <%if(restReviewList == null || restReviewList.size() == 0){ %>
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
			                    <div class="d-flex flex-column ps-2 gap-2">
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
			                <!-- 
			                <button type="button" class="btn btn-secondary btn-sm">
			                  <span>00님 맛집 </span>리스트 더보기 
			                </button>
			                 -->
			              </div>
			            </div>
						<% } %>
					<% } %>
	          </div>
	        </div>
    	</section>
    </main>
<%@include file="/footer.jsp" %>