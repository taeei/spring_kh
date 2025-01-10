<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		
		<script>
			function send_check(){
				let f = document.f;
				
				if( f.subject.value === '' ){
					alert("제목을 입력하세요");
					return;
				}
				
				if( f.name.value === '' ){
					alert("작성자를 입력하세요");
					return;
				}
				
				if( f.content.value === '' ){
					alert("내용을 입력하세요");
					return;
				}
				
				if( f.pwd.value === '' ){
					alert("비밀번호를 입력하세요");
					return;
				}
				
				f.method="post";
				f.action = "insert.do";
				f.submit();
				
			}
		</script>
		
	</head>
	
	<body>
		<form name="f">
		
			<table width="700">
				<caption>새 글 작성하기</caption>
			
				<tr>
					<td><img src="/bbs/resources/img/td_bg_01.gif"></td>
					
					<th width="120">제목</th>
					
					<td><img src="/bbs/resources/img/td_bg_01.gif"></td>
					
					<td>
						<input name="subject">
					</td>
					
					<td><img src="/bbs/resources/img/td_bg_01.gif"></td>
				</tr>
				
				<tr>
					<td><img src="/bbs/resources/img/td_bg_01.gif"></td>
					
					<th width="120">작성자</th>
					
					<td><img src="/bbs/resources/img/td_bg_01.gif"></td>
					
					<td>
						<input name="name">
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
						     	  name="content"></textarea>
					</td>
					
					<td><img src="/bbs/resources/img/td_bg_01.gif"></td>
				</tr>
				
				<tr>
					<td><img src="/bbs/resources/img/td_bg_01.gif"></td>
					
					<th width="120">비밀번호</th>
					
					<td><img src="/bbs/resources/img/td_bg_01.gif"></td>
					
					<td>
						<input name="pwd"
						       type="password">
					</td>
					
					<td><img src="/bbs/resources/img/td_bg_01.gif"></td>
				</tr>
				
				<tr>
					<td colspan="5" align="right">
						<img src="/bbs/resources/img/btn_reg.gif"
						     onclick="send_check();">
						
						<img src="/bbs/resources/img/btn_back.gif"
						     onclick="history.back()">
					</td>
				</tr>
			</table>
		
		</form>
	</body>
</html>












