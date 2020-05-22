<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>위치 설정</title>
<style>
p{
	display: lnline-block;
	font-size: 20px;
}
div {
	display: inline-block;
	border: 1px solid;
	text-align: center;
	padding: 8px;
	margin: 10px;
}
input[type="text"]{
	text-align: center;
}
.btn{
	float: right;
	font-size: 20px;
}
</style>
<script src="https://code.jquery.com/jquery-1.11.0.js"></script>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<script>
	$(function() {
		// Geolocation API에 액세스할 수 있는지를 확인
		if (navigator.geolocation) {
			//위치 정보를 얻기
			navigator.geolocation.getCurrentPosition(function(pos) {
				alert(pos.coords.latitude + ' ' + pos.coords.longitude + ' ' + pos.coords.heading);
				$('#latitude').attr('value', pos.coords.latitude); // 위도
				$('#longitude').attr('value', pos.coords.longitude); // 경도
				$('#heading').attr('value', pos.coords.heading); // 방향
			}, function(error){
	        	 alert("error");
	         }, { enableHighAccuracy: true, maximumAge: 5000}); 
		} else {
			alert("이 브라우저에서는 Geolocation이 지원되지 않습니다.")
		}
	});
</script>
</head>
<body>
	<form action="/HelpRoad/path" method="post">
			출발지 <div> 현재 위치
			<input type="hidden" id="latitude" name="latitude">
       		<input type="hidden" id="longitude" name="longitude">
       		<input type="hidden" id="heading" name="heading">
			<select name="startFloor">
				<option value="1">1층</option>
				<option value="2">2층</option>
				<option value="3">3층</option>
				<option value="4">4층</option>
				<option value="5">5층</option>
				<option value="6">6층</option>
				<option value="7">7층</option>
			</select>
		</div>
		<br /> 도착지 
		<div>
			<input type="text" name="floor" value="${store.floor}" style="width:20px">F
			<input type="text" name="name" value="${store.name}">
		</div>
		<a href="<c:url value="/map/search"/>"> <i class="fas fa-search"></i></a>
		<br/>
		<input type="submit" value="길 찾기" class="btn">
	</form>
</body>
</html>