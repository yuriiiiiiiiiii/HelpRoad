<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매장 이름 검색</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css">
<style>
* {
   font-size: 40px;
   font-family: 'Gamja Flower', cursive;
}
body {
   background-color: #ffffff;
}
</style>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
</head>
<body>
   <sf:form action="${pageContext.request.contextPath}/map/list"
      method="get">
      <input type="text" name="name"
         style="text-align: center; width: 250px; height: 40px;">
      <button type="submit" class="button-icon">
         <i class="fas fa-search"></i>
      </button>
   </sf:form>
</body>
</html>