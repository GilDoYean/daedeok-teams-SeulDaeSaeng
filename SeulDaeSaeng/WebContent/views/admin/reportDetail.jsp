<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="/header.jsp" %> 
<%
	BoardVO bv = (BoardVO)request.getAttribute("boardDetail");

	MemberVO wd = (MemberVO)request.getAttribute("WrDetail");
%>

<%
	String msg = session.getAttribute("msg") == null ? "" : (String) session.getAttribute("msg");
	session.removeAttribute("msg");
	int level = bv.getBoardLevel();
	String boardName = null;
	String board = null;
	if(level == 1) {
			boardName = "자유게시판";
			board = "freeBoard";
		}
		else if(level == 2) {
			boardName = "공부게시판";
			board = "studyBoard";
		}
		else if(level == 3) {
			boardName = "공지사항";
			board = "noticeBoard";
		}
	
%>
<main data-aos="fade" data-aos-delay="700">
      <!-- ======= End Page Header ======= -->
      <div class="page-header sub d-flex align-items-center">
        <div class="container position-relative">
          <div class="row d-flex justify-content-center">
            <div class="text-center">
              <p class="mb-5 d-flex justify-content-between">
              	
                   <a href="/freeBoard.do"><i class="bi bi-chevron-left"></i> 뒤로가기 </a>
                
                <small class="look"><i class="bi bi-eye"></i><%=bv.getBoardHit() %> </small>
              </p>
              <p class="mb-0">
                <small class="badge bg-body-secondary mb-3 me-2">
              <%=boardName %>
                </small>
                <small><%=bv.getBoardAt() %></small>
              </p>
              <h3><%=bv.getBoardTitle() %></h3>
              <small>작성자 : <%=wd.getMemNick() %></small>
            </div>
          </div>
        </div>
      </div>
      <!-- End Page Header -->
      <section class="view">
        
      <!-- START 이전글/다음글 구분 출력 -->
        
        <div class="container d-flex p-0">
             
             <!-- 이전글 -->
             
          
          <div class="prve col-md-6 col-12 none-post ps-2">
            <a href="#" class="d-block pt-4 pb-4">
              <span class="d-inline-block pe-3"> <i class="bi bi-chevron-up"></i></span> 
              <b></b>
            </a>
          </div>
             
              
              <!-- 다음글 -->
              
             <div class="next col-md-6 col-12 text-end none-post pe-2">
              <a href="#" class="d-block pt-4 pb-4">
                <b class="pe-3"></b>
                <span class="d-inline-block"> <i class="bi bi-chevron-down"></i></span>
             </a>
            </div>
             
         </div>
         
        <div class="container pt-5 pb-5 aos-init aos-animate" data-aos="fade-up">
          
          <div style="word-wrap: break-word;"><p>테스트 게시판<br></p></div>

 
          
        <div class="btn-box container d-flex align-items-center justify-content-center pb-5 pt-5 gap-2">
                
   		<form action="<%=request.getContextPath()%>/views/reportDetail.do" method="post">
   			<input type="hidden" name="boardNo" value="<%=bv.getBoardNo()%>">
          <button type="submit" class="btn btn-secondary">블라인드처리</button>
         </form>
        </div>
        
        <!-- END MENU -->
      </div></section>
    </main>

<%@include file="/footer.jsp" %>


