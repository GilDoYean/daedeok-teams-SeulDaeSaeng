<%@page import="kr.or.ddit.restaurant.vo.RestaurantVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>
<%
	String restBizno = request.getParameter("no");
	String fileString = request.getSession().getServletContext().getRealPath("/");	
%>

<main data-aos="fade" data-aos-delay="700">
    <div class="page-header sub d-flex align-items-center">
        <div class="container position-relative">
            <div class="row d-flex justify-content-center">
                <p class="mb-5 text-start">
                	<a href="<%=request.getContextPath() %>/restaurant/view.do?no=<%=restBizno %>">
                        <i class="bi bi-chevron-left"></i> 뒤로가기
                    </a>
                </p>
                <div>
                    <h2>리뷰작성</h2>
                    <form action="<%=request.getContextPath()%>/restaurant/reviewWrite.do?no=<%=restBizno %>" method="post" enctype="multipart/form-data" role="form" id="insertForm" class=" needs-validation" novalidate>
                        <div class="contact container px-0">
                       		<div class="d-flex align-items-center mb-3 starBox gap-3 is-valid">
 
								<input class="form-check-input d-none" type="radio" name="reviewStar" id="star5" value="10">
								<label class="form-check-label fs-4" for="star5">
									<i class="bi bi-star"></i>
									<i class="bi bi-star-fill"></i>
								</label>
								
								<input class="form-check-input d-none" type="radio" name="reviewStar" id="star4" value="8">
								<label class="form-check-label fs-4" for="star4">
									<i class="bi bi-star"></i>
									<i class="bi bi-star-fill"></i>
								</label>
								
								<input class="form-check-input d-none" type="radio" name="reviewStar" id="star3" value="6">
								<label class="form-check-label fs-4" for="star3">
									<i class="bi bi-star"></i>
									<i class="bi bi-star-fill"></i>
								</label>
								
								<input class="form-check-input d-none" type="radio" name="reviewStar" id="star2" value="4">
								<label class="form-check-label fs-4" for="star2">
									<i class="bi bi-star"></i>
									<i class="bi bi-star-fill"></i>
								</label>
								
								<input class="form-check-input d-none" type="radio" name="reviewStar" id="star1" value="2">
								<label class="form-check-label fs-4" for="star1">
									<i class="bi bi-star"></i>
									<i class="bi bi-star-fill"></i>
								</label>
								
								<input class="form-check-input d-none" type="radio" name="reviewStar" id="star0" value="" checked="checked">

								<p class="mb-0 pt-3">
									점수
									<span class="score score5">5점</span>
									<span class="score score4">4점</span>
									<span class="score score3">3점</span>
									<span class="score score2">2점</span>
									<span class="score score1">1점</span>
									주기								 
								</p>
	                       	</div>
	                        
	                        <div class="form-group">
	                            <textarea class="form-control" style="min-height: 200px;" name="review" id="review" placeholder="리뷰 내용을 작성해 주세요.(필수입니다)" required></textarea>
	                        </div>
			                <div class="form-group mt-2 d-flex gap-2 align-items-center">
			                  <input class="form-control form-control-lg " type="file" name="file" id="file" required/>
			                </div>
			                
				          	<input type="hidden" id="restBizno" name="restBizno" value=<%=restBizno %>>
				          	<input type="hidden" id="fileString" name="fileString" value=<%=fileString %>>
	                         <div class="text-center mt-5 mb-5">
	                            <button type="button" id="submitBtn" name="submitBtn" >작성하기</button>
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
    var insertForm = $("#insertForm");
    
     $(".form-check-label").on("click",function(){
		$(".starBox").removeClass("is-invalid");
		$(".starBox").addClass("is-valid");
     });

    submitBtn.on("click", function(){
    	var reviewStar = $(".form-check-input[type=radio]:checked").val();
    	
        var review = $("#review").val();
        var file = $("#file").val();   
        
        var errors = false;
        
        if(reviewStar == null || reviewStar == 0 || reviewStar == ""){
        	$(".starBox").addClass("is-invalid");
        	$(".starBox").removeClass("is-valid");
	        errors = true;
        } 
        /*
        if(file == null || file == ""){
            $("#file").addClass("is-invalid");
            $("#file").removeClass("is-valid");
        }else {
        	$("#file").addClass("is-valid");
        	$("#file").removeClass("is-invalid");
		}
		*/
        if(review == null || review == ""){
            $("#review").addClass("is-invalid");
            $("#review").removeClass("is-valid");
	        errors = true;
        }else {
        	$("#review").addClass("is-valid");
        	$("#review").removeClass("is-invalid");
		}
		
		if (!errors) {
        	insertForm.submit();
	    } else {
	    	alert("입력값을 확인해주세요!");
	    }
    });
});
</script>

<%@include file="/footer.jsp" %>
