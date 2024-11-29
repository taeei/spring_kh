<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		
		<script>
			function send(f){
				let saname = f.saname.value;
				
				f.action="insert.do";
				f.submit();
			}
		</script>
	</head>
	
	<body>
		<form>
			<table border="1">
				<caption>사원등록</caption>
				<tr>
					<th>이름</th>
					<td><input name="saname"></td>
				</tr>  
				
				<tr>
					<th>성별</th>
					<td><input name="sagen"></td>
				</tr>
				
				<tr>
					<th>부서번호</th>
					<td><input name="deptno"></td>
				</tr>
				
				<tr>
					<th>직책</th>
					<td><input name="sajob"></td>
				</tr>
				
				<tr>
					<th>상사번호</th>
					<td><input name="samgr"></td>
				</tr>
				
				<tr>
					<th>연봉</th>
					<td><input name="sapay"></td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="등록"
							   onclick="send(this.form);">
							   
						<input type="button" value="취소">
					</td>
				</tr>
			</table>		
		</form>
	</body>
</html>