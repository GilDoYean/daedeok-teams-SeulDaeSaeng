<%@page import="kr.or.ddit.member.vo.UsersVO"%>
<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
//String currentJSP = request.getServletPath();
//String fileName = currentJSP.substring(currentJSP.lastIndexOf("/") + 1, currentJSP.lastIndexOf("."));
//String fileExtension = currentJSP.substring(currentJSP.lastIndexOf(".") + 1);

String uri = request.getRequestURI();
String id = (String) session.getAttribute("usersId");

String isAdminOk = (String) session.getAttribute("isAdminOk");

%>


<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>슬기로운 대덕 생활 - GallerySingle</title>
    <meta content="개발 공부만 하기 바쁜 대덕인재개발원 사람들을 위해 점심머먹지? 오늘 어떤걸배우지? 바로 볼수 있습니다!" name="description">
    <meta content="대덕인재개발원, 대전광역시, 개발자, 커뮤니티, 점심추천, 시간표" name="keywords">
    <!-- Favicons -->
    <link href="/favicon.ico" rel="icon">
    <!-- Fonts -->
    <link rel="stylesheet" href="/css/font.css">
    
    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"  ></script>
    
    <link href="/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">
    <link href="/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
    <link href="/vendor/aos/aos.css" rel="stylesheet">

    <!-- Template Main CSS File -->
     <link href="/css/main.css" rel="stylesheet">
    
    <% if(request.getRequestURI().contains("index")){ %>
         <link href="/css/index.css" rel="stylesheet">
    <% }else if(request.getRequestURI().contains("timetable")){ %>
      	<link href="/css/timetable.css" rel="stylesheet">
    <% } else if(request.getRequestURI().contains("reviewWrite")){ %>
		<link href="/css/reviewWrite.css" rel="stylesheet">
	<% }else if(request.getRequestURI().contains("write") || request.getRequestURI().contains("edit") || request.getRequestURI().contains("Write") || request.getRequestURI().contains("Edit")){ %>
    	<link rel="stylesheet" href="/css/bootform2.css">  
    <% } else if(request.getRequestURI().contains("restaurantUpDate")){%>
       <link href="/css/restaurantUpDate.css" rel="stylesheet">
   	<% } else if(request.getRequestURI().contains("restaurantView")){ %>
   		<link href="/css/restaurantView.css" rel="stylesheet">
   	<% } else if(request.getRequestURI().contains("404")){ %>
   		<link href="/css/404.css" rel="stylesheet">
   	<% } %>
  </head>
  <body>
    <!-- ======= Header ======= -->
    <header id="header" class="header d-flex align-items-center fixed-top">
      <div class="container-fluid d-flex align-items-center justify-content-between">
        <a href="<%=request.getContextPath()%>/main.do" class="logo d-flex align-items-center  me-auto me-lg-0">
          <h1>슬.대.생</h1>
        </a>
        <nav id="navbar" class="navbar">
          <ul>
             <% if(id!=null && !id.isEmpty()){ %>
               <li>
                 <a href="<%=request.getContextPath()%>/classBoard.do">반별게시판</a>
               </li>
               <li class="dropdown">
                 <a href="<%=request.getContextPath()%>/allBoard.do">
                   <span>전체게시판</span>
                   <i class="bi-caret-down-fill"></i>
                 </a>
                 <ul>
                   <li>
                     <a href="<%=request.getContextPath()%>/freeBoard.do">자유게시판</a>
                   </li>
                   <li>
                     <a href="<%=request.getContextPath()%>/studyBoard.do">공부게시판</a>
                   </li>
                   <li>
                     <a href="<%=request.getContextPath()%>/noticeBoard.do">공지게시판</a>
                   </li>
                 </ul>
               </li>
             <% } %>
         <li class="dropdown">
            <a href='<%=request.getContextPath() %>/restaurant/find.do?mcls=all'>
              <span>식당찾기</span>
              <i class="bi-caret-down-fill"></i>
            </a>
            <ul>
               <li>
                   <a href='<%=request.getContextPath() %>/restaurant/find.do?mcls=I201'>한식</a>
               </li>
               <li>
                 <a href='<%=request.getContextPath() %>/restaurant/find.do?mcls=I202'>중식</a>
               </li>
               <li>
                 <a href='<%=request.getContextPath() %>/restaurant/find.do?mcls=I203'>일식</a>
               </li>
               <li>
                 <a href='<%=request.getContextPath() %>/restaurant/find.do?mcls=I204'>서양식</a>
               </li>
               <li>
                 <a href='<%=request.getContextPath() %>/restaurant/find.do?mcls=I205'>동남아식</a>
               </li>
               <li>
                 <a href='<%=request.getContextPath() %>/restaurant/find.do?mcls=I210'>간이식</a>
               </li>
               <li>
                 <a href='<%=request.getContextPath() %>/restaurant/find.do?mcls=I211'>주점</a>
                </li>
               <li>
                 <a href='<%=request.getContextPath() %>/restaurant/find.do?mcls=I212'>카페</a>
                </li>
            </ul>
         </li>
            <li>
              <a href="<%=request.getContextPath()%>/gourmet/list.do">슬대생의 추천맛집</a>
            </li>
          </ul>
        </nav>
        <!-- .navbar -->
        <div class="d-flex align-items-center">
          <div class="header-social-links">
            <div class="dropdown">
              <button class="btn dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                <i class="bi bi-person-circle"></i>
                <!-- <img src="img/testimonials/testimonials-2.jpg" class="testimonial-img" alt=""> -->
              </button>
              <ul class="dropdown-menu">

            <%
               if(id == null) {
           	%>

                   <li>
                        <a class="dropdown-item" href="<%=request.getContextPath() %>/member/join.do">회원가입</a>
                   </li>
                   <li>
                   		<a class="dropdown-item" href="<%=request.getContextPath() %>/login.do">로그인</a>
              	   </li>

            <% 
               }else{
            %>
                   <li>
                      <%
                         if(isAdminOk == null){
                      %>
                        <a class="dropdown-item" href="<%=request.getContextPath() %>/member/myPageHome.do">마이페이지</a>
                      <%      
                         }else{
                      %>
                        <a class="dropdown-item" href="<%=request.getContextPath() %>/views/adminPage.do">관리자 페이지</a>
                      <%   
                         }
                      %>
                   </li>
               <li>
                  <a class="dropdown-item" href="<%=request.getContextPath() %>/logout.do">로그아웃</a>
               </li>
            <% 
               }
            %>


                <!-- <li><a class="dropdown-item" href="logout.html">로그아웃</a></li><li><a class="dropdown-item" href="myPage.html"><span>정윤지</span>페이지</a></li>-->
              </ul>
            </div>
          </div>
          <i class="mobile-nav-toggle mobile-nav-show bi bi-list"></i>
          <i class="mobile-nav-toggle mobile-nav-hide d-none bi bi-x"></i>
        </div>
      </div>
    </header>