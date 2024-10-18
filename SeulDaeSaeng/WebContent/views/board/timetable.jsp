<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@include file="/header.jsp" %>
    <main data-aos="fade" data-aos-delay="700">
    <!-- ======= End Page Header ======= -->
      <div class="page-header pb-4">
        <div class="container text-center">
          <h3>304호 시간표</h3>
        </div>
      </div>
      <!-- End Page Header -->
      <!-- ======= timetable Section ======= -->
      <section id="timetable" class="gallery-single pb-5">
        <div class="container-fluid">
          <div id="calendar"></div>
        </div>
      </section>
      <!-- End timetable Section -->
      <section id="memo" class="container pb-5 d-flex justify-content-center">
        <div class="col-lg-8 col-12">
            <nav class="taskNav">
              <a class="taskNav_item taskAll active" href="#">전체 스케줄</a>
              <a class="taskNav_item taskprogress" href="#">진행중인 스케줄</a>
              <a class="taskNav_item taskcompleted" href="#">완료된 스케줄</a>
            </nav>
            <!--/.add new task -->
            <div class="btn_addList">
                <div class=" text"> + 스케줄 만들기
                  <input class="addList_input control" type="text">
                </div>
                <div class="addList">
                    <div class="deadline">
                        <div class="addList_title">
                          <span class="addList_icon"><i class="bi bi-calendar-date"></i></span>
                          마감 일시 
                        </div>
                        <div class="addList_control">
                          <input class="control control_input" type="date" name="deadline_date" placeholder="yyyy/mm/dd">
                          <input class="control  control_input" type="time" name="deadline_time" placeholder="hh/mm">
                        </div>
                    </div>
                    <div class="addComment">
                        <div class="addList_title"><span class="addList_icon"><i class="bi bi-chat-dots"></i></span>메모 </div>
                        <div class="addList_control"><textarea name="" class="control  control_textarea" id="" rows="5" placeholder="메모를 입력하세요."></textarea></div>
                    </div>
                    <div class="upload">
                        <div class="addList_title"><span class="addList_icon"><i class="bi bi-star-fill"></i></span>중요표시 </div>
                        <div class="select_priority">
                          <input value="yes" type="radio" name="priority" id="yes" /><label for="yes">네</label>
                          <input value="no" type="radio" name="priority" id="no" /><label for="no">아니요</label>
                        </div>
                    </div>
                    <div class="add-items">
                      <button class="btn btn_cancel"><i class="bi bi-x"></i>취소</button>
                      <button class="btn btn_add"><i class="bi bi-plus"></i>만들기</button>
                    </div>
                </div>
            </div>
            <!--/.add new task end-->
            <!--/.taskList -->
            <ul class="taskList"></ul>
            <!--/.taskList end -->
            <div class="totaltask"><span class="totaltask_num">1</span>개 스케줄 </div>
        </div>
      </section>
      </main>
<%@include file="/footer.jsp" %>