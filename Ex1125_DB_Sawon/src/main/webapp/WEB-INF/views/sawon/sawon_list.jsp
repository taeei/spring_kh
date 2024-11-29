<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
		<table border="1">
			<caption>부서목록 조회</caption>
			
			<tr>
				<th>사번</th>
				<th>이름</th>
				<th>성별</th>
				<th>부서번호</th>
				<th>직책</th>
				<th>입사일</th>
				<th>상사번호</th>
				<th>급여</th>
			</tr>
			
			<c:forEach var="vo" items="${list}">
			<tr>
				<td>${ vo.sabun }</td>
				<td>${ vo.saname }</td>
				<td>${ vo.sagen }</td>
				<td>${ vo.deptno }</td>
				<td>${ vo.sajob }</td>
				<td>${ fn:split(vo.sahire, ' ')[0] }</td>
				<td>${ vo.samgr }</td>
				<td>${ vo.sapay }</td>
			</tr>
			</c:forEach>
			
			<tr>
				<td colspan="8" align="center">
					<input type="button" value="사원추가"
						   onclick="location.href='insert_form.do'">
				</td>
			</tr>
			
			
		</table>
	</body>
</html>