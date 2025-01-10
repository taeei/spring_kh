<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="/bbs/resources/css/comm_style.css">
		
		<script src="/bbs/resources/js/httpRequest.js"></script>
		
		<script>
			/* 보고있는 댓글의 페이지 번호를 저장할 변수 */
			let comm_page = 1;
			
		
			/* 상세보기 페이지로 오면, 원래 등록되어 있던 댓글들을 보여준다 */
			window.onload = function(){
				comment_list( comm_page );
			}
			
			/* 댓글 등록 메서드 */
			function send( f ) {
				let name = f.name.value;
				let content = f.content.value;
				let pwd = f.pwd.value;
				
				if(name.trim() === ''){
					alert("이름을 입력하세요");
					return;
				}
				if(content.trim() === ''){
					alert("내용을 입력하세요");
					return;
				}
				if(pwd.trim() === ''){
					alert("비밀번호를 입력하세요");
					return;
				}
				
				let url = "comment_insert.do";
				let param = "idx=${vo.idx}&name="+name+
							"&content="+encodeURIComponent(content)+
							"&pwd="+encodeURIComponent(pwd);
				
				sendRequest(url, param, comm_result, "post");
			}
			
			function comm_result() {
				if( xhr.readyState == 4 && xhr.status == 200 ){
					let data = xhr.responseText;
					let json = ( new Function('return '+data) )();
					
					if( json[0].result == 'yes'){
						//댓글목록 요청
						comment_list( comm_page );
					}else{
						alert("등록실패");
					}
				}
			}
			
			/* 댓글 출력 메서드 */
			function comment_list( page ) {

				comm_page = page;
				
				let url = "comment_list.do";
				let param = "idx=${vo.idx}&page="+page;
				sendRequest(url, param, comm_list_fn, "post");
			}
			
			function comm_list_fn() {
				if(xhr.readyState == 4 && xhr.status == 200){
					let data = xhr.responseText;
					let disp = document.getElementById("comment_disp");
					disp.innerHTML = data;
				}
			}
			
			function reply(){
				location.href='reply_form.do?idx=${param.idx}&page=${param.page}&search=${param.search}&search_text=${param.search_text}';
			}
			
			function chk_pwd(){
				let c_pwd = document.getElementById("c_pwd").value;
				if('${vo.pwd}' != c_pwd) {
					alert("비밀번호 불일치");
					return;
				}
				
				location.href='modify_form.do?idx=${param.idx}&page=${param.page}';
			}
			
			function del(){
				
				let c_pwd = document.getElementById("c_pwd").value;
				
				if( '${ vo.pwd }' != c_pwd ){
					alert("비밀번호 불일치");
					return;
				}
				
				if( !confirm('삭제 하시겠습니까?') ){
					return;
				}
				
				let url = "del.do";
				let param = "idx=${vo.idx}";
				sendRequest( url, param, resultFn, "post" );
				
			}
			
			function resultFn(){
				
				if( xhr.readyState == 4 && xhr.status == 200 ){
					
					let data = xhr.responseText;
					let json = ( new Function('return '+data) )();
					
					if( json[0].result === 'succ' ){
						alert("삭제성공");
					}else{
						alert("삭제실패");
					}
					
					location.href="list.do?page=${param.page}&search=${param.search}&search_text=${param.search_text}";					
					
				}
				
			}
			
			/* 댓글 삭제 메서드 */
			function del_comment( f ) {
				let pwd = f.pwd.value; //원본비번
				let pwd2 = f.pwd2.value; //입력비번
				let c_idx = f.c_idx.value;
				
				if(pwd != pwd2){
					alert("비밀번호 불일치");
					return;
				}
				
				if(!confirm("삭제하시겠습니까?")){
					return;
				}
				
				let url = "comment_del.do";
				let param = "c_idx="+c_idx;
				sendRequest(url, param, comm_list_del, "post");
			}
			
			function comm_list_del() {
				if(xhr.readyState == 4 && xhr.status == 200){
					let data = xhr.responseText;
					let json = ( new Function('return '+data) )();
					
					if( json[0].result === 'yes' ){
						comment_list( comm_page );
					}else{
						alert("삭제실패");
					}
				}
			}
		</script>
		
	</head>
	
	<body>
		<table border="1" width="500">
			<tr>
				<th>번호</th>
				<td>${ vo.idx }</td>
				
				<th>작성자</th>
				<td>${ vo.name }</td>
			</tr>
			
			<tr>
				<th>작성일</th>
				<td>${ vo.regdate }</td>
				
				<th>조회수</th>
				<td>${ vo.readhit }</td>
			</tr>
			
			<tr>
				<th>제목</th>
			
				<td colspan="3">
					${ vo.subject }
				</td>
			</tr>
			
			<tr>
				<td colspan="4">
					<pre>${ vo.content }</pre>
				</td>
			</tr>
			
			<tr>
				<th>비밀번호</th>
				<td colspan="3">
					<input type="password" id="c_pwd">
				</td>
			</tr>
			
			<tr>
				<td colspan="4" align="right">
					<!-- 목록으로 돌아가기 -->
					<img src="/bbs/resources/img/btn_list.gif" 
						 onclick="location.href='list.do?page=${param.page}&search=${param.search}&search_text=${param.search_text}'">
					
					
					<c:if test="${ vo.depth eq 0 }">
						<!-- 답변 -->
						<img src="/bbs/resources/img/btn_reply.gif"
						     onclick="reply();">
					</c:if>
					
					<!-- 수정 -->
					<img src="/bbs/resources/img/btn_modify.gif"
						 onclick="chk_pwd();">
					
					<!-- 글삭제 -->
					<img src="/bbs/resources/img/btn_delete.gif"
					     onclick="del();">
				</td>
			</tr>
		</table>
		
		<div id="comment_form">
			<form>
				<table>
					<tr>
						<th>작성자</th>
						<td><input name="name"></td>
					</tr>
					
					<tr>
						<th>내용</th>
						<td>
							<textarea name="content"
								      rows="5" cols="30"></textarea>
						</td>
					</tr>
					
					<tr>
						<th>비밀번호</th>
						<td>
							<input name="pwd" type="password">
							<input type="button" value="댓글등록" 
								   onclick="send(this.form);">
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		<div id="comment_disp">
		</div>
	</body>
</html>
















