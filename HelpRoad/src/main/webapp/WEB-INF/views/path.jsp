<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv='X-UA-Compatible' content='IE=edge'>
	<title>GeoAR.js demo</title>
	<script src='https://aframe.io/releases/0.9.2/aframe.min.js'></script>
	<script
		src="https://raw.githack.com/jeromeetienne/AR.js/master/aframe/build/aframe-ar.min.js"></script>
	<script
		src="https://raw.githack.com/donmccurdy/aframe-extras/master/dist/aframe-extras.loaders.min.js"></script>
	<script>
        THREEx.ArToolkitContext.baseURL = 'https://raw.githack.com/jeromeetienne/ar.js/master/three.js/'
    </script>
	<script>
        window.onload = function(){navigator.geolocation.getCurrentPosition(function(position){
          document.querySelector('a-entity').setAttribute('gps-entity-place', `latitude: ${position.coords.latitude}; longitude: ${position.coords.longitude};`)
        	});
        } // a-entity 태그를 찾아 gps-entity-place 속성을 현재 위치로 설정
   </script>
</head>

<body style='margin: 0; overflow: hidden;'>
	<%
		double stLat = Double.parseDouble(request.getParameter("latitude"));
		double stLon = Double.parseDouble(request.getParameter("longitude"));
	%>
	<a-scene vr-mode-ui="enabled: false" embedded
		arjs='sourceType: webcam; sourceWidth:1280; sourceHeight:960; displayWidth: 1280; displayHeight: 960; debugUIEnabled: false;'>
		<a-camera gps-camera rotation-reader>
			<a-entity
				gltf-model="./assets/magnemite/scene.gltf" look-at="[gps-camera]"
				scale="0.15 0.15 0.15" />
			
		</a-camera>
	</a-scene>
</body>
</html>