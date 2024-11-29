<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		
		<script>
			function send(f){
				
				if( f.title.value === '' ){
					alert("제목을 입력하세요");
					return;
				}
				
				if( f.photo.value === '' ){
					alert("사진을 선택하세요");
					return;
				}
				
				f.action = "upload.do";
				f.submit();
				
			}
		</script>
	</head>
	
	
	<body>
		<form method="post"
			  enctype="multipart/form-data">
			
			제목 : <input name="title"><br>
			사진 : <input type="file" name="photo"><br>
			<input type="button" value="전송" onclick="send(this.form)">
			
		</form>
	</body>
</html>