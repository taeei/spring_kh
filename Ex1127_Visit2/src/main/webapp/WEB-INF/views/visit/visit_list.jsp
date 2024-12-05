<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		
		<link rel="stylesheet"
			  href="/visit/resources/css/visit.css">
		
		<script src="/visit/resources/js/httpRequest.js"></script>
		
		<script>
			function del(f){
				let pwd = f.pwd.value;//원본 비밀번호
				let c_pwd = f.c_pwd.value;//삭제를 위한 비밀번호
	
				if( !confirm("정말 삭제하시겠어요?") ){
					return;
				}
				
				//id와 page를 Ajax로 전달
				let url = "delete.do";
				let param = "idx="+f.idx.value+"&page="+f.page.value+"&pwd="+pwd+"&c_pwd="+c_pwd;
				sendRequest( url, param, resultFn, "post" );
				
			}
			
			function resultFn(){
				if( xhr.readyState == 4 && xhr.status == 200 ){
					
					let data = xhr.responseText;
					let json = ( new Function('return '+data) )();
					
					if( json[0].res === 'no_pwd' ){
						alert("비밀번호 불일치");
						return;
					}
					
					if( json[0].res === '아니오' ){
						alert("삭제실패");
					}else{
						alert("삭제성공");
						location.href="list.do?page=${param.page}";
					}
					
				}
			}
			
			function modify(f){
				let pwd = f.pwd.value;
				let c_pwd = f.c_pwd.value;
				
				let url = "modify_password_chk.do";
				let param = "pwd="+pwd+"&c_pwd="+c_pwd;
				sendRequest(url, param, resPwd, "post");
				
				/* f.action = "modify_form.do";
				f.method = "post";
				f.submit(); */
			}
			
			function resPwd(){
				if( xhr.readyState == 4 && xhr.status == 200 ){
					
					let data = xhr.responseText;
					
					if( data == 'no_pwd' ){
						alert("비밀번호 불일치");
						return;
					}
					
					let f = document.getElementById("myf");
					f.action = "modify_form.do";
					f.method = "post";
					f.submit();
					
				}
			}
			
			
		</script>
	</head>
	
	<body>
		<div id="main_box">
			<h1>::: 방명록 리스트 :::</h1>
			
			<div align="center">
				<input type="button" value="글쓰기"
				       onclick="location.href='insert_form.do'">
			</div>
			
			<c:forEach var="vo" items="${list}">
			
			<div class="visit_box">
				<div class="type_content">
					<pre class="ww">${ vo.content }</pre>
				</div>
				
				<div class="type_name">${ vo.name }(${vo.ip})</div>
				<div class="type_regdate">${vo.regdate}</div>
			
				<form id="myf">
					<input type="hidden" name="idx" value="${vo.idx}">
					<input type="hidden" name="page" value="${param.page}">
					<input type="hidden" name="pwd" value="${vo.pwd }">
				
					비밀번호 : <input type="password" name="c_pwd">
					
					<input type="button" value="수정"
					       onclick="modify(this.form);">
					
					<input type="button" value="삭제"
					       onclick="del(this.form)">
				</form>
				
			</div>
			
			</c:forEach>
			
			<div align="center"
			     style="margin-top:20px; margin-bottom:50px;">
				${ pageMenu }
			</div>
			
		</div>
	</body>
</html>










