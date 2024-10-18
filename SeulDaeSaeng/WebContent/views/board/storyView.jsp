<%@page import="kr.or.ddit.board.vo.StoryVO"%>
<%@page import="com.itextpdf.text.log.SysoCounter"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.board.service.BoardServiceImpl"%>
<%@page import="kr.or.ddit.board.service.IBoardService"%>
<%@page import="kr.or.ddit.board.vo.ReplyVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.board.vo.FileDetailVO"%>
<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="/header.jsp" %>
<%
    IBoardService service = BoardServiceImpl.getInstance();
    MemberVO memDetail = (MemberVO)session.getAttribute("memDetail");
    
    int storyNo = (int) request.getAttribute("storyNo");
	List<StoryVO> storyList = service.getStoryList(memDetail.getUsersNo());
	StoryVO storyOne = service.getStoryOne(storyNo);
	FileDetailVO file = service.getFileDetail(storyOne.getFileNo());
	
%>

 <main data-aos="fade" data-aos-delay="700">
      <!-- ======= End Page Header ======= -->
      <div class="page-header sub d-flex align-items-center">
        <div class="container position-relative">
          <div class="row d-flex justify-content-center">
            <div class="text-center">
              <p class="mb-5 d-flex justify-content-between">
              	<a href="/allBoard.do"><i class="bi bi-chevron-left"></i> 뒤로가기 </a>
              </p>
              <p class="mb-0">
                <small class="badge bg-body-secondary mb-3 me-2">나의 스토리</small>
                <small><%=storyOne.getStoryAt() %></small>
              </p>
              <h3><%=storyOne.getStoryCon() %></h3>
            </div>
          </div>
        </div>
      </div>
      <!-- End Page Header -->
      <section class="view">
      
      <!-- START 파일  -->     
        <div class="container pt-5 pb-5" data-aos="fade-up">
          <a href="<%=request.getContextPath() %>/file/download.do?fileNo=<%=file.getFileNo() %>" download class="attached-file d-flex justify-content-between align-items-center">
            <span>
              <i class="bi bi-download px-2"></i><%=file.getFileOgname() %> </span>
            <span><%=file.getFileSize() %></span>
          </a>
        <!-- END 파일  -->
        
        <div class="btn-box container d-flex align-items-center justify-content-center pb-5 pt-5 gap-2">
           <a href="<%=request.getContextPath()%>/member/myPageHome.do"  type="button" class="btn btn-secondary"> 목록으로 </a>
           <a href="#" type="button" class="btn btn-secondary">수정하기</a>
           <a href="<%=request.getContextPath()%>/story/delete.do?storyNo=<%=storyOne.getStoryNo() %>" onclick="return confirm('삭제하시겠습니까?');" type="button" class="btn btn-secondary">삭제하기</a>
        </div>
      </section>
    </main>
<%@include file="/footer.jsp" %>