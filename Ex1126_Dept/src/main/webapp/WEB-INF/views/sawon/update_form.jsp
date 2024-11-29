<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		
		<script>
		
			window.onload = function(){
				
				let arr = [10, 20, 30, 40, 50];
				let selector = document.getElementById("selector");
				
				for( let i = 0; i < arr.length; i++ ){
					if( '${vo.deptno}' == arr[i] ){
						selector[i+1].selected = true;
					}
				}
			}
		
			function send(f){
				
				let deptno = f.deptno.value;
				let samgr = f.samgr.value;
				let sapay = f.sapay.value;
				
				let no_pattern = /^[0-9]{1,3}$/;
				if( !no_pattern.test( deptno ) ){
					alert("부서번호는 정수로 입력하세요");
					return;
				}
				
				if( !no_pattern.test( samgr ) ){
					alert("상사번호는 정수로 입력하세요");
					return;
				}
				
				let pay_pattern = /^[0-9]{4,8}$/;
				if( !pay_pattern.test( sapay ) ){
					alert("연봉은 4 ~ 8 자리의 정수");
					return;
				} 
				
				f.action="update.do";
				f.submit();
			}
		</script>	
	</head>
	
	<body>
		<form>
		
			<input type="hidden" name="sabun" value="${vo.sabun }">
			
			<table border="1">
				<caption>:::사원 수정:::</caption>
		
				<tr>
					<td colspan="2">
						<select name="deptno" id="selector">
							<option value="">부서를 선택하세요.</option>
							<option value="10">총무부</option>
							<option value="20">영업부</option>
							<option value="30">전산실</option>
							<option value="40">관리부</option>
							<option value="50">경리부</option>
						</select>
					</td> 
				</tr>
		
				<tr>
					<th>이름</th>
					<td><input name="saname" value="${vo.saname}"></td>
				</tr>
		
				<tr>
					<th>직책</th>
					<td><input name="sajob" value="${vo.sajob}"></td>
				</tr>
		
				<tr>
					<th>상사번호</th>
					<td><input name="samgr" value="${vo.samgr}"></td>
				</tr>
		
				<tr>
					<th>급여</th>
					<td><input name="sapay" value="${vo.sapay}"></td>
				</tr>
		
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="수정"
								onclick="send(this.form);">
						<input type="button" value="취소"
								onclick="history.back()">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>