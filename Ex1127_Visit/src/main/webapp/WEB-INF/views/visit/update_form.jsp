<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		
		<script>
			//수정
			function modify(f){
				
				f.method="post";
				f.action = "update.do?page=${param.page}";
				f.submit();
			}
			
			// x버튼 (이미지 삭제)
			function delImg(f){
				//원래 사용하던 파일명 변경
				f.filename.value = "no_file";
				
				let img = document.getElementById("m_img");
				img.style.display = "none";
			}
		</script>
	</head>
	<body>
		<form enctype="multipart/form-data">
			<input type="hidden"  name="idx" value="${vo.idx}">
			<input type="hidden" name="page" value="${ param.page }">
			<input type="hidden" name="filename" value="${ vo.filename }">
			
			<table border="1">
			
				<caption>::: 방명록 수정 :::</caption>
				
				<tr>
					<th>이름</th>
					<td><input name="name" value="${vo.name}"></td>
				</tr>
				
				<tr>
					<th>내용</th>
					<td>
						<textarea rows="5" cols="40"
								  style="resize:none"
								  wrap="on"
								  name="content">${vo.content}</textarea>
					</td>
				</tr>
				
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pwd"></td>
				</tr>
				
				<tr>
					<th>첨부파일</th>
					<td>
						<input type="file" name="photo">
						
						<c:if test="${ vo.filename ne 'no_file' }">
						<img src="/visit/resources/upload/${ vo.filename }"
							 width="30" id="m_img">
						<input type="button" value="x"
							   onclick="delImg(this.form);">
						</c:if>
					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="수정"
							   onclick="modify(this.form);">
						<input type="button" value="취소"
							   onclick="history.back(this.form);">
					</td>
				</tr>
				
			</table>
		
		</form>
	</body>
</html>