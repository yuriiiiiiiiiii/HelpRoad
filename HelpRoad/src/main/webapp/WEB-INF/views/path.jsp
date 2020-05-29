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
	<!-- <script>
        THREEx.ArToolkitContext.baseURL = 'https://raw.githack.com/jeromeetienne/ar.js/master/three.js/'
    </script> -->
    
    <script src="https://code.jquery.com/jquery-1.11.0.js"></script>
	
    <script>
    	//window.onload = function(){
	$(document).ready(function(){
		//var heading;
		navigator.geolocation.getCurrentPosition(function(position){
			document.querySelector('#navi').setAttribute('gps-entity-place', `latitude: ${position.coords.latitude}; longitude: ${position.coords.longitude};`);
        	//$('#latitude').attr('value', position.coords.latitude); // 위도
			//$('#longitude').attr('value', position.coords.longitude); // 경도
			//$('#heading').attr('value', position.coords.heading); // 방향
			//alert("계속 실행 되니");
				//alert(position.coords.heading);
			//heading = position.coords.heading;
			 
		}, function(error){
			alert("error");
		}, { enableHighAccuracy: true, maximumAge: 0 });
		
		/*setInterval(function(){
			 $.ajax({
				url: "path.do",
				type: 'POST',
				data: {"heading": heading},
				cache: false,
				dataType: "text",
				success: function(data){
					//alert("방향 "+ position.coords.heading);
					//alert("성공" + data);

					//document.getElementById("navi").src = "${pageContext.request.contextPath}/resources/images/"+data+".png";
					document.querySelector('#navi').setAttribute('src', `${pageContext.request.contextPath}/resources/images/`+data+`.png`);
					//document.querySelector('a-image').flushToDOM();
					//window.location.reload();
					//var time = new Date().getTime();
					//var src = '${pageContext.request.contextPath}/resources/images/'+data+'.png?time=' + time;
					//$('#navi').attr('src', src);
					document.querySelector('a-scene').flushToDOM(true);
					if(data == "end")
						setTimeout(function(){
							//location.replace("${pageContext.request.contextPath}/map");
							//location.href = "${pageContext.request.contextPath}/map";
						}, 5000);
				},
				error: function(request, status, error){
					alert("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
				}
			});
		}, 2000);*/
		if(window.DeviceOrientationEvent){	// 기능 지원 확인
			var alpha;
	    	window.addEventListener('deviceorientation', function(event){

	    		// ios
	    		if(event.webkitCompassHeading){
	    			alpha = event.webkitCompassHeading;
	    		}
	    		
	    		// android
	    		else{
	    			alpha = event.alpha;
	    			if(!window.chrome){
	    				alpha = alpha - 270;
	    			}
	    		}
	    		//alert(alpha);
	    	
	    	});
	    	setInterval(function(){
	    		//alert(alpha);
				 $.ajax({
					url: "path.do",
					type: 'POST',
					data: {"heading": alpha},
					cache: false,
					dataType: "text",
					success: function(data){
						//alert(alpha);
						document.querySelector('#navi').setAttribute('src', `${pageContext.request.contextPath}/resources/images/`+data+`.png`);
						document.querySelector('a-scene').flushToDOM(true);
						if(data == "end")
							setTimeout(function(){
								//location.href = "${pageContext.request.contextPath}/map";
								//alert("도착");
							}, 5000);
					},
					error: function(request, status, error){
						alert("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
					}
				});
			}, 2000);
	    }
		else{
			alert('이 기기는 방향 지원을 하지 않습니다.');
		}
		
	});
    	//}
        // a-entity 태그를 찾아 gps-entity-place 속성을 현재 위치로 설정 */
        /* $(document).ready(function(){navigator.geolocation.getCurrentPosition(function(position){
            document.querySelector('#navi').setAttribute('gps-entity-place', `latitude: ${position.coords.latitude}; longitude: ${position.coords.longitude};`)
          }); */
   </script>
 
   <!-- <script>
   		window.onload = function(){
   			document
   			.querySelector('button')
   			.addEventListener('click', function(){
   				location.href="${pageContext.request.contextPath}/map";
   			});
   		};
   		 AFRAME.registerComponent('markerhandler', {

   		    init: function() {
   		        const marker = document.querySelector("#quit");

   		        marker.addEventListener('click', function(e){
   		            location.href="${pageContext.request.contextPath}/map";
   		        });
   		}}); 
   </script> -->
</head>

<body style='margin: 0; overflow: hidden;'>
<!-- <form id="pos_form" action="/HelpRoad/path" method="post">
	<input type="hidden" id="latitude" name="latitude">
    <input type="hidden" id="longitude" name="longitude">
    <input type="hidden" id="heading" name="heading">
</form> -->
<!-- <script>
setTimeout(function(){
	this.document.getElementById("pos_form").submit();
}, 2000);
</script> -->
<!--  <div class="div2">
        <button class="stopButton" id="btnStop"> 종료  </button>
</div> -->
	<a-scene vr-mode-ui="enabled: false" embedded
		arjs='sourceType: webcam; sourceWidth:1280; sourceHeight:960; displayWidth: 1280; displayHeight: 960; debugUIEnabled: false;'>
		<!-- <a-assets>
          <!-- <a-asset-item id="scene" src="./assets/magnemite/scene.gltf"></a-asset-item> -->
          <%-- <c:set var="src" value="/resources/images/${heading}.png" />
          <script>
          	$('#navi').attr('src', src);
          </script> --%>
         
          <!--  <img id="navi" src="<c:url value='/resources/images/${heading}.png'/>">
          
        </a-assets> -->
        
        <a-entity gps-camera look-controls rotation-reader>
          <!-- <a-entity gltf-model="#scene" look-at="[gps-camera]" scale="0.1 0.1 0.1"/> -->
          <a-image id="navi" src="<c:url value='/resources/images/${heading}.png'/>" position="0 1 0" look-at="[gps-camera]" scale="0.5 0.5 0.5"></a-image>
          <!-- <a-marker markerhandler emitevents="true" cursor="rayOrigin: mouse" id="quit" type='barcode' value='6'></a-marker> -->
        </a-entity>
	</a-scene>
</body>
</html>