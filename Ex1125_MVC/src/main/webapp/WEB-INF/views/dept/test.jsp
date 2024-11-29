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
		test.do가 호출되었을 때 보여지는 jsp
		<br>${ ip }님 환영
		
		<ul>
			<c:forEach var="v" items="${ msg }">
				<li>${ v }</li>		
			</c:forEach>
		</ul>
	</body>
</html>