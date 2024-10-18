
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="/header.jsp" %>
<main data-aos="fade" data-aos-delay="700">
      <!-- ======= End Page Header ======= -->
      <div class="page-header d-flex align-items-center">
        <div class="container position-relative">
          <div class="row d-flex justify-content-center">
            <div class="col-lg-6 text-center">
              <h2>로그인</h2>
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
              <form action="/login.do" method="post" role="form" >
                <div class="form-group">
                  <input type="text" class="form-control" name="id" id="id" placeholder="아이디" required>
                  <div class="invalid-feedback"> 아이디를 확인해주세요. </div>
                </div>
                <div class="form-group mt-3">
                  <input type="password" class="form-control" name="pw" id="pw" placeholder="비밀번호" required>
                  <div class="invalid-feedback"> 비밀번호를 확인해주세요. </div>
                </div>
                <div class="text-center mt-5">
                  <button type="submit">로그인</button>
                </div>
              </form>
              <hr class="mt-5">
              <div class="w-100 d-flex gap-5 text-center justify-content-center">
                <a href="/findId.do">회원 아이디 찾기</a>
                <div class="vr"></div>
                <a href="/findPass.do">회원 비밀번호 찾기</a>
              </div>
            </div>
            <!-- End Contact Form -->
          </div>
        </div>
      </section>
      <!-- End Contact Section -->
    </main>

<%@include file="/footer.jsp" %>