<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>길찾기 오류</title>
<link rel="stylesheet" type="text/css"
   href="${pageContext.request.contextPath}/resources/css/main.css">
<style>
* {
   text-align: center;
   font-family: 'Noto Sans KR', sans-serif;
   letter-spacing: 3px;
   margin-bottom: 120px;
   zoom : 1.2;
}
</style>
</head>
<body>
	
    <h3>길찾기 오류</h3>
	<p style="color: #FF0000">현재 위치는 신세계백화점 본점이 아닙니다.<br>신세계백화점 본점 내에서만 이용 가능합니다.</p>

	<footer>
		<p><a href="${pageContext.request.contextPath}">Home</a></p>
	</footer>
</body>
</html>