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
   List<BoardVO> boardList = (List<BoardVO>)request.getAttribute("boardList");
    int levelChk = (int)request.getAttribute("levelChk");
    int usersRole = (int)session.getAttribute("usersRole");
    
    String msg = session.getAttribute("msg") == null ? "" : (String) session.getAttribute("msg");
    session.removeAttribute("msg");
    
    String board = null;
    String boardName = null;
    if(levelChk == 0){
       board = "allBoard";
       boardName = "전체게시판";
    }else if(levelChk == 1){
       board = "freeBoard";
       boardName = "자유게시판";
    }else if(levelChk == 2){
       board = "studyBoard";
       boardName = "공부게시판";
    }else if(levelChk == 3){
       board = "noticeBoard";
       boardName = "공지사항";
    }
    
    // 검색 옵션 선택
    String option = null;
    if(request.getAttribute("searchOption") != null){
       option = (String)request.getAttribute("searchOption");
    }
    // 검색 텍스트
    String searchTxt = null;
    if(request.getAttribute("searchText") != null){
    	searchTxt = (String)request.getAttribute("searchText");
    }
    
    //페이징 기능
    int itemsPerPage = 5;
    int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
    int totalItems = (boardList != null) ? boardList.size() : 0;
    int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);
    int startIndex = (currentPage - 1) * itemsPerPage;
    int endIndex = Math.min(startIndex + itemsPerPage, totalItems);
   
%>
<main data-aos="fade" data-aos-delay="700">
   <!-- ======= End Page Header ======= -->
   <div class="page-header d-flex align-items-center">
      <div class="container position-relative">
         <div class="row d-flex justify-content-center">
            <div>
               <h2><%=boardName %></h2>
               <p>
                  <a class="cta-btn" href="<%=request.getContextPath()%>/allBoard.do">전체게시판</a> 
                  <a class="cta-btn" href="<%=request.getContextPath()%>/freeBoard.do">자유게시판</a>
                  <a class="cta-btn" href="<%=request.getContextPath()%>/studyBoard.do">공부게시판</a> 
                  <a class="cta-btn" href="<%=request.getContextPath()%>/noticeBoard.do">공지사항</a>
               </p>
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
                               총 <b><%= (boardList != null) ? boardList.size() : 0 %></b> 개
                </p>
            <div class="input-group input-group-sm col-4">
               <%
                  if(option.equals("all")){
               %>
               <button class="btn dropdown-toggle" type="button"
                  data-bs-toggle="dropdown" aria-expanded="false">전체</button>
               <ul class="dropdown-menu">
                  <li><a class="dropdown-item" href="<%=request.getContextPath()%>/<%=board %>.do?searchOption=title">제목</a></li>
                  <li><a class="dropdown-item" href="<%=request.getContextPath()%>/<%=board %>.do?searchOption=content">내용</a></li>
                  <li><a class="dropdown-item" href="<%=request.getContextPath()%>/<%=board %>.do?searchOption=nickname">닉네임</a></li>
               </ul>
               <%
                  }else if(option.equals("title")){
               %>
               <button class="btn dropdown-toggle" type="button"
                  data-bs-toggle="dropdown" aria-expanded="false" value="title">제목</button>
               <ul class="dropdown-menu">
                  <li><a class="dropdown-item" href="<%=request.getContextPath()%>/<%=board %>.do?searchOption=all">전체</a></li>
                  <li><a class="dropdown-item" href="<%=request.getContextPath()%>/<%=board %>.do?searchOption=content">내용</a></li>
                  <li><a class="dropdown-item" href="<%=request.getContextPath()%>/<%=board %>.do?searchOption=nickname">닉네임</a></li>
               </ul>
               <%
                  }else if(option.equals("content")){
               %>
               <button class="btn dropdown-toggle" type="button"
                  data-bs-toggle="dropdown" aria-expanded="false" value="content">내용</button>
               <ul class="dropdown-menu">
                  <li><a class="dropdown-item" href="<%=request.getContextPath()%>/<%=board %>.do?searchOption=all">전체</a></li>
                  <li><a class="dropdown-item" href="<%=request.getContextPath()%>/<%=board %>.do?searchOption=title">제목</a></li>
                  <li><a class="dropdown-item" href="<%=request.getContextPath()%>/<%=board %>.do?searchOption=nickname">닉네임</a></li>
               </ul>
               <%
                  }else if(option.equals("nickname")){
               %>
               <button class="btn dropdown-toggle" type="button"
                  data-bs-toggle="dropdown" aria-expanded="false" value="nickname">닉네임</button>
               <ul class="dropdown-menu">
                  <li><a class="dropdown-item" href="<%=request.getContextPath()%>/<%=board %>.do?searchOption=all">전체</a></li>
                  <li><a class="dropdown-item" href="<%=request.getContextPath()%>/<%=board %>.do?searchOption=title">제목</a></li>
                  <li><a class="dropdown-item" href="<%=request.getContextPath()%>/<%=board %>.do?searchOption=content">내용</a></li>
               </ul>
               <%
                  }
               %>
               
                  <form action="<%=request.getContextPath()%>/<%=board %>.do?searchOption=<%=option %>" method="get" role="form" id="searchForm">

               <%
               	  if(searchTxt != null){
               %>
                  <input type="text" id="searchText" name="searchText" value=<%=searchTxt%> class="form-control"
                     aria-label="Text input with dropdown button" >
               <%
               	  }else{
               %>
                  <input type="text" id="searchText" name="searchText" class="form-control"
                     aria-label="Text input with dropdown button" >
                <%
               	  }
                %>
                <input type="hidden" id="searchOption" name="searchOption" value="<%=option %>">
               </form>
            </div>
         </div>
         <div class="list-group">
            <%
            if (boardList == null || boardList.isEmpty()) {
                %>
            <p>작성된 게시글이 없습니다</p>
            <%
                   }else{

                      int idx = (currentPage-1)*5;

                      for (int i = startIndex; i < endIndex; i++) {
                         
                         //level별 이름지정
                          BoardVO bv = boardList.get(i);
                          int level2 = bv.getBoardLevel();
                           
                           // html 제거
                           String ogText = bv.getBoardCon();
                      	   String regex = "<[^>]*>";
                      	   String pureText = ogText.replaceAll(regex, "");
                           
                %>
                
              <a href="<%=request.getContextPath()%>/board/detail.do?boardNo=<%=bv.getBoardNo() %>&idx=<%=idx %>&levelChk=<%=levelChk %>" class="list-group-item">
               <div class="d-flex w-100 justify-content-between align-items-center">
                  <h5 class="mb-2 text-truncate">
                     <small class="attach"> <i class="bi bi-paperclip"></i>
                     </small>
                     <%=bv.getBoardTitle()%>
                  </h5>
                  <small class="badge bg-light">
                  <%=boardName %>
                  </small>
               </div>
               <p class="mb-2 text-truncate"><%=pureText %></p>
               <div class="d-flex w-100 justify-content-between align-items-center">
                  <small class="days"><%=bv.getBoardAt() %></small> <small
                     class="look"> <i class="bi bi-eye"></i> <%=bv.getBoardHit() %>
                  </small>
               </div>
            </a>
            
            <%
                         idx++;
                       }
                   }
                %>
         </div>


    <!-- Pagination links -->
    <nav aria-label="Page navigation" class = "d-flex justify-content-center align-items-center pt-5 pm-5">
        <ul class="pagination">
            <% if (currentPage > 1) { %>
                <li class="page-item">
                <%
               	  if(searchTxt != null){
                %>
                    <a class="page-link" href="<%= request.getContextPath() + "?page=" + (currentPage - 1) %>&searchText=<%=searchTxt %>&searchOption=<%=option %>">&laquo;</a>
                <%
               	  }else{
                %>
                    <a class="page-link" href="<%= request.getContextPath() + "?page=" + (currentPage - 1) %>&searchOption=<%=option %>">&laquo;</a>
                <%
               	  }
                %>
                </li>
            <% } %>
            
            <% for (int i = 1; i <= totalPages; i++) { %>
                <li class="page-item <%= (i == currentPage) ? "active" : "" %>">
                <%
               	  if(searchTxt != null){
                %>
                    <a class="page-link" href="<%= request.getContextPath() + "?page=" + i %>&searchText=<%=searchTxt %>&searchOption=<%=option %>"><%= i %></a>
                <%
               	  }else{
                %>
                	<a class="page-link" href="<%= request.getContextPath() + "?page=" + i %>&searchOption=<%=option %>"><%= i %></a>
                <%
               	  }
                %>
                </li>
            <% } %>

            <% if (currentPage < totalPages) { %>
                <li class="page-item">
                <%
               	  if(searchTxt != null){
                %>
                    <a class="page-link" href="<%= request.getContextPath() + "?page=" + (currentPage + 1) %>&searchText=<%=searchTxt %>&searchOption=<%=option %>">&raquo;</a>
                <%
               	  }else{
                %>
                	<a class="page-link" href="<%= request.getContextPath() + "?page=" + (currentPage + 1) %>&searchOption=<%=option %>">&raquo;</a>
                <%
               	  }
                %>
                </li>
            <% } %>
        </ul>
    </nav>
    	
    	<%
    		if(levelChk != 3 || (levelChk == 3 && usersRole == 1)) {
    	%>
         <div class="container d-flex align-items-center justify-content-end pb-5 gap-2 p-0">
            <a href="<%=request.getContextPath()%>/board/write.do?levelChk=<%=levelChk %>" type="button" class="btn btn-outline-warning">글쓰기</a>
         </div>
         <%
    		}
         %>
      </div>
   </section>
   </section>
</main>

<script type="text/javascript">

   window.onload = function() {
      var msg = '<%= msg %>';
      if(msg != null && msg != '') alert(msg);
   
   };

    var buttons = document.querySelectorAll('.cta-btn');

    // 페이지 로드될 때 현재 URL을 확인하여 해당 버튼의 스타일을 변경
    var currentUrl = window.location.href;
    buttons.forEach(function (button) {
        if (currentUrl.includes(button.getAttribute('href'))) {
            button.classList.remove('gray');
            button.classList.add('orange');
        } else {
            button.classList.add('gray');
            button.classList.remove('orange');
        }
    });

    buttons.forEach(function (button) {
        button.addEventListener('click', function (event) {

            buttons.forEach(function (btn) {
                btn.classList.add('gray');
                btn.classList.remove('orange');
            });

            event.target.classList.remove('gray');
            event.target.classList.add('orange');
        });
    });
    
   // Enter 키를 누를 때 폼 제출 이벤트 처리
    document.getElementById("searchText").addEventListener("keypress", function(event) {
        if (event.key === "Enter") {
            event.preventDefault(); // 기본 제출 동작 방지
            document.getElementById("searchForm").submit(); // 폼 제출
        }
    });

</script>

<%@include file="/footer.jsp"%>