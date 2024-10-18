<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.apache.logging.log4j.core.impl.MementoMessage"%>
<%@page import="kr.or.ddit.contact.vo.ContactVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="/header.jsp" %>
 <%
 	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
      		String formatDate = sdf.format(new Date());
 %>
<%

	ContactVO contactVO =(ContactVO) request.getAttribute("contactVO");
%>
 <main class="pb-5" data-aos="fade" data-aos-delay="700">
      <!-- ======= End Page Header ======= -->
      <div class="page-header d-flex align-items-center">
        <div class="container position-relative">
          <div class="row d-flex justify-content-center">
            <div class="col-lg-6 text-center">
              <h2>오류제보</h2>
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
                <div class="row">
                  <div class="col-md-6 form-group">
                    <p><%=sdf.format(contactVO.getqAt()) %></p>
                  </div>
                  <div class="col-md-6 form-group mt-3 mt-md-0">
                    <p><%=contactVO.getqEmail() %></p>
                  </div>
                </div>
                <div class="form-group mt-3">
                  <p><%=contactVO.getqTitle() %></p>
                </div>
                <div class="form-group mt-3">
                  <p><%=contactVO.getqCon() %></p>
                </div>
              
                <div class="text-center">
        

                  <a href="<%=request.getContextPath() %>/views/contactSend.do?qNO=<%=contactVO.getqNO()%>" id="submitBtn">답변 전송</a>

                </div>
          
            </div>
            <!-- End Contact Form -->
          </div>
        </div>
      </section>
      <!-- End Contact Section -->
    </main>



<%@include file="/footer.jsp" %>