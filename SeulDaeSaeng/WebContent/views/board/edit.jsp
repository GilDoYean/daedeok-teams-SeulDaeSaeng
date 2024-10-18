<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>

    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>

    <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
    <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>		
<!-- 	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script> -->
<%
 	int levelChk = (int)request.getAttribute("levelChk");
	int idx = (int)request.getAttribute("idx");
	int boardNo = (int)request.getAttribute("boardNo");
	BoardVO bv = (BoardVO)request.getAttribute("boardDetail");
	int classBoardChk = 0;
	if(request.getAttribute("classBoardChk")!=null){
	   classBoardChk = (int)request.getAttribute("classBoardChk");
	}
	
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

    if(boardName==null){
    	boardName = String.valueOf(levelChk) + "호";
    }
    
    if(classBoardChk==1){
  		board = "eachClassNotice";
    }else if(classBoardChk==2){
  		board = "eachClassBoard";
    }
 %>
<main data-aos="fade" data-aos-delay="700" >
<!-- <main> -->
  <!-- ======= End Page Header ======= -->
  <div class="page-header sub d-flex align-items-center">
    <div class="container position-relative">
      <div class="row d-flex justify-content-center">
        <p class="mb-5 text-start">
        <% if(levelChk > 300){ %>
          <a href="<%=request.getContextPath()%>/board/detail.do?boardNo=<%=bv.getBoardNo() %>&idx=<%=idx %>&levelChk=<%=levelChk %>&classBoardChk=<%=classBoardChk %>">
		<%
        	}else{
		%>			        
          <a href="<%=request.getContextPath()%>/board/detail.do?boardNo=<%=bv.getBoardNo() %>&idx=<%=idx %>&levelChk=<%=levelChk %>">
        <%
			}
        %>
            <i class="bi bi-chevron-left"></i> 뒤로가기
          </a>
        </p>
        <div>
          <h2>
          <%=boardName %>
          </h2>
        </div>
      </div>
    </div>
  </div><!-- End Page Header -->
  <div class="contact">
    <div class="container pb-3">
      <!-- {{changeDetected}} -->
      <form action="<%=request.getContextPath()%>/board/edit.do" method="post" role="form" id="insertForm" class="php-email-form needs-validation" novalidate>
        <div class="form-group">
          <input type="text" class="form-control" name="title" id="title" placeholder="제목" value="<%=bv.getBoardTitle() %>" required>
          <div class="invalid-feedback">제목을 작성해주세요.</div>
        </div>
        	<textarea class="form-control summernote" rows="5" id="content" name="content"><%=bv.getBoardCon() %></textarea>
        	<input type="hidden" id="levelChk" name="levelChk" value=<%=levelChk %>>
        	<%
        		if(classBoardChk != 0){
        	%>
        	<input type="hidden" id="classBoardChk" name="classBoardChk" value=<%=classBoardChk %>>
			<%
        		}
			%>        	
        	<input type="hidden" id="idx" name="idx" value=<%=idx %>>
          	<input type="hidden" id="boardNo" name="boardNo" value=<%=boardNo %>>
        <div class="text-center mt-5 mb-5">
          <button type="submit" id="submitBtn">수정하기</button>
        </div>
      </form>
   
    </div>
  </div>

</main>
     
<script type="text/javascript">

$(function(){
	
	var submitBtn = $("#submitBtn");
	var insertForm = $("#insertForm");
	
	// 등록 버튼 클릭 시 이벤트
	submitBtn.on("click", function(){

		var title = $("#title").val();
		var content = $("#content").val();
		var boardNo = $("#boardNo").val();
		var levelChk = $("#levelChk").val();
		var idx = $("#idx").val();

		if(title == null || title == ""){
            alert("제목을 입력해주세요!");
            return false;
        }
        if(content == null || content == "" || content == "<p><br></p>" || content == "<br>"){
            alert("내용을 입력해주세요!");
            return false;
        }

        if(confirm("수정하시겠습니까?")){
        	insertForm.submit();
		} else{
			return false;
		}
	});
});

$(".summernote").summernote({
	
    //에디터의 높이
    height:503,
    //에디터 한글 설정
    lang:"ko-KR",
    placeholder: '내용을 입력해주세요',
    //에디터의 커서 이동
    focus:true,
    toolbar:[
       //글꼴지정
       ['fontname',['fontname']],

       //글자 크기 설정
       ['fontsize',['fontsize']],
       
       //굵기, 기움임꼴, 밑줄, 취소선 서식 지우기
       ['style',['bold','italic','underline','strikethough','clear']],
       
       //글자색
       ['color',['forecolor','color']],
       //표만들기
       ['table',['table']],
       //글머리 기호,번호매기기,문단정렬
       ['pare',['ul','ol','paragraph']],
       //줄간격
       ['height',['height']],
       //그림첨부,링크만들기,동영상첨부
       ['insert',['picture','link','video']],
       //코드 보기,확대해서 보기,도움말
       ['view',['codeview','fullscreen','help']]
       
       ],
       //추가한 글꼴
       fontName:['Arial','Arial Black','Comic Sans MS','맑은 고딕','궁서','굴림체','돋음체','바탕체'],
       //추가한 폰트사이즈
       fontSizes:['8','9','10','11','12','13','14','15','20','30','40']
 });


</script>
     
<!-- ======= Footer ======= -->
<%@include file="/footer.jsp" %>