<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="kr.or.ddit.admin.vo.MemberReqVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/header.jsp"%>
 <%
 	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
      		String formatDate = sdf.format(new Date());
 %>
 
 
<%
  	List<MemberReqVO> memallList = (List<MemberReqVO>)request.getAttribute("memberallList");
  %>
<main data-aos="fade" data-aos-delay="700">
	<div class="portfolio-description">
		<section id="calssBoard" class="gallery-single ">
			<div class="container-xl">
				<div class="col-lg-12">
					<div class="section-header">
						<h2>Request</h2>
						<p class="d-flex justify-content-between align-items-center">
							완료된 요청</p>
					</div>
					<div class="portfolio-description d-flex gap-5 ">
						<div class="list-group col">
 <%
 	if(memallList != null && !memallList.isEmpty()) {	
      	for(MemberReqVO member : memallList) {
 %>						
							<a href="#"
								class="list-group-item py-2 d-flex justify-content-between align-items-center">
								<span> <i class="bi bi-person-fill px-2"></i> <b><%=member.getMemName()%>(<%=member.getMemNick() %>)</b>
									<small><%=sdf.format(member.getMemRegdt()) %>(<%=member.getMemClass()%>호)</small>
								</span>
							</a> 
 <%
     	}
  	}else{
  	%> 
     	<div>완료된 내역이 없습니다.</div>
<%  
	    }
%>					
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
</main>
<%@include file="/footer.jsp"%>