<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../header.jsp" %>

<% 
	int UsersNo =  (Integer)request.getAttribute("UsersNo");
%>
    <main class="pb-5" data-aos="fade" data-aos-delay="700">
      <!-- ======= End Page Header ======= -->
      <div class="page-header d-flex align-items-center">
        <div class="container position-relative">
          <div class="row d-flex justify-content-center">
            <div class="col-lg-6 text-center">
              <h2>오류제보</h2>
              <p> 귀하께서는 슬.대.생의 서비스를 이용하며 발생하는 모든 개인정보 관련 질문을 오류제보 메뉴로 접수 할 수 있습니다. 또한 기능 오류 및 추가 제작요청 사항 부분도 오류제보 메뉴를 통하여 제보 부탁드립니다. </p>
            </div>
          </div>
        </div>
      </div>
      <!-- End Page Header -->
      <!-- ======= Contact Section ======= -->
      <section id="contact" class="contact">
        <div class="container">
          <div class="row justify-content-center mt-4">
            <div class="col-lg-9">
              <form action="/contact.do" method="post" role="form" class="php-email-form" id="insertForm">
                <div class="row">
                  <div class="col-md-6 form-group">
                    <input type="text" name="name" class="form-control" id="name" placeholder="제목" required>
                  </div>
                  <div class="col-md-6 form-group mt-3 mt-md-0">
                    <input type="email" class="form-control" name="email" id="email" placeholder="답변받을 Eemail" required>
                  </div>
                </div>
                <div class="form-group mt-3">
                  <input type="text" class="form-control" name="subject" id="subject" placeholder="주제" required>
                </div>
                <div class="form-group mt-3">
                  <textarea class="form-control" name="message"  id="message" rows="5" placeholder="내용 작성" required></textarea>
                </div>
                <div class="my-3">
                  <div class="loading">Loading</div>
                  <div class="error-message"></div>
                  <div class="sent-message">메시지를 보내주셔서 감사합니다. 빠른시일 내 답변드리겠습니다.</div>
                </div>
                <div class="text-center">
                  <button type="button" id="submitBtn" >내용 전송</button>
                </div>
              </form>
            </div>
            <!-- End Contact Form -->
          </div>
        </div>
      </section>
      <!-- End Contact Section -->
    </main>



<script>

$(function(){
	   
	   var submitBtn = $("#submitBtn");
	   var insertForm = $("#insertForm");
	   
	   // 등록 버튼 클릭 시 이벤트
	   submitBtn.on("click", function(){

	      var subject = $("#subject").val();
	      var message = $("#message").val();
	      var email = $("#email").val();
	    
	      
	      if(subject == null || subject==""){
	            alert("제목을 입력해주세요!");
	            return false;
	        }
	        if(message == null || message==""){
	            alert("내용을 입력해주세요!");
	            return false;
	        }
	        insertForm.submit();
	   });
	   console.log(name);
	});
</script>


 <%@include file="../footer.jsp" %>