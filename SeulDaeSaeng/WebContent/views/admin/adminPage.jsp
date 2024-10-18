<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@page import="kr.or.ddit.contact.vo.ContactVO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.io.Console"%>
<%@page import="kr.or.ddit.admin.vo.UserReqVO"%>
<%@page import="kr.or.ddit.admin.vo.MemberReqVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/header.jsp" %>

     <%
     	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
          		String formatDate = sdf.format(new Date());
     %>
<%
	List<MemberReqVO> memList = (List<MemberReqVO>)request.getAttribute("memberList");

	List<ContactVO> conList = (List<ContactVO>)request.getAttribute("contactList");
	
	List<BoardVO> repList = (List<BoardVO>)request.getAttribute("reportList");
	
	List<MemberReqVO> reportmem = (List<MemberReqVO>)request.getAttribute("reportmember");
	
	 MemberReqVO memreqVO = (MemberReqVO) request.getAttribute("memreqVO"); 

  	
%>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">

      <div class="modal-header" id="list">
        <h1 class="modal-title fs-5" id="exampleModalLabel"><span id="memName1"></span>님의 요청내역</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
 		<form action="
 		/views/adminaccept.do" method="post" id="userForm">
<%-- 	        <input type ="hidden" name = "usersNo" value = "<%=memreqVO.getUsersNo() %>" /> --%>
	        <p>이름: <span id="memName"></span></p>
	        <p>닉네임: <span id="memNick"></span></p>
	        <p>생년월일: <span id="memBirth"></span></p>
	        <p>이메일: <span id="memEmail"></span></p>
	        <p>전화번호: <span id="memTel"></span></p>
	        <p>분반: <span id="memClass"></span></p>
	        <p>등록일시: <span id="memRegdt"></span></p>
	        <p>주소 : <span id="addrNo"></span></p>
		    <div class="modal-footer">
		    	<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>	       
		    	<button type="button" class="btn btn-primary" id="accept" name="accept" >수락</button>
		    	<!-- <a href="/admin/memberYN.do?usersNo=" class="btn btn-primary" id="accept">수락</a> -->
		    </div>
 		</form>   
        </div>
    </div>
  </div>
</div>




<main data-aos="fade" data-aos-delay="700">

  <section id="calssBoard" class="gallery-single ">
    <div class="container-xl">
      <div class="col-lg-12">
      
        <div class="portfolio-description homework">
          <div class="section-header">
            <h2>Request</h2>
            <p class="d-flex justify-content-between align-items-center"> 
              	회원가입 요청내용
              <button type="button" class="btn btn-outline-warning btn-sm"><a href="/views/allList.do">완료된 요청보기</a></button>
            </p>
          </div>
          <div class="list-group d-flex justify-content-start align-items-center flex-row p-3 gap-3" id="listG">
                
               
     <%
     	 if(memList != null && !memList.isEmpty()) {	
                   for(MemberReqVO member : memList) {
    %>
     
            <a href="#" class="card" data-selectno="<%=member.getUsersNo()%>" >
              <div class="card-body" data-bs-toggle="modal" data-bs-target="#exampleModal"  >
                <div class="d-flex justify-content-between align-items-center">
                  <h5 class="card-title text-truncate"><%=member.getMemName()%>(<%=member.getMemNick() %>)</h5>
                  <small><%=member.getMemClass()%>호</small>
                </div>
                <p class="card-text "><%=sdf.format(member.getMemRegdt()) %></p>
              </div>
            </a>
   	<%
     		}
      	}
     		else{
  	%> 
     	<div>회원가입 요청이 없습니다.</div>
    	<%  
	    	}
   	%>

          </div>
        </div>
        
        
        
          <div class="portfolio-description d-flex justify-content-between gap-5">
            <div class="col">
              <div class="section-header">
                <h2>Request</h2>
                <p class="d-flex justify-content-between align-items-center"> 신고된 게시글 
                  <button type="button" class="btn btn-outline-warning btn-sm"><a href ="/views/reportAllList.do">더보기</a></button>
                </p>
              </div>              
              <div class="list-group">
       
  <%
      if(repList != null && !repList.isEmpty()) {	
    	  for(int i = 0; i<4 ; i++) {
         	 BoardVO board = repList.get(i);	
  %>              
                <a href="<%=request.getContextPath() %>/views/reportDetail.do?boardNo=<%=board.getBoardNo()%>" class="list-group-item d-flex w-100 justify-content-between align-items-center py-3">
                  <h6 class="mb-2 text-truncate">
                  	  <small class="badge bg-light">
                    	<%= "Y".equals(board.getBoardYn().trim()) ? "미확인" : "확인" %>
                    </small>
                    <small class="badge bg-light"><%=board.getBoardNo() %></small>
                    <small class="attach">
                      <i class="bi bi-paperclip"></i>
                    </small> <%=board.getBoardTitle() %>
                  </h6>
                  <small class="days"><%=board.getBoardAt() %></small>
                </a>    
 	<%
    			 if(repList.size()-1 == i) break;
     		}
      	}
     		else{
  	%>    
  	 	<div>신고 내역이 없습니다.</div>
   <%  
	    	}
  	%>            
             
             </div>
            </div>
          
          
  
            
            <div class="col">
              <div class="section-header">
                <h2>board</h2>
                <p class="d-flex justify-content-between align-items-center"> 오류제보 <button type="button" class="btn btn-outline-warning btn-sm">
                	<a href="/views/contactList.do">더보기</a></button>
                </p>
              </div>
              <div class="list-group">
    <% 
    
         	if(conList != null && !conList.isEmpty()) {	
//                 for(ContactVO contact : conList) {
                for(int i = 0; i<4 ; i++) {
                	 ContactVO contact = conList.get(i);	              
	%>
			
	   <a href="<%=request.getContextPath() %>/views/contactDetail.do?qNO=<%=contact.getqNO()%>" class="list-group-item d-flex w-100 justify-content-between align-items-center py-3">
                  <h6 class="mb-2 text-truncate">                  
                    <small class="badge bg-light">
                    	<%= "Y".equals(contact.getqYn().trim()) ? "완료" : "미완료" %>
                    </small>
                    <%=contact.getqTitle()%>
                 </h6>
                  <small class="days"><%=sdf.format(contact.getqAt()) %></small>
                </a>
  <%
                if(conList.size()-1 == i) break;
      		}
      	}
     		else{
 	%> 
     	<div>오류제보가  없습니다.</div>
                
   	<%   
	    	}
   	%> 
              </div>
            </div>
          </div>
          
          
          <div class="portfolio-description">
            <div class="section-header">
              <h2>Request</h2>
              <p class="d-flex justify-content-between align-items-center"> 신고 많은 회원 
                <button type="button" class="btn btn-outline-warning btn-sm">전체보기</button>
              </p>
            </div>
            <div class="portfolio-description d-flex gap-5 ">
              <div class="list-group col">
                          
    <%
     	if(reportmem != null && !reportmem.isEmpty()) {	
               	 for(MemberReqVO member : reportmem) {
// 				  for(int i = 0; i<5 ; i++) {
// 					  MemberReqVO member = reportmem.get(i);
				  
    %>   		
                <a href="#" class="list-group-item py-2 d-flex justify-content-between align-items-center">
                  <span>
                    <i class="bi bi-person-fill px-2"></i> 
                    <b><%=member.getMemName() %>(<%=member.getMemNick()%>)</b>
                    <small>(<%=member.getMemClass() %>)</small>
                  </span>             
                  <span class="text-danger"><small>신고횟수</small> <b><%=member.getReportNo() %></b></span>
                </a> 
  		
  	<%		
//   		  if(reportmem.size()-1 == i) break;
  	
     		}
      	} 

     	else{
  	%>       
  		<div>신고내역이 없습니다.</div>   
  <%
     	}
  %>     
              </div>
            </div>
          </div>
          
          
      </div>
    </div>
  </section>
</main>
<!-- End #main -->

<script type="text/javascript">


</script>

<script type="text/javascript">

	
 document.addEventListener('DOMContentLoaded', function() {
// 	 $(".modal-footer #accept").click(function(){
//         $.ajax({
//             type: 'POST',
//             url: '/views/adminaccept.do',
//             data: { usersNo: {usersNo},
//             success: function (response) {
//                 console.log(response);
//                 $('.modal').modal('hide');
//                 location.reload(true);
//             },
//             error: function () {
//                 console.error('Error');
//             }
//         });
//     });

    
    	/* var userNo = $(this).data("userNo");   */



    	
    	$("#listG").on("click", "a", function(event){
    		event.preventDefault();
    		
    		var usersNo = $(this).data("selectno");
    		var selectUsersNo;
//    		var targetHref = $(accept).attr('href');
    		 $.ajax({
 	            type: 'post',
 	            url: '/views/adminPage.do',
  	            data: { usersNo: usersNo}, 
 	            success: function (data) {
 	            	
//   	                console.log(JSON.parse(data)); // list 정보 들어옴
// 	                let rst = JSON.parse(data)[0]; //index로 접근 
 	                
//   	            	var memreqVO = data;
  	                $('#usersNo').text(data.usersNo);
  	                $('#memName1').text(data.memName);
  	                $('#memName').text(data.memName);
 	                $('#memNick').text(data.memNick);
  	                $('#memBirth').text(data.memBirth);
  	                $('#memEmail').text(data.memEmail);
  	                $('#memTel').text(data.memTel);
  	                $('#memClass').text(data.memClass);
  	                $('#memRegdt').text(data.memRegdt);
  	                $('#addrNo').text(data.addrNo);
  	             	selectUsersNo = data.usersNo;
  	                
//    		 		targetHref.after(usersNo);
 	            },
 	            error: function () {
 	                console.error('Error');
 	            }
 	        });
    		 
    		 document.getElementById("accept").onclick = function () {
	    		 document.location.href = "/admin/memberYN.do?usersNo=" + selectUsersNo;
    		    };
    		 
    	});
    
});	

</script>
<%@include file="/footer.jsp" %>