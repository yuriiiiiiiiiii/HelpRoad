<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>500 오류</title>
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
	
    <h3>500 오류</h3>
	<p style="color: #ff0000">서버 처리에서 문제가 발생했습니다 :(</p>

	<footer>
		<p><a href="${pageContext.request.contextPath}">Home</a></p>
	</footer>
</body>
</html>