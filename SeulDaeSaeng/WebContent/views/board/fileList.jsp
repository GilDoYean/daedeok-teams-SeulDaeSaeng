<%@page import="kr.or.ddit.board.vo.FileDetailVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@page import="kr.or.ddit.board.service.BoardServiceImpl"%>
<%@page import="kr.or.ddit.board.service.IBoardService"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/header.jsp"%>
<%
    int usersRole = (int)session.getAttribute("usersRole");
    List<FileDetailVO> fileList = (List<FileDetailVO>)request.getAttribute("fileList");
    int levelChk = (int)request.getAttribute("levelChk");
    //페이징 기능
    int itemsPerPage = 5;
    int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
    int totalItems = (fileList != null) ? fileList.size() : 0;
    int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);
    int startIndex = (currentPage - 1) * itemsPerPage;
    int endIndex = Math.min(startIndex + itemsPerPage, totalItems);
   
%>
<main data-aos="fade" data-aos-delay="700">
	<!-- ======= End Page Header ======= -->
	<div class="page-header d-flex align-items-center">
		<div class="container position-relative">
			<div class="row d-flex justify-content-center">
			<%
			if(usersRole==1){
			%>
			   <a href="<%=request.getContextPath()%>/classTeacherBoard.do?levelChk=<%=levelChk%>" class="pb-4 backbtn"><i class="bi bi-chevron-left"></i> 뒤로가기 </a>
			<%
			}else{
			%>
			   <a href="<%=request.getContextPath()%>/classBoard.do?levelChk=<%=levelChk%>" class="pb-4 backbtn"><i class="bi bi-chevron-left"></i> 뒤로가기 </a>
			<%
			}
			%>
				<div>
					<h2><%=levelChk %>호 파일</h2>
				</div>
			</div>
		</div>
	</div>
	<!-- End Page Header -->
	<section class="board mb-5">
		<!-- End Page Header -->
	<section class="board mb-5">
		<div class="container" data-aos="fade-up">
			<div
				class="row mb-3 d-flex justify-content-between align-items-center">
				<p class="col-3">
                               총 <b><%= (fileList != null) ? fileList.size() : 0 %></b> 개
                </p>
			</div>
			<div class="list-group">
				<%
				if (fileList == null || fileList.isEmpty()) {
                %>
				<p>공유된 파일이 없습니다</p>
				<%
                   }else{
                	   int idx = (currentPage-1)*5;
                	   for (int i = startIndex; i < endIndex; i++) {		   
                		   //level별 이름지정
                    	   FileDetailVO fd = fileList.get(i);
                %>
                
				  <a href="<%=request.getContextPath()%>/file/download.do?fileNo=<%=fd.getFileNo() %>" class="list-group-item">
					<div class="d-flex w-100 justify-content-between align-items-center">
						<h5 class="mb-2 text-truncate">
							<small class="attach"> <i class="bi bi-paperclip"></i>
							</small>
							<%=fd.getFileSavednm()%>
						</h5>
						<small class="badge bg-light">
						<%=levelChk %>호
						</small>
					</div>
					<div class="d-flex w-100 justify-content-between align-items-center">
						<small class="days"><%=fd.getFileDt() %></small> <small
							class="look"><%=fd.getFileSize() %>
						</small>
					</div>
				</a>
				
				<%

                       }
                   }
                %>
			</div>


    <!-- Pagination links -->
     <nav aria-label="Page navigation" class = "d-flex justify-content-center align-items-center pt-5 pm-5">
        <ul class="pagination">
            <% if (currentPage > 1) { %>
                <li class="page-item">
                    <a class="page-link" href="<%= request.getContextPath() %>?levelChk=<%=levelChk %>&page=<%= currentPage - 1 %>">&laquo;</a>
                </li>
            <% } %>
            
            <% for (int i = 1; i <= totalPages; i++) { %>
                <li class="page-item <%= (i == currentPage) ? "active" : "" %>">
                    <a class="page-link" href="<%= request.getContextPath() %>?levelChk=<%=levelChk %>&page=<%= i %>"><%= i %></a>
                </li>
            <% } %>

            <% if (currentPage < totalPages) { %>
                <li class="page-item">
                    <a class="page-link" href="<%= request.getContextPath() %>?levelChk=<%=levelChk %>&page=<%= currentPage + 1 %>">&raquo;</a>
                </li>
            <% } %>
        </ul>
    </nav>
	</div>
	</section>
	</section>
</main>
<%@include file="/footer.jsp"%>