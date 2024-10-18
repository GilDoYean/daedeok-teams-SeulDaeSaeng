
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="/header.jsp" %>

<%
	String usersPass = (String)request.getAttribute("usersPass");
%>

<main class="pb-5 mb-5" data-aos="fade" data-aos-delay="700">
      <!-- ======= End Page Header ======= -->
      <div class="page-header d-flex align-items-center">
        <div class="container position-relative">
          <div class="row d-flex justify-content-center">
            <div class="col-lg-6 text-center">
              <h2>비밀번호 확인</h2>
            </div>
          </div>
        </div>
      </div>
      <!-- End Page Header -->
      <!-- ======= Contact Section ======= -->
      <section id="login" class="contact">
        <div class="container">
          <div class="row justify-content-center">
            <div class="col-lg-6">
              <form action="<%=request.getContextPath() %>/member/modify.do" method="post" role="form" >
                <div class="form-group">
                  <input type="password" class="form-control" name="pw" id="pw" placeholder="비밀번호" required>
                  <div class="invalid-feedback"></div>
                </div>
                <div class="form-group mt-3">
                  <input type="password" class="form-control" name="pwChk" id="pwChk" placeholder="비밀번호 확인" required>
                  <div class="invalid-feedback"></div>
                </div>
                <div class="text-center mt-5">
                  <button type="button">입력</button>
                </div>
              </form>
            </div>
            <!-- End Contact Form -->
          </div>
        </div>
      </section>
      <!-- End Contact Section -->
    </main>

<%@include file="/footer.jsp" %>

<script>

$(function() {
	
	var pw = $("#pw");
	var pwChk = $("#pwChk");
	var usersPw = "<%=usersPass %>";
	
 	$('form button').click(function(){
 		
		if(pw.val() != pwChk.val()){
			alert("비밀번호 중복체크에 실패했습니다.");
			return false;
		}
		if (pw.val() != usersPw){
			alert("비밀번호가 틀립니다.");
			return false;
		}
		if (pw.val() == usersPw){
			document.location.href = "/member/modify.do";
			/* $('form').submit(); */
		}

	}); 

});

</script>