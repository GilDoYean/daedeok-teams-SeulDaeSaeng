<%@page import="kr.or.ddit.restaurant.service.IRestaurantService"%>
<%@page import="kr.or.ddit.restaurant.service.RestaurantServiceImpl"%>
<%@page import="java.util.List"%>
<%@page import="java.io.File"%>
<%@page import="kr.or.ddit.restaurant.vo.RestaurantVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>
<%
    IRestaurantService service = RestaurantServiceImpl.getInstance();
    int usersNo = (int)request.getAttribute("usersNo");
    String memNick = (String)request.getAttribute("memNick");

%>

<main data-aos="fade" data-aos-delay="700">
    <div class="page-header sub d-flex align-items-center">
        <div class="container position-relative">
            <div class="row d-flex justify-content-center">
                <p class="mb-5 text-start">
                	<a href="<%=request.getContextPath() %>/gourmet/list.do">
                        <i class="bi bi-chevron-left"></i> 뒤로가기
                    </a>
                </p>
                <div>
           <h2><%=memNick %>의 추천맛집</h2>

             <li class="food-list mb-4">
              <ul class="d-flex justify-content-start flex-wrap">
              <%
              List<RestaurantVO> myLikeList = service.getMyLikeList(usersNo);
              for(int k=0 ; k<myLikeList.size() ; k++){
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
                      <div class="d-flex justify-content-between align-items-center">
                        <h5 class="card-title text-truncate"><%=myLike.getName() %></h5>
                        <span>
                          <i class="bi bi-star-fill"></i> <%=myLike.getAvgReviewStar()/2 %> </span>
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
                </div>
            </div>
        </div>
    </div>
</main>

<%@include file="/footer.jsp" %>
