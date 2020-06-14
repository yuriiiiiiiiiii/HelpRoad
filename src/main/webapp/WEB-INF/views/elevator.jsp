<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>엘리베이터 이동중</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css">
<style>
* {
   font-size: 40px;
   font-family: 'Gamja Flower', cursive;
}
</style>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<script src="https://code.jquery.com/jquery-1.11.0.js"></script>
<script>
  $(function() {
	   if(window.DeviceOrientationEvent){	// 자이로센서 기능 지원 확인
	    	window.addEventListener('deviceorientation', function(event){
	    		var alpha;
	    		// ios
	    		if(event.webkitCompassHeading){
	    			alpha = event.webkitCompassHeading;
	    		}
	    		
	    		// android
	    		else{
	    			alpha = event.alpha - 180;
	    			if(alpha < 0){
	    				alpha = 360 + alpha;
	    			}
	    		}
	    		$('#heading').attr('value', alpha);
	    	});
	    	
	    }
		else{
			alert('이 기기는 방향 지원을 하지 않습니다.');
		}
  });
</script>
</head>

<body>
   <div class="div3">
      <img src="${pageContext.request.contextPath}/resources/images/elve.png" width="500" height="800"><br/><br/>
      <span style="color:#F58E91"><i class="far fa-hand-point-down"></i></span>
      엘리베이터에서 내리면 아래 버튼을 눌러주세요  
      <span style="color:#F58E91"><i class="far fa-hand-point-down"></i></span><br/>
      <form action="/HelpRoad/path" method="post">
		<input type="hidden" id="startFloor" name="startFloor" value="${startFloor}">
		<input type="hidden" id="name" name="name" value="${name}">
		<input type="hidden" id="startpx" name="startpx" value="${startpx}">
		<input type="hidden" id="startpy" name="startpy" value="${startpy}">
		<input type="hidden" id="heading" name="heading">
		<button type="submit" class="elevator-button"> 도착 </button>
      </form>
   </div>
</body>
</html>