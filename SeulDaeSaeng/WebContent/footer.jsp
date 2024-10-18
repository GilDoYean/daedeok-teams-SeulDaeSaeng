<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
    <footer id="footer" class="footer">
      <div>
        <ul class="credits">
          <li>
            <a href="privacy.jsp">개인정보 처리방침</a>
          </li>
          <!-- 
          <li>
            <a href="siteMap.jsp">사이트맵</a>
          </li>
           -->
          <li>
            <a href="<%=request.getContextPath()%>/contact.do">오류제보</a>
          </li>
        </ul>
        <div class="copyright"> &copy; Copyright <strong>
            <span>슬기로운 대덕 생활</span>
          </strong>. All Rights Reserved </div>
      </div>
    </footer>
    
    <!-- End Footer -->
    <a href="#" class="scroll-top d-flex align-items-center justify-content-center">
      <i class="bi bi-arrow-up-short"></i>
    </a>
    
 

    <div id="preloader">
      <div class="line"></div>
    </div>
    
    <!-- Vendor JS Files -->

<!--     <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script> -->

    <script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.11/index.global.min.js"></script>
    <script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="/vendor/swiper/swiper-bundle.min.js"></script>
    <script src="/vendor/glightbox/js/glightbox.min.js"></script>
    <script src="/vendor/aos/aos.js"></script>
    
    <!-- Template Main JS File -->
    <script src="/js/main.js"></script>
    <% if(request.getRequestURI().contains("timetable")){ %>
         <script src="/js/timetable.js"></script>
    <% }else if(request.getRequestURI().contains("write")){ %>
       <!-- <script src="/js/write.js"></script> -->
    <% }else if(request.getRequestURI().contains("restaurantUpDate")){%>
    	<script src="/js/restaurantUpDate.js"></script>
   	<% }else if(request.getRequestURI().contains("restaurantView")){%>
   		<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDB_rGxKVPYlMG7ViAovZZH84Ezae0R140&callback=initMap&libraries=marker&v=beta" defer></script>	
   	<%} %>
  </body>
</html>