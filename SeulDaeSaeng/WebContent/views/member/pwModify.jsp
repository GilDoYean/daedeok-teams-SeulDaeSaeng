
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
              <h2>비밀번호 변경</h2>
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
              <form action="<%=request.getContextPath() %>/member/pwModify.do" method="post" role="form" >
                <div class="form-group mt-3">
                  <input type="password" class="form-control" name="pwOri" id="pwOri" placeholder="기존 비밀번호" required>
                  <div class="invalid-feedback"></div>
                </div>
                <div class="form-group mt-3">
                  <input type="password" class="form-control" name="pwChn" id="pwChn" placeholder="새 비밀번호 (8~16자 영문 대소문자,숫자,특수문자조합)" required pattern="(?=.*?[a-zA-Z])(?=.*?[0-9])(?=.*?[~!@#$%^&*]).{8,16}">
                  <div class="invalid-feedback"></div>
                </div>
                <div class="form-group mt-3">
                  <input type="password" class="form-control" name="pwChnChk" id="pwChnChk" placeholder="새 비밀번호 확인" required>
                  <div class="invalid-feedback"></div>
                </div>
                <div class="text-center mt-5">
                  <button type="submit">입력</button>
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
	
	var pwOri = $("#pwOri");
	var pwChn = $("#pwChn");
	var pwChnChk = $("#pwChnChk");
	var usersPw = "<%=usersPass %>";
	
	var pwreg = /^(?=.*?[a-zA-Z])(?=.*?[0-9])(?=.*?[~!@#$%^&*]).{8,16}$/;
	
 	$('form button').click(function(){
 		
		if (pwOri.val() != usersPw){
			alert("비밀번호가 틀립니다.");
			return false;
		}
		if(pwChn.val() != pwChnChk.val()){
			alert("비밀번호 중복체크에 실패했습니다.");
			return false;
		}
		if(!pwreg.test(pwChn.val())) {
			alert("비밀번호 형식이 틀렸습니다.")
			return false;
		}
/* 		if (pwOri.val() == usersPw){
			document.location.href = "/member/pwModify.do";
			return true;
		} */

	}); 

});

</script>