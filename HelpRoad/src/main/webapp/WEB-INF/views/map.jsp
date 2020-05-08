<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>위치 설정</title>
<style>
p {
	display: inline;
	border: 1px solid;
}
</style>
<script src="http://code.jquery.com/jquery-1.11.0.js"></script>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<script>
	$(function() {
		// Geolocation API에 액세스할 수 있는지를 확인
		if (navigator.geolocation) {
			//위치 정보를 얻기
			navigator.geolocation.getCurrentPosition(function(pos) {
				lat = $('#latitude').html(pos.coords.latitude); // 위도
				lon = $('#longitude').html(pos.coords.longitude); // 경도
			});
		} else {
			alert("이 브라우저에서는 Geolocation이 지원되지 않습니다.")
		}
	});
</script>
</head>
<body>
	<form action="/HelpRoad/map/path" method="post">
			출발지 : <p> 현재 위치
			<input type="hidden" id="latitude" name="latitude" value=lat>
       		<input type="hidden" id="longitude" name="longitude" value=lon>
			<select id="floor">
				<option value="1f">1층</option>
				<option value="2f">2층</option>
				<option value="3f">3층</option>
				<option value="4f">4층</option>
			</select>
		</p>
		<br /> 도착지 :
		<div>
			<c:forEach var="store" items="${stores}"> 
			${store.floor}F
			${store.name}
			</c:forEach>
		</div>
		<a href="<c:url value="/map/search"/>"> <i class="fas fa-search-location"></i></a>
		<input type="submit" value="길 찾기">
	</form>
</body>
</html>