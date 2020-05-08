<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색 결과 창</title>
</head>
<body>
	<sf:form action="${pageContext.request.contextPath}/map" method="get">
		<c:forEach var="store" items="${stores}"> 
			<td><c:out value="${store.floor}"/> </td>F
			<td><c:out value="${store.name}"/></td>
			<td><input type="button" class="btn" value="선택"/></td> <!-- 각각 맞는 위치값을 받게 -->
			<br/>
		</c:forEach>
		<input type="submit" value="완료">
	</sf:form>
</body>
</html>