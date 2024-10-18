<%@page import="java.util.List"%>
<%@page import="java.io.File" %>
<%@page import="kr.or.ddit.restaurant.vo.RestaurantVO"%>
<%@page import="kr.or.ddit.restaurant.vo.restLikeVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/header.jsp"%>
<%
	String url = request.getAttribute("javax.servlet.forward.request_uri") + "?" + request.getQueryString();
	String query = request.getQueryString();
	
	List<RestaurantVO> restList = (List<RestaurantVO>) request.getAttribute("restList");

		
	int totalpost = (int) request.getAttribute("totalpost");
	int totalpage = (int) request.getAttribute("totalpage");
	int totalblock = (int) request.getAttribute("totalblock");
	int block = (int) request.getAttribute("block");
	int firstpage = (int) request.getAttribute("firstpage");
	int pageperblock = (int) request.getAttribute("pageperblock");
	int nowPage = (int) request.getAttribute("nowPage");
	
	if (nowPage<=0 || nowPage>totalpage) {
%>
        <script type="text/javascript">
         
        	alert("<%=nowPage%> <%=totalpage%> 잘못된 접근입니다.")
            window.location.href = '<%=request.getContextPath() %>/restaurant/find.do?mcls=all';
        </script>
<%
        return; // 나머지 JSP 코드 실행 중단
    }
	
	String mclsName = (String) request.getAttribute("mclsName");
	String sclsName = (String) request.getAttribute("sclsName");
	String mclsId = (String) request.getAttribute("mcls");
	String sclsId = (String) request.getAttribute("scls");
	String order = (String) request.getAttribute("order");
	
	List<RestaurantVO> menuList = !mclsId.equals("") && !mclsId.equals("all") ? 
            (List<RestaurantVO>) request.getAttribute("sclsList") : 
            (List<RestaurantVO>) request.getAttribute("mclsList");


%>

<main data-aos="fade" data-aos-delay="700">
	<!-- ======= End Page Header ======= -->
	<div class="page-header d-flex align-items-center">
		<div class="container position-relative">
			<div class="row d-flex justify-content-center">
				<div>
					<nav style="--bs-breadcrumb-divider: '|';" aria-label="breadcrumb">
						<ol class="breadcrumb">
							<%if(sclsName.equals("") || sclsName == null){
								if(mclsName.equals("") || mclsName == null){%>
									<li class="breadcrumb-item active" aria-current="page">식당찾기</li>
								<%}else{%>
									<li class="breadcrumb-item">
										<a href="<%=request.getContextPath() %>/restaurant/find.do?mcls=all">식당찾기</a>
									</li>
									<li class="breadcrumb-item active" aria-current="page"><%=mclsName %></li>									
								<%}							
							}else{%>
								<li class="breadcrumb-item">
									<a href="<%=request.getContextPath() %>/restaurant/find.do?mcls=all">식당찾기</a>
								</li>
								<li class="breadcrumb-item">
									<a href="<%=request.getContextPath() %>/restaurant/find.do?mcls=<%=mclsId%>"><%=mclsName%></a>
								</li>
								<li class="breadcrumb-item active" aria-current="page"><%=sclsName %></li>									
							<%}%>							
						</ol>
					</nav>
					<h2>
						<%if(sclsName.equals("") || sclsName == null){
							if(mclsName.equals("") || mclsName == null){%>
								식당찾기
							<%}else{%>
								<%=mclsName %>
							<%}							
						}else{%>
							<%=sclsName %>
						<%}%>	
					</h2>
					<p class="d-flex gap-2 flex-wrap">
					<%if(!sclsId.equals("") && !sclsId.equals("all") ){%>
						<a class="cta-btn" href='<%=request.getContextPath() %>/restaurant/find.do?mcls=<%=mclsId%>&scls=<%=sclsId%>'><%=sclsName%></a>
					<%}else if(!mclsId.equals("") && !mclsId.equals("all") ){%>
						<a class="cta-btn" href='<%=request.getContextPath() %>/restaurant/find.do?mcls=<%=mclsId%>'><%=mclsName%></a>
					  	<% for (int i = 0; i < menuList.size(); i++) {
					  		RestaurantVO restVo = menuList.get(i);
					  	%>
						    <a class="cta-btn gray" href='<%=request.getContextPath() %>/restaurant/find.do?mcls=<%=restVo.getMcls() %>&scls=<%=restVo.getScls()%>'><%=restVo.getSclsName() %></a>
						<% }
					 }else{%>
					   <a class="cta-btn" href='<%=request.getContextPath() %>/restaurant/find.do?mcls=all'>ALL</a>
					  	<% for (int i = 0; i < menuList.size(); i++) {
					  		RestaurantVO restVo = menuList.get(i);
					  	%>
						    <a class="cta-btn gray" href='<%=request.getContextPath() %>/restaurant/find.do?mcls=<%=restVo.getMcls() %>'><%=restVo.getMclsName() %></a>
						<% } 
				  	}%>	
					</p>
				</div>
			</div>
		</div>
	</div>
	<!-- End Page Header -->
	<section id="restaurantFind" class="board mb-5">
		<div class="container" data-aos="fade-up">
			<div class="row mb-3 d-flex justify-content-between align-items-center">
				<p class="col-md-3 col-12 mb-0 pe-2">
					총 <b><%=totalpost%></b>개 <span class="px-2">|</span> <b><%=nowPage%></b> / <b><%=totalpage %></b> page
				</p>
				<div class=" col-md-4 col-12 text-end flex-wrap d-flex justify-content-end">
					<button class="btn filter" type="button" data-bs-toggle="dropdown"
						aria-expanded="false">
						<i class="bi bi-funnel-fill"></i> 
						<% if(order.equals("") || order == null){%>
							기본순
						<% } else if(order.equals("likeUp")){%>
							찜많은순
						<% } else if(order.equals("starUp")){%>
							별점높은순
						<% } else if(order.equals("starDonw")){%>
							별점낮은순
						<% } else if(order.equals("nameUp")){%>
							이름오름차순
						<% } else if(order.equals("nameDonw")){%>
							이름내림차순
						<% }%>
					</button>
					<ul class="dropdown-menu">
						<%
						String[] tempUrl = url.split("&order=");
	            	    tempUrl = tempUrl[0].split("&nowPage=");
	            		String filterUrl = tempUrl[0];
					%>
						<li><a class="dropdown-item" href='<%=filterUrl%>&order='>기본순</a></li>
						<li><a class="dropdown-item" href='<%=filterUrl%>&order=likeUp'>찜많은순</a></li>
						<li><a class="dropdown-item" href='<%=filterUrl%>&order=starUp'>별점높은순</a></li>
						<li><a class="dropdown-item" href='<%=filterUrl%>&order=starDonw'>별점낮은순</a></li>
						<li><a class="dropdown-item" href='<%=filterUrl%>&order=nameUp'>이름오름차순</a></li>
						<li><a class="dropdown-item" href='<%=filterUrl%>&order=nameDonw'>이름내림차순</a></li>					
					</ul>
				</div>
			</div>
			<ul class="row gy-4 justify-content-start ps-0">
			<% if(restList == null || restList.size() == 0){ %>
				<li class="col-xl-12 col-lg-12 col-md-12 col-12">
					<p class="card-text ">식당이 존재하지 않습니다.</p>
				</li>
			<% } else {
					for (int i=0; i<restList.size(); i++){	
						RestaurantVO restVo = restList.get(i);
			%>
					<li class="card col-xl-3 col-lg-4 col-md-6">
						<a href="<%=request.getContextPath() %>/restaurant/view.do?no=<%=restVo.getRestBizno() %>">
							<p class="card-like">
								<i class="bi bi-heart-fill"></i> 
								<span><%=restVo.getLikeCount() %></span>
							</p>
							<div class="img-fluid">
								<%
								String restBizno = restVo.getRestBizno();
								String filePath = getServletContext().getRealPath("/img/restaurantImage/" + restBizno);
								String fileUrl = "/img/restaurantImage/" + restBizno;
								
								// File 객체 생성
							  	File file = new File(filePath+".jpg");
							  	File file2 = new File(filePath+".png");

							  	// 파일 존재 여부 확인
							  	if (file.exists()) { %>
							  		<img src="<%=fileUrl%>.jpg" class="card-img-top" alt="<%=restVo.getName()%> 이미지" onError="this.onerror=null; this.src='https://i.imgur.com/BFfnYMT.jpeg';" >
							    <% } else if (file2.exists()) { %>
							  		<img src="<%=fileUrl%>.png" class="card-img-top" alt="<%=restVo.getName()%> 이미지" onError="this.onerror=null; this.src='https://i.imgur.com/BFfnYMT.jpeg';" >
							    <% }else { %>
							  		<img src="/img/no-image.jpg" class="card-img-top" alt="<%=restVo.getName()%> 이미지" >
							    <% } %>
							</div>
							<div class="card-body">
								<small class="badge bg-body-secondary mb-1">
									<%=restVo.getMclsName()%>
									&raquo;
									<%=restVo.getSclsName()%>
								</small>
								<div class="d-flex justify-content-between align-items-center">
									<h5 class="card-title text-truncate">
									<%=restVo.getName()%>
									</h5>
									<span> 
									<i class="bi bi-star-fill"></i> 
									<% if(restVo.getAvgReviewStar() == -1){%>
										없음
									<% } else{%>
										<%=restVo.getAvgReviewStar()/2 %>
									<% } %>
									</span>
								</div>
								<p class="card-text "><%=restVo.getAddrBasic()%></p>
							</div>
						</a>
					</li>
			<%
					}
				} 
			%>
			</ul>
			<nav
				class="d-flex justify-content-center align-items-center pt-5 pm-5"
				aria-label="Page navigation example">
				<ul class="pagination">
			<%
    	    tempUrl = tempUrl[0].split("&nowPage=");
    		filterUrl = tempUrl[0];
    		
       		if(block != 1){
       			String disabled ="";
       			if(block == 1){	disabled = "disabled"; }
         	%>
					<li class="page-item <%=disabled %>"><a class="page-link"
						href="<%=filterUrl %>&nowPage=1" aria-label="Previous"> <span
							aria-hidden="true">&laquo;&laquo;</span>
					</a></li>
			<% 
       		}
       		if(nowPage != 1){
       			String disabled ="";
       			if(nowPage == 1){ disabled = "disabled";}
            %>
            	<li class="page-item <%=disabled %>">
					<% int prevpage = nowPage-1; %> 
					<a class="page-link" href="<%=filterUrl %>&nowPage=<%=prevpage %>" aria-label="Previous"> 
						<span aria-hidden="true">&laquo;</span>
					</a>
				</li>
			<%
       		}
				String pactive = "";
				String plink = "";
       	
       		for(int i=0; i<pageperblock; i++){
      			int pnum = firstpage + i;
      			if(pnum == nowPage){
      				pactive = "active";
      				plink = "#";      				
      			}else{
      				pactive = "";
      				plink = filterUrl+"&nowPage="+pnum;
      			}
      			if(pnum <= totalpage){
         	%>
				<li class="page-item <%=pactive%>"><a class="page-link"
					href="<%=plink%>"> <%=pnum %>
				</a></li>
			<%
     			}
         	 }
       		if(nowPage != totalpage){

    			String disabled ="";
    			if(nowPage == totalpage){ disabled = "disabled";}
       		%>
				<li class="page-item <%=disabled%>">
					<% int nextpage = nowPage+1; %> 
					<a class="page-link" href="<%=filterUrl %>&nowPage=<%=nextpage %>" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
			<%
       		}
	   		if(block != totalblock && totalblock != 0){
	   			String disabled ="";
	   		   if(block == totalblock){ disabled = "disabled";}
	   		%>
				<li class="page-item <%=disabled%>"><a class="page-link"
					href="<%=filterUrl %>&nowPage=<%=totalpage %>" aria-label="Next">
						<span aria-hidden="true">&raquo;&raquo;</span>
					</a>
				</li>
			<% } %>

				</ul>
			</nav>
			<!-- <div class="container d-flex align-items-center justify-content-end pb-5 gap-2 p-0"><a href="write.html" type="button" class="btn btn-outline-warning">글쓰기</a></div> -->
		</div>
	</section>
</main>

<%@include file="/footer.jsp"%>
