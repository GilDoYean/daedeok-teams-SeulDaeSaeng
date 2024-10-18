<%@page import="kr.or.ddit.contact.vo.ContactVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="/header.jsp" %>

<%
	ContactVO contactVO =(ContactVO) request.getAttribute("contactVO");
%>
<main>
    <div class="page-header sub d-flex align-items-center">
        <div class="container position-relative">
            <div class="row d-flex justify-content-center">
             
                <div>
                    <form action="<%= request.getContextPath() %>/views/contactList.do" method="post" id="insertForm" class="php-email-form needs-validation" >
                        <div class="contact">
	                        <div class="container pb-3">
		                        <div class="form-group d-flex align-items-center">
		                            <input type="radio" class="btn-check" name="level" id="freeBoard" value="1" autocomplete="off" checked>	
		                            <label class="btn btn-outline-warning" for="freeBoard">답변하기</label>
		                        </div>
		                        <div class="form-group">
		                            <input type="text" class="form-control" name="memEmail" id="memEmail" placeholder="이메일"  value="<%=contactVO.getqEmail() %>" readonly="readonly">
		                            <input type="text" class="form-control" name="title" id="title" placeholder="제목" >
		                             <input type="hidden" name="qNO" value="<%= contactVO.getqNO() %>">
		                        </div>
		                        <textarea class="form-control" rows="5" id="body" name="body"></textarea>
		                        <div class="text-center mt-5 mb-5">
		                       		<button type="submit" id="submitBtn" name="submitBtn" style="background-color:orange;">보내기</button>
		                        </div>
	                        </div>
                        </div>
                    </form>
                    <script type="text/javascript">
                    
/*                     	var title = $("#title");
                    	var memEmail = $("#memEmail");
                    	$("#submitBtn").on("click",function(e){
                    		e.preventDefault();
                        	console.log(title.val());
                        	console.log(memEmail.val());                    		
                    	}); */
                    	
                    	
                    </script>
                </div>
            </div>
        </div>
    </div>
</main>
<%@include file="/footer.jsp" %>