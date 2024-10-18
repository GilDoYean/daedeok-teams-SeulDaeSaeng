<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="/header.jsp" %>

    <main data-aos="fade" data-aos-delay="700" >
    <!-- <main> -->
      <!-- ======= End Page Header ======= -->
      <div class="page-header pb-4">
        <div class="container text-center">
          <h3>304호 채팅</h3>
        </div>
      </div>
      <!-- End Page Header -->
      <!-- ======= chat Section ======= -->
      <section id="chat" class="gallery-single pb-5">
        <div class="container">
          <div class="chat">
            <div class="card">
              <div class="card-body msg_card_body">
                <div class="d-flex justify-content-start mb-4">
                  <div class="img_cont_msg">
                    <img src="https://static.turbosquid.com/Preview/001292/481/WV/_D.jpg" class="rounded-circle user_img_msg" onError="this.onerror=null; this.src='https://i.imgur.com/BFfnYMT.jpeg';" >
                  </div>
                  <div class="msg_cotainer"> Hi, how are you Samim? <span class="msg_time">8:40 AM, Today</span>
                  </div>
                </div>
                <div class="d-flex justify-content-end mb-4">
                  <div class="msg_cotainer_send"> Ok, thank you have a good day <span class="msg_time_send">9:10 AM, Today</span>
                  </div>
                  <div class="img_cont_msg">
                    <img src="img/testimonials/testimonials-2.jpg" class="rounded-circle user_img_msg" onError="this.onerror=null; this.src='https://i.imgur.com/BFfnYMT.jpeg';" >
                  </div>
                </div>
                <!-- Other message cards go here -->
              </div>
              <div class="card-footer">
                <div class="input-group">
                  <div class="input-group-append">
                    <span class="input-group-text attach_btn">
                      <i class="bi bi-paperclip"></i>
                    </span>
                  </div>
                  <textarea name="" class="form-control type_msg" placeholder="Type your message..."></textarea>
                  <div class="input-group-append">
                    <span class="input-group-text send_btn">
                      <i class="bi bi-send"></i>
                    </span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
      <!-- End chat Section -->
    </main>
    <!-- End #main -->
    
<%@include file="/footer.jsp" %>