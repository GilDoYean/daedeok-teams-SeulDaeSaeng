<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>

<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>

<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>		

<%

%>
<main data-aos="fade" data-aos-delay="700">
  <div class="page-header sub d-flex align-items-center">
        <div class="container position-relative">
            <div class="row d-flex justify-content-center">
                <p class="mb-5 text-start">
                    <a href="<%=request.getContextPath() %>member/myPageHome.do">
                        <i class="bi bi-chevron-left"></i> 뒤로가기
                    </a>
                </p>
                <div>
                    <h2>스토리 업로드</h2>
                    <form action="<%=request.getContextPath()%>/story.do" method="post" enctype="multipart/form-data" role="form" id="insertForm" class="php-email-form needs-validation" novalidate>
                        <div class="contact">
                         <div class="container pb-3">
                        <div class="form-group">
                            <input type="text" class="form-control" name="title" id="title" placeholder="제목" required>
                            <div class="invalid-feedback">제목을 작성해주세요.</div>
                        </div>
		                <div class="form-group mt-2 d-flex gap-2 align-items-center">
		                  <input class="form-control form-control-lg " type="file" name="file" id="file" required>
		                </div>
                         <div class="text-center mt-5 mb-5">
                            <button type="button" id="submitBtn" name="submitBtn" style="background-color:orange;">작성하기</button>
                         </div>
                        </div>
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

    submitBtn.on("click", function(){
        var title = $("#title").val();
        var file = $("#file").val();

        if(title == null || title == ""){
            alert("제목을 입력해주세요");
            return false;
        }
        if(file == null || file == ""){
            alert("파일을 업로드해주세요");
            return false;
        }
        insertForm.submit();
    });
});
</script>

<%@include file="/footer.jsp" %>
