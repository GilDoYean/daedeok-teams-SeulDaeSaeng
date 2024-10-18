<%@page import="kr.or.ddit.member.vo.AddressVO"%>
<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@page import="kr.or.ddit.member.vo.UsersVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/header.jsp" %>

<%
	UsersVO usersVo = (UsersVO)request.getAttribute("usersVo");
	MemberVO memberVo = (MemberVO)request.getAttribute("memberVo");
	AddressVO addrVo = (AddressVO)request.getAttribute("addrVo");
%>

    <main class="pb-5 mb-5" data-aos="fade" data-aos-delay="700">
      <!-- ======= End Page Header ======= -->
      <div class="page-header d-flex align-items-center">
        <div class="container position-relative">
          <div class="row d-flex justify-content-center">
            <div class="col-lg-6 text-center">
              <h2>정보수정</h2>
            </div>
          </div>
        </div>
      </div>
      <!-- End Page Header -->
      <!-- ======= Contact Section ======= -->
      <section id="join" class="contact">
        <div class="container">
          <div class="row justify-content-center">
            <div class="col-lg-6">
              <form action="<%=request.getContextPath() %>/member/modify.do" method="post" role="form" class="php-email-form needs-validation" novalidate>
                 <input type="hidden" name="pw" id="pw" value="<%=usersVo.getUsersPass() %>" />
                 <div class="form-group d-flex align-items-center">
                  <span>저는 </span>
                  <%
                  	if(usersVo.getUsersRole() == 1){
                  %>
                  <input type="radio" class="btn-check" name="classify" id="teacher" value="1" autocomplete="off" checked>
                  <label class="btn btn-outline-warning" for="teacher">선생님</label>
					<%
					}else if(usersVo.getUsersRole() == 2){                  	
					%>
                  <input type="radio" class="btn-check" name="classify" id="student" value="2" autocomplete="off" checked>
                  <label class="btn btn-outline-warning" for="student">학생</label>
                  <%
					}
                  %>
                  <span>입니다.</span>
                </div>
                <div class="form-group mt-2">
                  <label class="form-label" for="id">아이디</label>
                  <input type="text" class="form-control" name="id" id="id" readonly="readonly" value="<%=usersVo.getUsersId() %>" />
                </div>
                <div class="form-group mt-2">
                	<label class="form-label" for="name">이름</label>
                  <input type="text" class="form-control" name="name" id="name" placeholder="이름" value="<%=memberVo.getMemName() %>" required />
                  <div class="invalid-feedback">올바른 이름을 입력해주세요.</div>
                </div>
                <div class="form-group mt-2">
               	  <label class="form-label" for="nickname">닉네임</label>
                  <input type="text" class="form-control" name="nickname" id="nickname" placeholder="닉네임" value="<%=memberVo.getMemNick() %>" required />
                  <div class="invalid-feedback">올바른 닉네임을 입력해주세요.</div>
                </div>
                <div class="row g-3 align-items-center">
				  <div class="col-auto">
				  	<label class="form-label" for="postAddr">주소</label>
				    <input type="text" class="form-control" id="postAddr" name="postAddr" placeholder="우편번호" required value="<%=addrVo.getAddrPost() %>">
			        <input type="button" id="addrBtn" name="addrBtn" value="주소검색">
				  </div>
				</div>
			    <div class="form-group">
			      <div class="col-sm-6">
			        <input type="text" class="form-control" id="addr1" name="addr1" placeholder="주소" value="<%=addrVo.getAddrBasic() %>" readonly required>
			      </div>
			    </div>
			    <div class="form-group mt-2">
			      <label class="form-label" for="addr1">상세주소</label>
			      <input type="text" class="form-control" id="addr2" name="addr2" placeholder="상세주소" value="<%=addrVo.getAddrDetail() %>" required>
			    </div>
                <div class="form-group mt-2">
                  <label class="form-label" for="tel">전화번호</label>
                  <input type="tel" class="form-control" name="tel" id="tel" placeholder="전화번호 (-없이 입력)" value="<%=memberVo.getMemTel() %>" required />
                  <div class="valid-feedback"></div>
                  <div class="invalid-feedback">전화번호를 올바르게 작성해주세요.</div>
                </div>
                <div class="form-group mt-2">
                  <label class="form-label" for="email">이메일</label>
                  <input type="email" class="form-control" name="email" id="email" placeholder="이메일" value="<%=memberVo.getMemEmail() %>" required pattern="[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}" />
                  <div class="valid-feedback"></div>
                  <div class="invalid-feedback">이메일 형식에 맞게 작성해주세요.</div>
                </div>
                <div class="form-group mt-2">
                  <label class="control-label col-sm-2" for="birthday">생년월일</label>
                  <input type="date" class="form-control" name="birthday" id="birthday" placeholder="생년월일" value="<%=memberVo.getMemBirth() %>" required />
                  <div class="valid-feedback"></div>
                  <div class="invalid-feedback"></div>
                </div>
                <div class="form-group mt-2">
                  <label class="control-label col-sm-2" for="birthday">프로필 사진</label>
                  <input class="form-control form-control-lg " type="file" name="profile" id="profile" />
                  <div class="valid-feedback"></div>
                  <div class="invalid-feedback">파일을 등록해주세요.</div>
                </div>
                <div class="text-center mt-5">
                  <button type="submit">수정하기</button>
                </div>
              </form>
              <hr class="mt-5">
              <div class="w-100 d-flex gap-5 text-center justify-content-center">
                <a href="<%=request.getContextPath() %>/member/pwModify.do">비밀번호 변경</a>
                <div class="vr"></div>
                <a href="<%=request.getContextPath() %>/member/memberUnregister.do">회원탈퇴하기</a>
              </div>
              
            </div>
            <!-- End Contact Form -->
          </div>
        </div>
      </section>
      <!-- End Contact Section -->
    </main>
    <!-- End #main -->
    
    
<%@include file="/footer.jsp" %>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>

$(function() {
	
	var id = $("#id");
	var name = $("#name");
	var nickname = $("#nickname");
	var postAddr = $("#postAddr");
	var addr1 = $("#addr1");
	var addr2 = $("#addr2");
	var tel = $("#tel");
	var email = $("#email");
	var birthday = $("#birthday");
	var profile = $("#profile");
	var classify = "<%= memberVo.getMemClass()%>";
	
	$('#addrBtn').on('click',function () {
        new daum.Postcode({
            oncomplete: function(data) {
                document.getElementById('postAddr').value = data.zonecode;
                document.querySelector("#addr1").value = data.roadAddress;
            }
        }).open();
    });
	
 	$('form button').click(function(){
   		const classify = $('input[name=classify]:checked').val();
		
		var emailreg = /^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}$/; 
		
		console.log("회원가입 버튼 누름");
		
		if(name.val() == "") return false;
		if(nickname.val() == "") return false;
		if(postAddr.val() == "") return false;
		if(addr2.val() == "") return false;
		if(!emailreg.test(email.val())) {
			alert("이메일을 올바르게 입력해주세요.");			
			return false;
		}
		if(birthday.val() == "") return false;
		else $('form').submit();   
		
	}); 

});

</script>

