<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색 결과 창</title>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
</head>
<body>
	<%-- <sf:form action="${pageContext.request.contextPath}/map" method="get"> --%>
		<c:forEach var="store" items="${stores}"> 
			<td>${store.floor} </td>F
			<td>${store.name}</td>
			<td>
				<a href="<c:url value="/map/${store.floor}/${store.name}"/>"><i class="far fa-hand-point-left"></i></a>
			</td> <!-- 각각 맞는 위치값을 받게 -->
			<br/>
		</c:forEach>
	<%-- </sf:form> --%>
</body>
</html>