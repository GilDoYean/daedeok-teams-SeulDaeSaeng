<%@page import="kr.or.ddit.board.vo.HomeworkVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>

<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>

<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>     	
<!-- 	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script> -->
<%
 	int levelChk = (int)request.getAttribute("levelChk");
	HomeworkVO hwDetail = (HomeworkVO)request.getAttribute("hwDetail");
 	String boardName = "숙제 작성하기";
 	String board = "classTeacherBoard";
 %>
<main data-aos="fade" data-aos-delay="700" >
<!-- <main> -->
  <!-- ======= End Page Header ======= -->
  <div class="page-header sub d-flex align-items-center">
    <div class="container position-relative">
      <div class="row d-flex justify-content-center">
        <p class="mb-5 text-start">
        
          <a href="<%=request.getContextPath() %>/classTeacherBoard.do?levelChk=<%=levelChk %>">
            <i class="bi bi-chevron-left"></i> 뒤로가기
          </a>
        </p>
   <div>
          <h2><%=boardName %></h2>
          <!-- {{changeDetected}} -->
          <form action="<%=request.getContextPath()%>/homework/edit.do?levelChk=<%=levelChk %>" method="post" role="form" id="insertForm" class="php-email-form needs-validation" novalidate>
            <div class="contact">
             <div class="container pb-3">
              <input type="hidden" class="btn-check" name="level" value=<%=levelChk %>>
          <div class="form-group d-flex align-items-center pt-4">
           <p class="pe-2 pt-2">기간 설정 : <p>
           <input type="date" class="form-control" name="startDate" id="startDate" value="<%=hwDetail.getHwStart() %>" max="9999-12-31" style="width:180px; display:inline" required> ~ 
           <input type="date" class="form-control" name="endDate" id="endDate" value="<%=hwDetail.getHwEnd() %>" max="9999-12-31" style="width:180px;  display:inline" required>
          </div> 
          <div class="form-group">
               <input type="text" class="form-control" name="title" id="title" placeholder="제목" value="<%=hwDetail.getHwTitle() %>" required>
               <div class="invalid-feedback">제목을 작성해주세요.</div>
          </div>
        	<textarea class="form-control summernote" rows="5" id="content" name="content"><%=hwDetail.getHwCon() %></textarea>
        	<input type="hidden" id="levelChk" name="levelChk" value=<%=levelChk %>>
        	<input type="hidden" id="hwNo" name="hwNo" value=<%=hwDetail.getHwNo() %>>
			
        <div class="text-center mt-5 mb-5">
          <button type="submit" id="submitBtn" onclick="return confirm('수정하시겠습니까?');">수정하기</button>
        </div>
      </form>
       </div>
       </div>
      </div>
    </div>
</main>

<script type="text/javascript">
$(function(){
    var submitBtn = $("#submitBtn");
    var insertForm = $("#insertForm");

    submitBtn.on("click", function(){
        var title = $("#title").val();
        var content = $("#content").val();
        var startDate = $("#startDate").val();
        var endDate = $("#endDate").val();

        if(title == null || title == ""){
            alert("제목을 입력해주세요!");
            return false;
        }
        if(endDate == null || endDate == ""){
        	alert("과제 마감일을 선택해주세요!");
        	return false;
        }

        insertForm.submit();
    });
});

$(".summernote").summernote({ 
	
    height: 503,
    lang: "ko-KR",
    placeholder: '내용을 입력해주세요',
    focus: true,
    toolbar: [
        ['fontname', ['fontname']],
        ['fontsize', ['fontsize']],
        ['style', ['bold', 'italic', 'underline', 'strikethrough', 'clear']],
        ['color', ['forecolor', 'color']],
        ['table', ['table']],
        ['para', ['ul', 'ol', 'paragraph']],
        ['height', ['height']],
        ['insert', ['picture', 'link', 'video']],
        ['view', ['codeview', 'fullscreen', 'help']]
    ],
    fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', '맑은 고딕', '궁서', '굴림체', '돋움체', '바탕체'],
    fontSizes: ['8', '9', '10', '11', '12', '13', '14', '15', '20', '30', '40']
});
</script>

<!-- ======= Footer ======= -->
<%@include file="/footer.jsp" %>