<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	
	
	<body>
		${ ip }님 환영해<br>
		<c:forEach var="f" items="${fruit}">
			${ f }<br>
		</c:forEach>
	</body>
</html>