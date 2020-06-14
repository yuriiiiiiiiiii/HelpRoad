<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>404 오류</title>
<link rel="stylesheet" type="text/css"
   href="${pageContext.request.contextPath}/resources/css/main.css">
<style>
* {
   position: absolute;
   top: 50%;
   left: 50%;
   margin-left: -405px;
   margin-top: -605px;
   font-family: 'Noto Sans KR', sans-serif;
   zoom : 1.2;
}
</style>
</head>
<body>
	
    <h3>404 오류</h3>
	<p style="color: #ff0000">해당 페이지를 찾을 수 없습니다 :(</p>

	<footer>
		<p><a href="${pageContext.request.contextPath}">Home</a></p>
	</footer>
</body>
</html>