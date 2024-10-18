<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="/header.jsp" %>

    <main data-aos="fade" data-aos-delay="700">
      <!-- ======= End Page Header ======= -->
      <div class="page-header d-flex align-items-center">
        <div class="container position-relative">
          <div class="row d-flex justify-content-center">
            <div>
              <p>공감을 가장 많이 받은 맛집 리스트 TOP5(미식인)의 리스트를 공개합니다!</p>
              <h2>미식인의 추천맛집</h2>
              <p>
                <a class="cta-btn" href="foodLikeList.html">TOP5 맛집 리스트</a>
                <a class="cta-btn gray" href="#">새로운 맛집 리스트</a>
              </p>
            </div>
          </div>
        </div>
      </div>
      <!-- End Page Header -->
      <section class="foodLikeList mb-5">
        <div class="container" data-aos="fade-up">
          <!-- <div class="row mb-3 d-flex justify-content-between align-items-center"><p class="col-3">총 <b>25</b> 개</p><div class="input-group input-group-sm col-4"><button class="btn dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">전체</button><ul class="dropdown-menu"><li><a class="dropdown-item" href="#title">제목</a></li><li><a class="dropdown-item" href="#content">내용</a></li><li><a class="dropdown-item" href="#nickname">닉네임</a></li></ul><input type="text" class="form-control" aria-label="Text input with dropdown button"></div></div> -->
          <div class="food-list-board d-flex flex-column gap-4">
            <div class="food-list">
              <h6 class="mb-3 d-flex justify-content-between align-items-center">
                <span>
                  <i class="bi bi-person-circle"></i>
                  <!-- <img src="/img/testimonials/testimonials-2.jpg" class="testimonial-img" alt=""> --> 고객 아이디 </span>
                <button type="button" class="btn btn-secondary btn-sm">
                  <span>00님 맛집 </span>리스트 더보기 </button>
              </h6>
              <ul class="d-flex justify-content-between">
                <li class="card col">
                  <a href="http://">
                    <p class="card-like">
                      <i class="bi bi-heart-fill"></i>
                      <span>25</span>
                    </p>
                    <div class="img-fluid">
                      <img src="/img/no-image.jpg" class="card-img-top" alt="맛집이미지" onError="this.onerror=null; this.src='https://i.imgur.com/BFfnYMT.jpeg';" >
                    </div>
                    <div class="card-body">
                      <small class="badge bg-body-secondary mb-1">한식</small>
                      <div class="d-flex justify-content-between align-items-center">
                        <h5 class="card-title text-truncate">식당이름입니다~</h5>
                        <span>
                          <i class="bi bi-star-fill"></i> 4.5 </span>
                      </div>
                      <p class="card-text ">식당주소를 작성하고 있습니다. 집에 보내주세요.</p>
                    </div>
                  </a>
                </li>
                <li class="card col dis-none-550">
                  <a href="http://">
                    <p class="card-like">
                      <i class="bi bi-heart-fill"></i>
                      <span>25</span>
                    </p>
                    <div class="img-fluid">
                      <img src="/img/gallery/gallery-1.jpg" class="card-img-top" alt="맛집이미지" onError="this.onerror=null; this.src='https://i.imgur.com/BFfnYMT.jpeg';" >
                    </div>
                    <div class="card-body">
                      <small class="badge bg-body-secondary mb-1">한식</small>
                      <div class="d-flex justify-content-between align-items-center">
                        <h5 class="card-title text-truncate">식당이름입니다~</h5>
                        <span>
                          <i class="bi bi-star-fill"></i> 4.5 </span>
                      </div>
                      <p class="card-text ">식당주소를 작성하고 있습니다. 집에 보내주세요.</p>
                    </div>
                  </a>
                </li>
                <li class="card col dis-none-750">
                  <a href="http://">
                    <p class="card-like">
                      <i class="bi bi-heart-fill"></i>
                      <span>25</span>
                    </p>
                    <div class="img-fluid">
                      <img src="/img/gallery/gallery-1.jpg" class="card-img-top" alt="맛집이미지" onError="this.onerror=null; this.src='https://i.imgur.com/BFfnYMT.jpeg';" >
                    </div>
                    <div class="card-body">
                      <small class="badge bg-body-secondary mb-1">한식</small>
                      <div class="d-flex justify-content-between align-items-center">
                        <h5 class="card-title text-truncate">식당이름입니다~</h5>
                        <span>
                          <i class="bi bi-star-fill"></i> 4.5 </span>
                      </div>
                      <p class="card-text ">식당주소를 작성하고 있습니다. 집에 보내주세요.</p>
                    </div>
                  </a>
                </li>
                <li class="card col dis-none-990">
                  <a href="http://">
                    <p class="card-like">
                      <i class="bi bi-heart-fill"></i>
                      <span>25</span>
                    </p>
                    <div class="img-fluid">
                      <img src="/img/gallery/gallery-1.jpg" class="card-img-top" alt="맛집이미지" onError="this.onerror=null; this.src='https://i.imgur.com/BFfnYMT.jpeg';" >
                    </div>
                    <div class="card-body">
                      <small class="badge bg-body-secondary mb-1">한식</small>
                      <div class="d-flex justify-content-between align-items-center">
                        <h5 class="card-title text-truncate">식당이름입니다~</h5>
                        <span>
                          <i class="bi bi-star-fill"></i> 4.5 </span>
                      </div>
                      <p class="card-text ">식당주소를 작성하고 있습니다. 집에 보내주세요.</p>
                    </div>
                  </a>
                </li>
              </ul>
            </div>
            
          </div>
          <!-- <nav class="d-flex justify-content-center align-items-center pt-5 pm-5" aria-label="Page navigation example"><ul class="pagination "><li class="page-item disabled"><a class="page-link" href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li><li class="page-item active"><a class="page-link" href="#">1</a></li><li class="page-item"><a class="page-link" href="#">2</a></li><li class="page-item"><a class="page-link" href="#">3</a></li><li class="page-item"><a class="page-link" href="#">4</a></li><li class="page-item"><a class="page-link" href="#">5</a></li><li class="page-item"><a class="page-link" href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li></ul></nav> -->
          <div class="container d-flex align-items-center justify-content-center py-5 gap-2">
            <a href="#" type="button" class="btn btn-outline-warning">내 맛집 공유하기</a>
          </div>
        </div>
      </section>
    </main>

<%@include file="/footer.jsp" %>