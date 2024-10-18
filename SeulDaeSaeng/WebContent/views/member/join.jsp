<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/header.jsp" %>

    <main class="pb-5 mb-5" data-aos="fade" data-aos-delay="500">
      <!-- ======= End Page Header ======= -->
      <div class="page-header d-flex align-items-center">
        <div class="container position-relative">
          <div class="row d-flex justify-content-center">
            <div class="col-lg-6 text-center">
              <h2>회원가입</h2>
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
              <form action="<%=request.getContextPath() %>/member/join.do" method="post" role="form" class="php-email-form needs-validation" novalidate>
                 <div class="form-group d-flex align-items-center">
                  <span>저는 </span>
                  <input type="radio" class="btn-check" name="classify" id="teacher" value="1" autocomplete="off">
                  <label class="btn btn-outline-warning" for="teacher">선생님</label>
                  <input type="radio" class="btn-check" name="classify" id="student" value="2" autocomplete="off" checked>
                  <label class="btn btn-outline-warning" for="student">학생</label>
                  <span>입니다.</span>
                </div>
                <div class="form-group mt-2 d-flex gap-2 align-items-center">
				  <label for="id" class="form-label mb-0 pe-2">아이디 </label>
                  <input type="text" class="form-control" name="id" id="id" placeholder="4~12자의 숫자와 영문 대소문자" required pattern="[a-zA-Z0-9]{4,12}" />
                  <button type="button" id="idCheck" name="idCheck" class="btn btn-warning" > 중복확인</button>
                </div>
                 <div class="form-group mt-2 d-flex gap-2 align-items-center">
  				  <label for="pw" class="form-label mb-0 pe-2">비밀번호 </label>
                  <input type="password" class="form-control" name="pw" id="pw" placeholder="8~16자 영문 대소문자,숫자,특수문자조합" required pattern="(?=.*?[a-zA-Z])(?=.*?[0-9])(?=.*?[~!@#$%^&*]).{8,16}" />
                  <input type="password" class="form-control" name="pwChk" id="pwChk" placeholder="비밀번호 체크" required />
                </div>
                <div class="form-group mt-2 d-flex gap-2 align-items-center">
  				  <label for="name" class="form-label mb-0 pe-2">이름 </label>
                  <input type="text" class="form-control" name="name" id="name" placeholder="이름" required />
                </div>
                <div class="form-group mt-2 d-flex gap-2 align-items-center">
  				  <label for="nickname" class="form-label mb-0 pe-2">닉네임 </label>
                  <input type="text" class="form-control" name="nickname" id="nickname" placeholder="닉네임" required />
                </div>
			    <div class="form-group mt-2 d-flex gap-2">
  				  	  <label for="addrBtn" class="form-label mb-0 pe-2 pt-2">주소 </label>
  				  	  <div class="w-100">
		  				  <div class="w-100 d-flex gap-2 align-items-center">
					          <input type="text" class="form-control" id="postAddr" name="postAddr" placeholder="우편번호" readonly required>
					          <button type="button" id="addrBtn" name="addrBtn" class="btn btn-warning">주소검색</button>
		  				  </div>
					      <input type="text" class="form-control mt-2" id="addr1" name="addr1" placeholder="주소" readonly required>
					      <input type="text" class="form-control mt-2" id="addr2" name="addr2" placeholder="상세주소" required>
  				  	  </div>
			    </div>
                <div class="form-group mt-2 d-flex gap-2 align-items-center">
  				  <label for="tel" class="form-label mb-0 pe-2">전화번호 </label>
                  <input type="tel" class="form-control w-100" name="tel" id="tel" placeholder="전화번호 (-없이 입력)" required pattern="(01[016789]{1})[0-9]{3,4}[0-9]{4}" />
                  <div class="valid-feedback"></div>
                </div>
                <div class="form-group mt-2 d-flex gap-2 align-items-center">
  				  <label for="email" class="form-label mb-0 pe-2">이메일 </label>
                  <input type="email" class="form-control w-100" name="email" id="email" placeholder="이메일" required pattern="[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}" />
                  <div class="valid-feedback"></div>
                </div>
                <div class="form-group mt-2 d-flex gap-2 align-items-center">
  				  <label for="birthday" class="form-label mb-0 pe-2">생년월일 </label>
                  <input type="date" class="form-control w-100" name="birthday" id="birthday" placeholder="생년월일" required />
                </div>
                <div class="form-group mt-2 d-flex gap-2 align-items-center">
				    <label for="validationCustom04" class="form-label mb-0 pe-2">반호수 </label>
				    <select class="form-select w-100" name="validationCustom04" id="validationCustom04" required>
				        <option selected disabled value="">반 선택</option>
				        <option value="301">301호</option>
				        <option value="302">302호</option>
				        <option value="303">303호</option>
				        <option value="304">304호</option>
				        <option value="305">305호</option>
				        <option value="교무실">교무실</option>
				    </select>
				</div>
<!--                 <div class="form-group mt-2">
                  <input type="date" class="form-control" name="register" id="register" placeholder="등록일" required />
                  <div class="valid-feedback"></div>
                  <div class="invalid-feedback"></div>
                </div> -->
<!--                 <div class="form-group mt-2 d-flex gap-2 align-items-center">
                     <label for="checkImg" class="form-label mb-0 pe-2">HRD 캡쳐</label>
                     <input class="form-control form-control-lg " type="file" name="file" id="file" />
               </div> -->
                <div class="text-center mt-5">
                  <button class="submit" type="button">회원가입</button>
                </div>
              </form>
              
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
	var pw = $("#pw");
	var pwChk = $("#pwChk");
	var name = $("#name");
	var nickname = $("#nickname");
	var postAddr = $("#postAddr");
	var addr1 = $("#addr1");
	var addr2 = $("#addr2");
	var tel = $("#tel");
	var email = $("#email");
	var birthday = $("#birthday");
	var validationCustom04 = $("#validationCustom04");
	var checkImg = $("#checkImg");
	var postAddr = $("#postAddr");
	
	var idreg = /^[a-zA-Z0-9]{4,12}$/;
	
	var idOk = false;
	$('button#idCheck').click(function(){
		var usersId = $("#id").val();
		$.ajax({
			type: "POST",
			url: "/member/idCheck.do",
			data: { usersId : usersId },
 			contentType:"application/x-www-form-urlencoded;charset=UTF-8",
			dataType: "text", 
			success:function(cnt){
				if(!idreg.test(id.val())){
					alert("아이디 형식에 맞지 않습니다.");
					id.addClass("is-invalid");
					id.removeClass("is-valid");
		 			return false;
		 		} else if(cnt == 0){
					idOk = true;
					alert("사용가능한 아이디입니다.");
					id.addClass("is-valid");
					id.removeClass("is-invalid");
				} else{
					idOk = false;
					alert("중복된 아이디 입니다. 다시 입력해주세요.");
					id.addClass("is-invalid");
					id.removeClass("is-valid");
				}
			},
			error: function(){
				alert("에러입니다.");
				id.addClass("is-invalid");
				id.removeClass("is-valid");
			}
		})
	});
	
	function searchAddress() {
		new daum.Postcode({
            oncomplete: function(data) {
                document.getElementById('postAddr').value = data.zonecode;
                document.querySelector("#addr1").value = data.roadAddress;
            }
        }).open();
	}
    document.getElementById('postAddr').addEventListener('click', searchAddress);
    document.getElementById('addr1').addEventListener('click', searchAddress);
	document.getElementById('addrBtn').addEventListener('click', searchAddress);
	

	$('form button.submit').click(function(e) {
	    e.preventDefault();  // 기본 서브밋 동작 방지

	    const classify = $('input[name=classify]:checked').val();
	    var pwreg = /^(?=.*?[a-zA-Z])(?=.*?[0-9])(?=.*?[~!@#$%^&*]).{8,16}$/;
	    var telreg = /^(01[016789]{1})[0-9]{3,4}[0-9]{4}$/;
	    var emailreg = /^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}$/;
	    
	    var errors = false;

	    if (!idreg.test(id.val())) {
	        //alert("아이디 형식에 맞지 않습니다.");
	        id.addClass("is-invalid");
	        id.removeClass("is-valid");
	        errors = true;
	    } else if (idOk == false) {
	        alert("아이디 중복확인을 해주세요.");
	        id.addClass("is-invalid");
	        id.removeClass("is-valid");
	        errors = true;
	    } else {
	        id.addClass("is-valid");
	        id.removeClass("is-invalid");
	    }

	    if (!pwreg.test(pw.val())) {
	        //alert("비밀번호 형식에 맞지 않습니다.");
	        pw.addClass("is-invalid");
	        pw.removeClass("is-valid");
	        errors = true;
	    } else {
	        pw.addClass("is-valid");
	        pw.removeClass("is-invalid");
	    }

	    if (pw.val() != pwChk.val()) {
	        //alert("비밀번호 중복확인 실패");
	        pwChk.addClass("is-invalid");
	        pwChk.removeClass("is-valid");
	        errors = true;
	    } else {
	        pwChk.addClass("is-valid");
	        pwChk.removeClass("is-invalid");
	    }

	    if (name.val() == "") {
	        //alert("이름을 입력해주세요.");
	        name.addClass("is-invalid");
	        name.removeClass("is-valid");
	        errors = true;
	    } else {
	        name.addClass("is-valid");
	        name.removeClass("is-invalid");
	    }

	    if (nickname.val() == "") {
	        //alert("닉네임을 입력해주세요.");
	        nickname.addClass("is-invalid");
	        nickname.removeClass("is-valid");
	        errors = true;
	    } else {
	        nickname.addClass("is-valid");
	        nickname.removeClass("is-invalid");
	    }

	    if (postAddr.val() == "" || addr1.val() == "" || addr2.val() == "") {
	        //alert("주소를 입력해주세요.");
	        postAddr.addClass("is-invalid");
	        addr1.addClass("is-invalid");
	        addr2.addClass("is-invalid");
	        postAddr.removeClass("is-valid");
	        addr1.removeClass("is-valid");
	        addr2.removeClass("is-valid");
	        errors = true;
	    } else {
	        postAddr.addClass("is-valid");
	        addr1.addClass("is-valid");
	        addr2.addClass("is-valid");
	        postAddr.removeClass("is-invalid");
	        addr1.removeClass("is-invalid");
	        addr2.removeClass("is-invalid");
	    }

	    if (!telreg.test(tel.val())) {
	        //alert("형식에 맞지 않은 전화번호 입니다.");
	        tel.addClass("is-invalid");
	        tel.removeClass("is-valid");
	        errors = true;
	    } else {
	        tel.addClass("is-valid");
	        tel.removeClass("is-invalid");
	    }

	    if (!emailreg.test(email.val())) {
	        //alert("형식에 맞지 않은 이메일 입니다.");
	        email.addClass("is-invalid");
	        email.removeClass("is-valid");
	        errors = true;
	    } else {
	        email.addClass("is-valid");
	        email.removeClass("is-invalid");
	    }

	    if (birthday.val() == "") {
	        //alert("생일을 입력해주세요.");
	        birthday.addClass("is-invalid");
	        birthday.removeClass("is-valid");
	        errors = true;
	    } else {
	        birthday.addClass("is-valid");
	        birthday.removeClass("is-invalid");
	    }

	    var selectedVal = $('#validationCustom04').val();
	    if (selectedVal == null || selectedVal == "") {
	        //alert("값을 선택해주세요.");
	        $('#validationCustom04').addClass("is-invalid");
	        $('#validationCustom04').removeClass("is-valid");
	        errors = true;
	    } else {
	        $('#validationCustom04').addClass("is-valid");
	        $('#validationCustom04').removeClass("is-invalid");
	    }

	    if (!errors) {
	        console.log("서브밋");
	        $('form').submit();
	    } else {
	        console.log("errors 존재");
	    }
	});

});

</script>

