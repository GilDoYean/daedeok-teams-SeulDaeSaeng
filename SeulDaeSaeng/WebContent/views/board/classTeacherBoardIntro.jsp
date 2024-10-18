<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="/header.jsp" %>

<div class="page-header d-flex align-items-center">
      <div class="container position-relative">
        <div class="row d-flex justify-content-center">
          <div>
            <h2>반 선택</h2>
          </div>
        </div>
      </div>
    </div>
    <!-- End Header -->
    <main data-aos="fade" data-aos-delay="700" class="aos-init aos-animate">
      <section id="teacherIntro" class="container pt-0 pb-0">
        <div class="row gy-4">
        <%
        for(int i = 301 ; i < 306 ; i++){
        %>
          <a href="<%=request.getContextPath()%>/classTeacherBoard.do?levelChk=<%=i %>" class="col-xl-3 col-lg-4 col-md-6 mt-0 p-3">
            <p class="mb-0 p-4 text-center" id="class-no">
              <%=i %>호
            </p>
          </a> 
        <%
        }
        %>
        </div>
      </section>
    </main>
    <!-- End #main -->
<%@include file="/footer.jsp" %>