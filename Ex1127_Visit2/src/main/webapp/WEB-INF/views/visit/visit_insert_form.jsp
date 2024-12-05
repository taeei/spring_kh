<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		
		<script>
			function send(f){
				let name = f.name.value;
				let content = f.content.value;
				let pwd = f.pwd.value;
				
				if( name === '' ){
					alert('이름은 필수');
					return;
				}
				
				if( content === '' ){
					alert('내용은 필수');
					return;
				}
				
				if( pwd === '' ){
					alert('비밀번호 필수');
					return;
				}
				
				f.method = "post";
				f.action = 'insert.do';
				f.submit();
				
			}
		</script>
	</head>
		
	<body>
		<form>
			<table border="1">
				<caption>::: 새 글 작성 :::</caption>
				
				<tr>
					<th>작성자</th>
					<td><input name="name"></td>
				</tr>
				
				<tr>
					<th>내용</th>
					<td>
						<textarea rows="5" cols="40"
							      style="resize:none;"
							      wrap="on"
							      name="content"></textarea>
					</td>
				</tr>
				
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pwd"></td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="글쓰기"
						       onclick="send(this.form);">
						<input type="button" value="돌아가기"
						       onclick="history.back();">       
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>













