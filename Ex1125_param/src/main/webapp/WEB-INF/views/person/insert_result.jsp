<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		이름 : ${ vo.name }<br>
		나이 : ${ vo.age }<br>
		전화 : ${ vo.tel }<br>
		<a href="insert_form.do">돌아가기</a>
	</body>
</html>