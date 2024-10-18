<%@page import="java.util.ArrayList"%>
<%@page import="com.itextpdf.text.log.SysoCounter"%>
<%@page import="java.time.Duration"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.LocalTime"%>
<%@page import="kr.or.ddit.board.service.BoardServiceImpl"%>
<%@page import="kr.or.ddit.board.service.IBoardService"%>
<%@page import="kr.or.ddit.board.vo.StoryVO"%>
<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.member.vo.AddressVO"%>
<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@page import="kr.or.ddit.member.vo.UsersVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="/header.jsp" %>

<%
    IBoardService service = BoardServiceImpl.getInstance();
    MemberVO memDetail = (MemberVO) session.getAttribute("memDetail");

	UsersVO usersVo = (UsersVO)request.getAttribute("usersVo");
	MemberVO memberVo = (MemberVO)request.getAttribute("memberVo");
	AddressVO addrVo = (AddressVO)request.getAttribute("addrVo");
	List<StoryVO> storyVo = service.getStoryList(memDetail.getUsersNo());
	List<BoardVO> memBoardList = request.getAttribute("memBoardList") == null 
								? new ArrayList() : (List<BoardVO>)request.getAttribute("memBoardList");
	List<BoardVO> memberBoardReplyList = request.getAttribute("memberBoardReplyList") == null
								? new ArrayList() : (List<BoardVO>)request.getAttribute("memberBoardReplyList");
%>

 <main class="myPage" data-aos="fade" data-aos-delay="700">
      <section id="myPage" class="gallery-single ">
        <div class="container-xl">
          <div class="row justify-content-between gy-4">
            <div class="col-lg-3">
              <div class="portfolio-info sticky-top">
                <h3 class="d-flex align-items-center">
                  <img src="img/testimonials/testimonials-2.jpg" class="testimonial-img me-2" alt="" onError="this.onerror=null; this.src='https://i.imgur.com/BFfnYMT.jpeg';" >
                  <p>
                    <span><%if(memberVo.getMemNick() != null){ %>
                    <%=memberVo.getMemNick() %>(<%=memberVo.getMemName() %>)
                    <%
                    	}else{
                    %>
                    	없음
					<%
                    	}
					%>                    		
                    </span>
                    <small><%=usersVo.getUsersId() %></small>
                  </p>
                </h3>
                <ul>
                  <li>
                    <strong>주소 </strong>
                    <span>
                    <%if(addrVo.getAddrBasic() != null){ %>
                    	<%=addrVo.getAddrBasic() %>
                    <%
                   		 }else{
                    %>
					없음
					<% 
                   		 }
					%>
                    </span>
                  </li>
                  <li>
                    <strong>전화번호</strong>
                    <span>
                    <%if(memberVo.getMemTel() != null){ %>
                    	<%=memberVo.getMemTel() %>
                    <%
                   		 }else{
                    %>
					없음
					<% 
                   		 }
					%>
                    </span>
                  </li>
                  <li>
                    <strong>이메일</strong>
                    <span>
                    <%if(memberVo.getMemEmail() != null){ %>
                    	<%=memberVo.getMemEmail() %>
                    <%
                   		 }else{
                    %>
					없음
					<% 
                   		 }
					%>
                    </span>
                  </li>
                  <li>
                    <strong>생일</strong>
                    <span>
                    <%if(memberVo.getMemBirth() != null){ %>
                    	<%=memberVo.getMemBirth() %>
                    <%
                   		 }else{
                    %>
					없음
					<% 
                   		 }
					%>
                    </span>
                  </li>
                  <li>
                    <strong>반</strong>
                    <span>
                    <%if(memberVo.getMemClass() != null){ %>
                    	<%=memberVo.getMemClass() %>
                    <%
                   		 }else{
                    %>
					없음
					<% 
                   		 }
					%>
                    </span>
                  </li>
                  <li>
                    <a href="<%=request.getContextPath() %>/member/pwCheck.do" class="btn-visit align-self-start">정보수정 / 탈퇴</a>
                  </li>
                </ul>
              </div>
            </div>
            <div class="col-lg-9">
              <div class="portfolio-description">
                <div class="section-header">
                  <h2>story</h2>
                  <p class="d-flex justify-content-between align-items-center"> 
                    나의 오늘의 스토리
                    <button type="button" class="btn btn-outline-warning btn-sm" onclick="location.href='<%=request.getContextPath() %>/story.do'">
                                       올리기
                    </button>
                  </p>
                </div>
                <div class="list-group mb-5">
                 <%
                 if (storyVo == null || storyVo.isEmpty()) {
                 %>
                       <p>   작성된 스토리가 없습니다</p>
                 <%
                 }else{
                	 for(StoryVO story : storyVo){
                		 
                		 String time = service.getStoryTime(story.getStoryNo());

                         LocalDateTime uploadTime = LocalDateTime.parse(time,  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                         System.out.println(uploadTime);

                         LocalDateTime now = LocalDateTime.now();

                         LocalDateTime expiryTime = uploadTime.plusHours(24);
                         System.out.println(expiryTime);
                                 
                         Duration duration = Duration.between(now, expiryTime);
                                 
                         String timeRemaining;
                         if (duration.isNegative() || duration.isZero()) {
                               timeRemaining = "만료됨";
                               int status = service.updateStory(story.getStoryNo());
                              
                         } else {
                               long hours = duration.toHours();
                               long minutes = duration.toMinutes() % 60;
                                     
                                if (hours > 0) {
                                    timeRemaining = hours + "시간 " + minutes + "분";
                                } else if (minutes > 0) {
                                    timeRemaining = minutes + "분";
                                } else {
                                    timeRemaining = "곧";
                                }
                                timeRemaining += "후에 지워짐";
                         }
                %>
                  <a href="<%=request.getContextPath() %>/story/detail.do?storyNo=<%=story.getStoryNo() %>" class="list-group-item py-2 d-flex justify-content-between align-items-center" >
                    <span><i class="bi bi-clock-history px-2"></i> <%=story.getStoryCon() %> </span>
                    <small><%=timeRemaining  %></small>
                  </a>
                <%
                  }
                 }
                %>
                </div>
              </div>
              <div class="services mb-5">
                <div class="row gy-4">
                  <div class="col-md-4 col-12 d-flex">
                    <div class="service-item position-relative w-100">
                      <i class="bi bi-card-list"></i>
                      <h4>
                        <a href="/member/myReview.do" class="stretched-link">내가 쓴 리뷰</a>
                      </h4>
                      <p>식당에 내가 남긴 리뷰를 확인하고 삭제할 수 있습니다. </p>
                    </div>
                  </div>
                  <!-- End Service Item -->
<!--                   <div class="col-md-4 col-12 d-flex">
                    <div class="service-item position-relative w-100">
                      <i class="bi bi-heart-fill"></i>
                      <h4>
                        <a href="" class="stretched-link">내가 찜한 식당</a>
                      </h4>
                      <p>내가 찜한 식당 리스트를 관리하고 추천한 맛집리스트를 올리거나 삭제할 수 있습니다. </p>
                    </div>
                  </div> -->
                  <!-- End Service Item -->
<!--                   <div class="col-md-4 col-12 d-flex">
                    <div class="service-item position-relative w-100">
                      <i class="bi bi-calendar-date"></i>
                      <h4>
                        <a href="" class="stretched-link">시간표 스케줄러</a>
                      </h4>
                      <p> 학급시간표를 확인하고 내 스케줄을 관리해 보세요. </p>
                    </div>
                  </div> -->
                  <!-- End Service Item -->
                </div>
              </div>
              <div class="portfolio-description d-flex justify-content-between gap-5 mb-5">
                <div class="col">
                  <div class="section-header">
                    <h2>board</h2>
                    <p class="d-flex justify-content-between align-items-center"> 
                     	 내가 작성한 게시글 
                      <button type="button" class="btn btn-outline-warning btn-sm" onclick="location.href='/member/memberMyBoardList.do'">더보기</button>
                    </p>
                  </div>
                  <div class="list-group">
                  <%	
                  		for(int i=0; i<4; i++){
                  			if(memBoardList.size() == 0){
                  	%>
                  	<p>작성한 글이 없습니다.</p>
                  <%			
                  				break;
                  			}
                  			BoardVO boardVo = memBoardList.get(i);
                  	%>			
                    <a href="#" class="list-group-item d-flex w-100 justify-content-between align-items-center py-3">
                      <h6 class="mb-2 text-truncate">
                        <small class="badge bg-light"><%=boardVo.getBoardLevelKr() %></small>
                        <%
                        	if(boardVo.getFileNo() > 0){
                        %>
                        <small class="attach">
                          <i class="bi bi-paperclip"></i>
                        </small>
                        <%
                        	}else{
                        %>
						<small class="attach">
                          <i></i>
                        </small>
						<%
                        	}
	                        String Title = boardVo.getBoardTitle();
	                        if(Title.length() > 10) Title = Title.substring(0, 11) + "...";
						%>                        		
                        <%=Title %>
                      </h6>
                      <small class="days"><%=boardVo.getBoardAt() %></small>
                    </a>
                  <%
                  			if(memBoardList.size() == (i+1)) break;
                  		}
                  %>
                  </div>
                </div>
                 <div class="col">
                  <div class="section-header">
                    <h2>board</h2>
                    <p class="d-flex justify-content-between align-items-center"> 
                     	 내가 댓글 쓴 게시글 
                      <button type="button" class="btn btn-outline-warning btn-sm" onclick="location.href='/member/myComment.do'">더보기</button>
                    </p>
                  </div>
                  <div class="list-group">
                  <%	
                  		for(int i=0; i<4; i++){
                  			if(memberBoardReplyList.size() == 0){
                  	%>
                  	<p>작성한 글이 없습니다.</p>
                  <%			
                  				break;
                  			}
                  			BoardVO boardReplyVo = memberBoardReplyList.get(i);
                  	%>			
                    <a href="#" class="list-group-item d-flex w-100 justify-content-between align-items-center py-3">
                      <h6 class="mb-2 text-truncate">
                        <small class="badge bg-light"><%=boardReplyVo.getBoardLevelKr() %></small>
                        <%
                        	if(boardReplyVo.getFileNo() > 0){
                        %>
                        <small class="attach">
                          <i class="bi bi-paperclip"></i>
                        </small>
                        <%
                        	}else{
                        %>
						<small class="attach">
                          <i></i>
                        </small>
						<%
                        	}
	                        String Title = boardReplyVo.getBoardTitle();
	                        if(Title.length() > 10) Title = Title.substring(0, 11) + "...";
						%>                        		
                        <%=Title %>
                      </h6>
                      <small class="days"><%=boardReplyVo.getBoardAt() %></small>
                    </a>
                  <%
                  			if(memberBoardReplyList.size() == (i+1)) break;
                  		}
                  %>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </main>
    <!-- End #main -->

<%@include file="/footer.jsp" %>