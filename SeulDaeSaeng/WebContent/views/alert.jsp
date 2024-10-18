<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과 메세지 출력</title>
</head>
<body>
<script type="text/javascript">
var msg = '${msg}';
var returnUrl = '${url}';
alert(msg);
document.location.href = returnUrl;
</script>

</body>
</html>