<%@page import="kr.or.ddit.contact.vo.ContactVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="/header.jsp" %>
 <%
 	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
      		String formatDate = sdf.format(new Date());
 %>
 
 
<%
  	List<ContactVO> contactList = (List<ContactVO>)request.getAttribute("contactList");
  %>
  
<main data-aos="fade" data-aos-delay="700">
	<div class="portfolio-description">
		<section id="calssBoard" class="gallery-single ">
			<div class="container-xl">
				<div class="col-lg-12">
					<div class="section-header">
						<h2>Request</h2>
						<p class="d-flex justify-content-between align-items-center">
							오류 제보</p>
					</div>
					<div class="portfolio-description d-flex gap-5 ">
						<div class="list-group col">
 <%
 	if(contactList != null && !contactList.isEmpty()) {	
 		  for(int i= 0; i<contactList.size(); i++) {
 			 ContactVO contact = contactList.get(i);
 %>						
<!-- 							<a href="#" -->
<!-- 								class="list-group-item py-2 d-flex justify-content-between align-items-center"> -->
<%-- 								<span> <i class="bi bi-person-fill px-2"></i> <b><%=con.ge%></b> --%>
<%-- 									<small><%=sdf.format(member.getMemRegdt()) %>(<%=member.getMemClass()%>호)</small> --%>
<!-- 								</span> -->
<!-- 							</a>  -->
				<a href="<%=request.getContextPath() %>/views/contactDetail.do?qNO=<%=contact.getqNO()%>" class="list-group-item d-flex w-100 justify-content-between align-items-center py-3">
                  <h6 class="mb-2 text-truncate">
                  	<small class="badge bg-light">
                  			<%= "Y".equals(contact.getqYn().trim()) ? "완료" : "미완료" %>
                  	</small>
                  	 <%= contact.getqTitle()%>
                  </h6>
                  <small class="days"><%=sdf.format(contact.getqAt()) %></small>
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

<%@include file="/footer.jsp" %>