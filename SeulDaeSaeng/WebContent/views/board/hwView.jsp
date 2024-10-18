<%@page import="java.time.LocalDate"%>
<%@page import="kr.or.ddit.board.vo.HomeworkVO"%>
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
    IBoardService boardService = BoardServiceImpl.getInstance();
    
	String msg = session.getAttribute("msg") == null ? "" : (String) session.getAttribute("msg");
	session.removeAttribute("msg");

	int usersNo = (int)session.getAttribute("usersNo");
    MemberVO wd = (MemberVO)request.getAttribute("writerDetail");
    HomeworkVO hw = (HomeworkVO)request.getAttribute("hwDetail");
    int levelChk = (int)request.getAttribute("levelChk");
	int usersRole = (int)session.getAttribute("usersRole");
	List<FileDetailVO> mySubmit = (List<FileDetailVO>)request.getAttribute("mySubmit");
	List<FileDetailVO> fileList = boardService.getHwFileList(hw.getHwNo());
    
    int classNo = hw.getHwClass();
%>

 <main data-aos="fade" data-aos-delay="700">
      <div class="page-header sub d-flex align-items-center">
        <div class="container position-relative">
          <div class="row d-flex justify-content-center">
            <div class="text-center">
              <p class="mb-5 d-flex justify-content-between">
              <%
              if(usersRole==1){
              %>
              	<a href="<%=request.getContextPath() %>/classTeacherBoard.do?levelChk=<%=levelChk %>"><i class="bi bi-chevron-left"></i> 뒤로가기 </a>
              <%
              }else{
              %>
              <a href="<%=request.getContextPath() %>/classBoard.do?levelChk=<%=levelChk %>"><i class="bi bi-chevron-left"></i> 뒤로가기 </a>
              <%
              }
              %>
              </p>
              <p class="mb-0">
                <small class="badge bg-body-secondary mb-3 me-2">
                  <%=hw.getHwClass() %>호 숙제
                </small>
              </p>
              <h3><%=hw.getHwTitle() %></h3>
              <small>숙제기한 : <%=hw.getHwStart() %> ~ <%=hw.getHwEnd() %></small><br>
              <small>작성자 : <%=wd.getMemNick() %></small>
            </div>
          </div>
        </div>
      </div>

      <section class="view">
         <div class="container pt-5 pb-5" data-aos="fade-up">
            <div style="word-wrap: break-word;"><%=hw.getHwCon() %></div>
         <%
         if(usersRole==2){ 
        	 LocalDate now = LocalDate.now();
             LocalDate finalDt = hw.getHwEnd();
             if(now.isAfter(finalDt)){
        %>
            <br>
            <script>
                alert('과제 제출기간이 지났습니다');
            </script>
            	 
        <%     }else{  %>
             <br>
             <form class="file-drop" method="post" action="/file/upload.do?levelChk=<%=levelChk %>&hwNo=<%=hw.getHwNo() %>" enctype="multipart/form-data">
               <input type="file" name="upload" id="upload-file" multiple>
               <!-- 아래 label 내용 변경하지 마세요. js와 연동 -->
               <label for="upload-file">파일을 드레그하거나 클릭해서 등록</label>
               <button type="submit" ><i class="bi bi-arrow-bar-up"></i></button>
             </form>
         <%   	 
             }
         %>
         <%
         if(mySubmit!=null){
         %>
                     <div class="list-group">
             <%
              for(int i=0 ; i<mySubmit.size() ; i++){
            	  FileDetailVO fd = mySubmit.get(i);
             %>
              <a href="<%=request.getContextPath() %>/file/download.do?fileNo=<%=fd.getFileNo() %>" class="list-group-item py-2 d-flex justify-content-between align-items-center">
                <span>
                  <i class="bi bi-download px-2"></i> <%=fd.getFileOgname() %> </span>
                <span> <%=fd.getFileDt() %></span>
              </a>
              <%
              }
              %>
            </div>
         <%
         }
         %>
         <%
         }else if(usersRole==1){
            if(fileList != null && fileList.size()!=0){
        	 for(FileDetailVO file : fileList){
        		 MemberVO mem = boardService.getHwSubmitMem(file.getFileNo());
         %>
   
         <div class="container" data-aos="fade-up" style="border-top:none;">
          <a href="<%=request.getContextPath() %>/file/download.do?fileNo=<%=file.getFileNo() %>" download 
          class="attached-file d-flex justify-content-between align-items-center" style="margin-bottom: 10px;">
            <span><i class="bi bi-download px-2"></i><%=file.getFileOgname() %> </span>
            <span>제출자 : <%=mem.getMemName() %></span>
          </a>
         </div>
         
         <%
           }
          }else{
          %>
          <p> 업로드된 숙제 제출이 없습니다. <P>
         <% 
          }
         }
         %>
         <div class="btn-box container d-flex align-items-center justify-content-center pb-5 pt-5 gap-2">
          <%
          //세션에서 꺼내와야함. + 위에 댓글입력창 닉네임 또한 함께 수정 + 댓글 수정 삭제 보이는 부분도users에서 역할role로 비교해야함!!!!!1
          if(usersRole==1){
          %>
          <a href="<%=request.getContextPath()%>/classTeacherBoard.do?levelChk=<%=levelChk %>" type="button" class="btn btn-secondary">메인으로</a>
          	<%
          		if(wd.getUsersNo() == usersNo){
          	
          	%>
          	<a href="<%=request.getContextPath()%>/homework/edit.do?levelChk=<%=levelChk %>&hwNo=<%=hw.getHwNo() %>" type="button" class="btn btn-secondary">수정하기</a>
          	<a href="<%=request.getContextPath()%>/homework/delete.do?levelChk=<%=levelChk %>&hwNo=<%=hw.getHwNo() %>" onclick="return confirm('삭제하시겠습니까?');" type="button" class="btn btn-secondary">삭제하기</a>
          	<%
          		}
          	%>
          <%
          }else if(usersRole==2){
          %>
          <a href="<%=request.getContextPath()%>/classBoard.do?levelChk=<%=levelChk %>" type="button" class="btn btn-secondary">메인으로</a>
          <%
          if(mySubmit!=null && mySubmit.size()!=0){
           %>
          <a href="#" type="button" class="btn btn-secondary" style="background-color:orange; border-color:orange;">제출완료</a>
          <%
          }else{
          %>
          <a href="#" type="button" class="btn btn-secondary" style="background-color:red; border-color:red;">미제출</a>
          <%
          }
          }
          %>
        </div>
        
        <!-- END MENU -->
      </section>
    </main>
<script>
	window.onload = function() {
		var msg = '<%= msg %>';
		if(msg != null && msg != '') alert(msg);
	
	};
	
</script>
<%@include file="/footer.jsp" %>