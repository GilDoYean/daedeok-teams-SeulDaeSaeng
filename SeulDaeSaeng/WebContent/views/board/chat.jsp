<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>슬기로운 대덕 생활 - GallerySingle</title>
    <meta content="개발 공부만 하기 바쁜 대덕인재개발원 사람들을 위해 점심머먹지? 오늘 어떤걸배우지? 바로 볼수 있습니다!" name="description">
    <meta content="대덕인재개발원, 대전광역시, 개발자, 커뮤니티, 점심추천, 시간표" name="keywords">
    <!-- Favicons -->
    <link href="/favicon.ico" rel="icon">
    <!-- Fonts -->
    <link rel="stylesheet" href="/css/font.css">
    
    <script defer src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous" ></script>
    
    <!-- Vendor CSS Files -->
<!--     footer34line에서 복사 -->
    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<!--     footer34line에서 복사 -->
    <link href="/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">
    <link href="/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
    <link href="/vendor/aos/aos.css" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="/css/main.css" rel="stylesheet">
<title>채팅창</title>

</head>
<%
	MemberVO memDetail = (MemberVO)session.getAttribute("memDetail");
	String chatId = memDetail.getMemNick();
	String classId = memDetail.getMemClass();
%>
<body>
<div class="page-header pb-4" style="padding-top: 50px; min-height: 0vh;">
	<div class="container text-center">
    	<h3><%=classId %>호 채팅</h3>
    </div>
</div>
<section id="chat" class="gallery-single pb-5">
        <div class="container">
          <div class="chat">
            <div class="card" style="height: 65vh;">
              <div id="chatWindow" class="card-body msg_card_body">
<!--               	메세지 출력 -->
              
              </div>
              <div class="card-footer">
              	<div class="input-group">
              		<input type="text" id="chatMessage" onkeyup="enterKey();" class="form-control type_msg" placeholder="메세지를 입력하세요..."
              				style="border-radius: 15px 0 0 15px;">
              		<div class="input-group-append">
                    	<button id="sendBtn" onclick="sendMessage();" class="input-group-text send_btn">
                      		<i class="bi bi-send"></i>
                    	</button>
                  	</div>
                </div>
              </div>
            </div>
          </div>
        </div>
</section>
<div class="d-flex justify-content-center">
	<button id="closeBtn" style="border:none; display: flex; justify-content: center; /* 버튼 내부 텍스트 가로 중앙 정렬 */"class="cta-btn" onclick="disconnect();" >채팅 종료</button>
</div>
    
<script>
var classId = "<%=classId%>";
var chatId = "<%=chatId%>";
var webSocket = new WebSocket("ws://192.168.34.118:8888/ChatingServer/" + classId + "/" + chatId);
	var chatWindow, chatMessage, chatId;
	
	// 채팅창이 열리면 대화창, 메시지 입력창, 아이디 표시란으로 사용할 DOM 객체 저장
	// 윈도우가 로드되면 실행할 익명 함수
	window.onload = function() {
		chatWindow = document.getElementById("chatWindow"); // 
		chatMessage = document.getElementById("chatMessage"); // 
		chatId = '<%=chatId %>';
	};
	
	// 시간을 가져와서 AM 또는 PM으로 포맷팅하는 함수
	function getCurrentTime() {
	    var currentTime = new Date();
	    var hours = currentTime.getHours();
	    var minutes = currentTime.getMinutes();
	    var ampm = hours >= 12 ? 'PM' : 'AM';
	    hours = hours % 12;
	    hours = hours ? hours : 12; // 0 시간은 12로 표시
	    minutes = minutes < 10 ? '0' + minutes : minutes;

	    // 현재 요일 가져오기
	    var days = ['일', '월', '화', '수', '목', '금', '토'];
	    var dayOfWeek = days[currentTime.getDay()];

	    var formattedTime = hours + ':' + minutes + ' ' + ampm + ', ' + dayOfWeek + '요일';
	    return formattedTime;
	}

	// 메시지 전송
	function sendMessage() {
		var messageTime = getCurrentTime();
		var messageContent = chatMessage.value.trim(); // 입력된 메시지의 앞뒤 공백을 제거하여 내용 확인
		
		// 입력된 메시지가 비어 있지 않은 경우에만 메시지를 보냄
	    if (messageContent !== "") {
			// 대화창에 표시 
			chatWindow.innerHTML += "<div class='d-flex justify-content-end mb-4 masBox'>"
										+"<div class='msg_cotainer_send'>" + chatMessage.value
										+ "<div class='msg_time'>" + messageTime + "</div>"
										+"</div></div>"; // test
	
			webSocket.send(chatId + '|' + chatMessage.value); // 서버로 전송 
			chatMessage.value = ""; //메시지 입력창 내용 지우기 
			chatWindow.scrollTop = chatWindow.scrollHeight;
	    }
	}

	function disconnect() { // 함수명 수정
		webSocket.close();
	}

	// 엔터 키 입력 처리
	function enterKey() {
		if (window.event.keyCode == 13) { // 13 = Enter 키의 코드값
			sendMessage();
		}
	}

	// 웹소켓 서버에 연결되었을 때 실행
	webSocket.onopen = function(event) {
		chatWindow.innerHTML += "<div style='text-align: center;'>채팅방에 연결되었습니다.</div>";
	};

	// 웹소켓이 닫혔을 때 실행
	webSocket.onclose = function(event) {
		chatWindow.innerHTML += "<div style='text-align: center;'>채팅을 종료합니다.</div>";
		window.close(); // 창 닫기
	}

	webSocket.onerror = function(event) {
		alert(event.data);
		chatWindow.innerHTML += "<div style='text-align: center;'>채팅 중 에러가 발생하였습니다.</div>"; 
	}

	// 메시지를 받았을 때 실행
	webSocket.onmessage = function(event) {
		var message = event.data.split("|"); // 대화명과 메시지 분리
		var sender = message[0];
		var content = message[1];
		var messageTime = getCurrentTime();
		if (sender === "SYSTEM") {
	        chatWindow.innerHTML += "<div style='text-align: center;'>" + content + "</div>";
	    }else{
		if (content != "") {
			if (content.match("/")) { // 귓속말
				if (content.match(("/" + chatId))) { // 나에게 보낸 메시지만 출력
					var temp = content.replace(("/" + chatId), "[귓속말] ");
					chatWindow.innerHTML += "<div class='msg_sender 'style='margin-left: 50px; font-size: 0.9em;'>"+sender+"</div>" + "<div class='d-flex justify-content-start mb-4 masBox'>"
					+"<div class='img_cont_msg'>"
      				+"<img src='https://static.turbosquid.com/Preview/001292/481/WV/_D.jpg' class='rounded-circle user_img_msg'>"
    				+"</div><div class='msg_cotainer'>"
											 + "" + temp + "<div class='msg_time_send'>" + messageTime + "</div>"
											+ "</div></div>"; 
				}
			} else { // 일반 대화
				chatWindow.innerHTML += "<div style='margin-left: 50px; font-size: 0.9em;'>"+sender+"</div>"+"<div class='d-flex justify-content-start mb-4 masBox'>"
								+"<div class='img_cont_msg'>"
                  				+"<img src='https://static.turbosquid.com/Preview/001292/481/WV/_D.jpg' class='rounded-circle user_img_msg'>"
                				+"</div><div class='msg_cotainer'>"
										+ content + "<div class='msg_time_send'>" + messageTime + "</div>"
										+ "</div></div>"; 
			}
		}
	    }
		chatWindow.scrollTop = chatWindow.scrollHeight;
	};
</script>
</body>