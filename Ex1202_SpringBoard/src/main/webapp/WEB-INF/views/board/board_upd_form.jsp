<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		
		<script src="/dbs/resources/js/httpRequest.js"></script>

		<script>
			function modify(f){
				let idx = f.idx.value;
				let subject = f.subject.value;
				let name = f.name.value;
				let content = f.content.value;
				
				//유효성 체크
				if( subject === '' ){
					alert("제목을 입력하세요.");
					return;
				}
				if( name === '' ){
					alert("작성자를 입력하세요.");
					return;
				}
				if( content === '' ){
					alert("내용을 입력하세요.");
					return;
				}
				
				let url = "modify.do";
				let param = "idx="+idx+
							"&subject="+encodeURIComponent(subject)+
							"&name="+encodeURIComponent(name)+
							"&content="+encodeURIComponent(content);
				
				sendRequest( url, param, resultFn, "get");
			}
			
			//콜백함수
			function resultFn(){
				if( xhr.readyState == 4 && xhr.status == 200 ){
					let data = xhr.responseText;
					let json = ( new Function('return '+data) )();
					
					if( json[0].param == "succ" ){
						alert("수정 성공");
						location.href="list.do?page=${param.page}";
					}else{
						alert("수정 실패");
					}
				}
			}
		</script>
	</head>
	
	
	<body>
		<form>
			<input type="hidden" name="idx" value="${ vo.idx }">
			<input type="hidden" name="page" value="${ param.page }">

			<table width="700">
				<caption>글 수정하기</caption>
				
				
				<tr>
					<td>제목</td>
					<td><input name="subject" value="${vo.subject}"}></td>
				</tr>
				
				<tr>
					<td>작성자</td>
					<td><input name="name" value="${vo.name}"></td>
				</tr>
				
				<tr>
					<td>내용</td>
					<td>
						<textarea rows="10" cols="80"
								  style="resize:none;"
								  name="content">${ vo.content }</textarea>
					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="수정"
							   onclick="modify(this.form);">
						<input type="button" value="취소"
							   onclick="history.back();">
					</td>
				</tr>
			</table>
			
			
		</form>
	</body>
</html>