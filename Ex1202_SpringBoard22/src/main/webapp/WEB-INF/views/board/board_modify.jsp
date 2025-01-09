<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		
		<script>
		function modify_check(){
			let f2 = document.f2;
			
			if( f2.subject.value === '' ){
				alert("제목을 입력하세요");
				return;
			}
			
			if( f2.name.value === '' ){
				alert("작성자를 입력하세요");
				return;
			}
			
			if( f2.content.value === '' ){
				alert("내용을 입력하세요");
				return;
			}
			
			f2.method="post";
			f2.action = "modify.do";
			f2.submit();
			
		}
		</script>
	</head>
	<body>
		<form name="f2">
			<input type="hidden" name="idx" value="${ vo.idx }">
			<input type="hidden" name="page" value="${ param.page }">
		
			<table width="700">
				<caption>게시글 수정하기</caption>
			
				<tr>
					<td><img src="/bbs/resources/img/td_bg_01.gif"></td>
					
					<th width="120">제목</th>
					
					<td><img src="/bbs/resources/img/td_bg_01.gif"></td>
					
					<td>
						<input name="subject" value="${ vo.subject }">
					</td>
					
					<td><img src="/bbs/resources/img/td_bg_01.gif"></td>
				</tr>
				
				<tr>
					<td><img src="/bbs/resources/img/td_bg_01.gif"></td>
					
					<th width="120">작성자</th>
					
					<td><img src="/bbs/resources/img/td_bg_01.gif"></td>
					
					<td>
						<input name="name" value="${ vo.name }">
					</td>
					
					<td><img src="/bbs/resources/img/td_bg_01.gif"></td>
				</tr>
				
				<tr>
					<td><img src="/bbs/resources/img/td_bg_01.gif"></td>
					
					<th width="120">내용</th>
					
					<td><img src="/bbs/resources/img/td_bg_01.gif"></td>
					
					<td>
						<textarea rows="10" cols="80" 
						     	  style="resize:none;"
						     	  name="content">${ vo.content }</textarea>
					</td>
					
					<td><img src="/bbs/resources/img/td_bg_01.gif"></td>
				</tr>
				
				<tr>
					<td colspan="5" align="right">
						<img src="/bbs/resources/img/btn_modify.gif"
						     onclick="modify_check();">
						
						<img src="/bbs/resources/img/btn_back.gif"
						     onclick="history.back()">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>