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
		<table border="1">
			<caption>:::부서목록:::</caption>
		
			<tr>
				<th>부서번호</th>
				<th>부서이름</th>
				<th>부서위치</th>
			</tr>
			
			<c:forEach var="vo" items="${list}">
				<tr>
					<td>${ vo.deptno }</td>
					<td>
						<a href="#"
						 onclick="location.href='sawon_list.do?deptno=${vo.deptno}'">
						 ${ vo.dname }</a>
					</td>
					<td>${ vo.loc }</td>
				</tr>
			</c:forEach>
			
			<tr>
				<td colspan="3" align="center">
					<input type="button" value="사원추가"
						   onclick="location.href='insert_sawon_form.do'">
				</td>
			</tr>
		</table>
	</body>
</html>