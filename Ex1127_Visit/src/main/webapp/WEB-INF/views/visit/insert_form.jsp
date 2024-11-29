<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		
		<script>
			function send(f){
				
				if( f.name.value === '' ){
					alert("이름은 필수");
					return;
				}
				
				if( f.content.value === '' ){
					alert("내용은 필수");
					return;
				}
				
				if( f.pwd.value=== '' ){
					alert("비번은 필수");
					return;
				}
				
				if( f.photo.value === '' ){
					alert("파일첨부는 필수");
					return;
				}
				
				f.method="post";
				f.action = "insert.do";
				f.submit();
			}
		</script>
	</head>
	
	<body>
		<form enctype="multipart/form-data">
		
			<table border="1">
				<caption>::: 방명록 작성 :::</caption>
				
				<tr>
					<th>이름</th>
					<td><input name="name"></td>
				</tr>
				
				<tr> 
					<th>내용</th>
					<td>
						<textarea rows="5" cols="50"
								  style="resize:none;"
								  wrap="on"
								  name="content"></textarea>
					</td>
				</tr>
				
				<tr>
					<th>파일</th>
					<td><input type="file" name="photo"></td>
				</tr>
				
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pwd"></td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="추가"
							   onclick="send(this.form);">
						<input type="button" value="취소"
							   onclick="history.back()">
					</td>
				</tr>
				
			</table>
		
		</form>
	</body>
</html>