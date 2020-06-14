<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv='X-UA-Compatible' content='IE=edge'>
	<title>경로 안내</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css">
	<script src='https://aframe.io/releases/0.9.2/aframe.min.js'></script>
	<script
		src="https://raw.githack.com/jeromeetienne/AR.js/master/aframe/build/aframe-ar.min.js"></script>
	<script
		src="https://raw.githack.com/donmccurdy/aframe-extras/master/dist/aframe-extras.loaders.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.11.0.js"></script>
	
    <script>
    window.onload = function(){
    	
		if(window.DeviceOrientationEvent){	// 자이로센서 기능 지원 확인
			var alpha;
	    	window.addEventListener('deviceorientation', function(event){

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
	    	
	    	});
	    	
	    	setInterval(function(){
	    		
				 $.ajax({
					url: "path.do",
					type: 'POST',
					header: {"Cache-Control": "no-cache, no-store, must-revalidate", "Pragma": "no-cache", "Expires": "0"},	//cache 사용x
					data: {"heading": alpha},
					cache: false,	//post에선 사용x
					dataType: "text",
					success: function(data){
						document.querySelector('a-entity').flushToDOM(true);
						document.querySelector('#navi').setAttribute('src', `${pageContext.request.contextPath}/resources/images/`+data+`.png`);
						document.querySelector('a-entity').flushToDOM(true);
						if(data == "end") {
							setTimeout(function(){	// 8초 후 초기화면으로 화면 전환
					    		location.href="/HelpRoad/";
							}, 8000);
						}
						else if(data == "elve"){
							setTimeout(function(){	// 2초 후 elevator로 화면 전환
								this.document.getElementById("pos_form").submit();
							}, 2000);
						}
							
					},
					error: function(request, status){
						alert("code = "+ request.status + "\n 잠시 후 다시 시도해주세요."); // 실패 시 처리
					}
				});
			}, 1500);
	    }
		else{
			alert('이 기기는 방향 지원을 하지 않습니다.');
		}
		
    }
   </script>
</head>

<body style='margin: 0; overflow: hidden;'>

	<form id="pos_form" action="/HelpRoad/path/elevator" method="post">
		<input type="hidden" id="startFloor" name="startFloor" value="${startFloor}">
		<input type="hidden" id="name" name="name" value="${name}">
		<input type="hidden" id="startpx" name="startpx" value="${startpx}">
		<input type="hidden" id="startpy" name="startpy" value="${startpy}">
	</form>
	
	<a-scene vr-mode-ui="enabled: false" embedded
		arjs='sourceType: webcam; sourceWidth:1280; sourceHeight:960; displayWidth: 1280; displayHeight: 960; debugUIEnabled: false;'>	<!-- AR -->
        
        <a-entity gps-camera look-controls rotation-reader>	<!-- 카메라 -->
          <a-image id="navi" src="<c:url value='/resources/images/${heading}.png'/>" position="0 1.25 0" look-at="[gps-camera]" scale="0.4 0.4 0.4"></a-image>	<!-- AR 객체 화살표 -->
        </a-entity>
        
	</a-scene>
</body>
</html>