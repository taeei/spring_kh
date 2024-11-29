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
			<caption>:::사원 목록 조회:::</caption>
			
			<tr>
				<th colspan="10" align="center">
					<c:if test="${list[0].deptno eq 10}">
						총무부
					</c:if>
					
					<c:if test="${list[0].deptno eq 20}">
						영업부
					</c:if>
					
					<c:if test="${list[0].deptno eq 30}">
						전산실
					</c:if>
					
					<c:if test="${list[0].deptno eq 40}">
						관리부
					</c:if>
					
					<c:if test="${list[0].deptno eq 50}">
						경리부
					</c:if>
				</th>				
			</tr>
			
			<tr>
				<th>사번</th>
				<th>이름</th>
				<th>성별</th>
				<th>부서번호</th>
				<th>직책</th>
				<th>입사일</th>
				<th>상사번호</th>
				<th>연봉</th>
				<th>비고</th>
			</tr>
			
			<c:forEach var="vo" items="${list}">
				<tr>
					<td>${ vo.sabun }</td>
					<td>
						<a href="#"
						   onclick="location.href='gogek_list.do?sabun=${vo.sabun}'">${ vo.saname }</a>
					</td>
					<td>${ vo.sagen }</td>
					<td>${ vo.deptno }</td>
					<td>${ vo.sajob }</td>
					<td>${ fn:split(vo.sahire, ' ')[0] }</td>
					<td>${ vo.samgr }</td>
					<td>${ vo.sapay }</td>
				
					<td>
						<input type="button" value="삭제"
							   onclick="location.href='sawon_del.do?sabun=${vo.sabun}&deptno=${vo.deptno}'">
						<input type="button" value="수정"
							   onclick="location.href='sawon_upd_form.do?sabun=${vo.sabun}'">
					</td>
				</tr>
			</c:forEach>
			
			<tr>
				<td colspan="10" align="center">
					<input type="button" value="부서 목록으로"
						   onclick="location.href='dept_list.do'">
				</td>
			</tr>
		</table>
	</body>
</html>