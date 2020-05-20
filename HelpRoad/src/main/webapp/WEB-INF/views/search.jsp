<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색 창</title>
<style>
div {
	display: inline-block;
	text-align: center;
	padding: 5px;
	margin: 10px;
}
input[type="text"]{
	text-align: center;
}
</style>
</head>
<body>
	
	<sf:form action="${pageContext.request.contextPath}/map/search/list" method="get"> 
		검색 <div>
		<input type="text" name="name">
		</div>
		<input type="submit" value="Press">
		
	</sf:form>
	<!--  <input type="file" accept="image/*" capture="camera"> -->
	
</body>
</html>