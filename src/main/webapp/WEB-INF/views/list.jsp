<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매장 고르기</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css">
<style>
* {
   font-size: 30px;
   font-family: 'Gamja Flower', cursive;
}
</style>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script> 
<script src="https://code.jquery.com/jquery-1.11.0.js"></script>
<script>
   $(function() {
	   var heading;
	   
      // Geolocation API에 액세스할 수 있는지를 확인
      if (navigator.geolocation) {
         //위치 정보를 얻기
         navigator.geolocation.getCurrentPosition(function(pos) {
            $('#latitude').attr('value', pos.coords.latitude); // 위도
            $('#longitude').attr('value', pos.coords.longitude); // 경도
            heading = pos.coords.heading;
            $('#heading').attr('value', heading); // 방향
         }, function(error){
               alert("현재 위치 접근에 실패했습니다 :(");
            }, { enableHighAccuracy: true, maximumAge: 3000}); 
      } else {
         alert("이 브라우저에서는 Geolocation이 지원되지 않습니다.")
      }
	  if(heading == null && window.DeviceOrientationEvent){	// geolocation으로 방향을 얻어오지 못할 때  자이로센서 기능 지원 확인
	    	window.addEventListener('deviceorientation', function(event){

	    		// ios
	    		if(event.webkitCompassHeading){
	    			heading = event.webkitCompassHeading;
	    		}
	    		
	    		// android
	    		else{
	    			heading = event.alpha - 180;
	    			if(heading < 0){
	    				heading = 360 + heading;
	    			}
	    		}
	    		$('#heading').attr('value', heading);
	    	});
	    	
	    }
		else{
			alert('이 기기는 방향 지원을 하지 않습니다.');
		}
   });
</script>
<script>
$(document).ready(function(){
	$('.button-icon').click(function(x){	// 버튼 클릭하면 선택 층과 이름 저장하고 폼 제출
		var tr = $(this).closest('tr');
		var td = tr.children();
		var floor = td.eq(0).text();
		floor = floor.replace("F","");
		var name = td.eq(1).text();
		
		
		$('#endFloor').attr('value', floor);
		$('#name').attr('value', name);

		document.getElementById("pos_form").submit();
	});
});
</script>
</head>

<body>
   <div class="div2">
   <form id="pos_form" action="${pageContext.request.contextPath}/path" method="post">
       <div class="div-floor"> 현재 층
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
      <br/>
      <input type="hidden" name="endFloor" id="endFloor">
      <input type="hidden" name="name" id="name">
      <c:forEach var="store" items="${stores}" varStatus="status">
      <table>
		<tr>
			<td>${store.floor}F</td>
			<td>${store.name}</td>
			<td>
				<button type="button" class="button-icon" > <i class="far fa-hand-point-left"></i> </button>
			</td>
		</tr> 
       </table>
      </c:forEach>
   </form>
   </div>
</body>
</html>