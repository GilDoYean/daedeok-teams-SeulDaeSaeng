<%@page import="kr.or.ddit.restaurant.service.IRestaurantService"%>
<%@page import="kr.or.ddit.restaurant.service.RestaurantServiceImpl"%>
<%@page import="java.util.List"%>
<%@page import="java.io.File"%>
<%@page import="kr.or.ddit.restaurant.vo.RestaurantVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>
<%
    List<MemberVO> likeMemList = (List<MemberVO>)request.getAttribute("likeMemList");
	IRestaurantService service = RestaurantServiceImpl.getInstance();
%>

<main data-aos="fade" data-aos-delay="700">
    <div class="page-header sub d-flex align-items-center">
        <div class="container position-relative">
            <div class="row d-flex justify-content-center">
                <p class="mb-5 text-start">
                	<a href="<%=request.getContextPath() %>/main.do">
                        <i class="bi bi-chevron-left"></i> 뒤로가기
                    </a>
                </p>
                <div>
           <h2>슬대생의 추천맛집</h2>
           <%
           for(int i=0;i<likeMemList.size();i++){
        	   MemberVO mem = likeMemList.get(i);
           %>
             <li class="food-list mb-4">
              <h6 class="mb-3 d-flex justify-content-between align-items-center">
                <span>
                   <i class="bi bi-person-circle text-warning me-3"></i> 
                  <!-- <img src="img/testimonials/testimonials-2.jpg" class="testimonial-img" alt=""> --> 
                  <%=likeMemList.get(i).getMemNick() %> 
                </span>
                <button type="button" class="btn btn-secondary btn-sm" onclick="location.href='<%=request.getContextPath()%>/gourmet/view.do?usersNo=<%=mem.getUsersNo() %>&memNick=<%=mem.getMemNick()%>'">
                  <span><%=mem.getMemNick() %>님 맛집 </span>리스트 더보기 
                </button>
              </h6>
              <ul class="d-flex justify-content-between flex-wrap">
              <%
              List<RestaurantVO> myLikeList = service.getMyLikeList(mem.getUsersNo());
              int size = myLikeList.size();
              if(size>4){size=4;}
              for(int k=0 ; k<size ; k++){
            	  RestaurantVO myLike = myLikeList.get(k);
              %>
                <li class="card col-lg-3 col-md-12">
                  <a href="<%=request.getContextPath() %>/restaurant/view.do?no=<%=myLike.getRestBizno() %>">
                    <p class="card-like text-light">
                      <i class="bi bi-heart-fill"></i>
                      <span><%=myLike.getLikeCount() %></span>
                    </p>
                    <div class="img-fluid">
						<%
						String restBizno = myLike.getRestBizno();
						String filePath = getServletContext().getRealPath("/img/restaurantImage/" + restBizno);
						String fileUrl = "/img/restaurantImage/" + restBizno;
						
						// File 객체 생성
					  	File file = new File(filePath+".jpg");
					  	File file2 = new File(filePath+".png");

					  	// 파일 존재 여부 확인
					  	if (file.exists()) { %>
					  		<img src="<%=fileUrl%>.jpg" class="card-img-top" alt="<%=myLike.getName()%> 이미지" onError="this.onerror=null; this.src='https://i.imgur.com/BFfnYMT.jpeg';" >
					    <% } else if (file2.exists()) { %>
					  		<img src="<%=fileUrl%>.png" class="card-img-top" alt="<%=myLike.getName()%> 이미지" onError="this.onerror=null; this.src='https://i.imgur.com/BFfnYMT.jpeg';" >
					    <% }else { %>
					  		<img src="/img/no-image.jpg" class="card-img-top" alt="<%=myLike.getName()%> 이미지" >
					    <% } %>
					</div>
                    <div class="card-body">
                      <small class="badge bg-body-secondary mb-1"><%=myLike.getMclsName() %></small>
                      <div class="d-flex justify-content-start align-items-center">
                        <h5 class="card-title text-truncate"><%=myLike.getName() %></h5>
                        <span> 
						<i class="bi bi-star-fill"></i> 
						<% if(myLike.getAvgReviewStar() == -1){%>
							없음
						<% } else{%>
							<%=myLike.getAvgReviewStar()/2 %>
						<% } %>
						</span>
                      </div>
                      <p class="card-text "><%=myLike.getAddrBasic() %></p>
                    </div>
                  </a>
                </li>
                <%
                }
                %>
              </ul>
            </li>
              <%
              }
              %>
                </div>
            </div>
        </div>
    </div>
</main>

<%@include file="/footer.jsp" %>
