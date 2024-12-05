<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		
		<script>
			function send(f){
				
				f.method = "post";
				f.action = "modify.do";
				f.submit();
				
			}
		</script>
		
	</head>
	
	<body>
		<form>
			<input type="hidden" name="idx" value="${ vo.idx }">
			<input type="hidden" name="page" value="${ param.page }">
			
			<table border="1">
				<caption>::: 글 수정하기 :::</caption>
				
				<tr>
					<th>작성자</th>
					<td><input name="name" value="${ vo.name }"></td>
				</tr>
				
				<tr>
					<th>내용</th>
					<td>
					<textarea name="content" rows="5" cols="40" wrap="on">${ vo.content }</textarea>
					</td>
				</tr>
				
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pwd"></td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="수정하기"
						       onclick="send(this.form);">
						       
						<input type="button" value="목록으로"
						       onclick="history.back();">       
					</td>
				</tr>
			</table>
			
		</form>
	</body>
</html>













