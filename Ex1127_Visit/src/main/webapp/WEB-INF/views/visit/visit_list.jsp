<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		
		<link rel="stylesheet" href="/visit/resources/css/visit.css">
		
		<script src="/visit/resources/js/httpRequest.js"></script>
		
		<script>
			//삭제
			function del(f){
				
				let idx = f.idx.value;				
				let pwd = f.pwd.value;
				let c_pwd = f.c_pwd.value;
				
				if( !confirm("정말 삭제?") ){
					return;
				}
				
				let url = "visit_del.do";
				let param = "idx="+idx+
							"&page=${param.page}&pwd="+pwd+
							"&c_pwd="+c_pwd+
							"&filename="+f.filename.value;
				
				sendRequest(url, param, delFn, "post");
				
			}
			
			function delFn(){
				if( xhr.readyState == 4 && xhr.status == 200 ){
					let data = xhr.responseText;
					let json = ( new Function('return '+data) )();
					
					//비밀번호가 틀렸으면 다음 작업 x
					if( json[0].result === "no_pwd" ){
						alert("비밀번호 불일치");
						return;
					}
					
					if( json[0].result == '아니오' ){
						alert("삭제 실패");
					}else{
						alert("삭제 성공");
						location.href="visit_list.do?page"+${param.page};
					}
				}
			}
			
			//수정
			function modify(f){
				let idx = f.idx.value;	
				let pwd = f.pwd.value;
				let c_pwd = f.c_pwd.value;
					
				let url = "modify_password_chk.do";
				let param = "pwd="+pwd+"&c_pwd="+c_pwd;
				sendRequest(url, param, resPwd, "post");
				
				/* f.method="post";
				f.action = "update_form.do?idx="+idx+"&page=${param.page}";
				f.submit(); */
			}
			
			function resPwd(){
				if( xhr.readyState == 4 && xhr.status == 200 ){
					let data = xhr.responseText;
					
					if( data == "no_pwd" ){
						alert("비밀번호 불일치");
						return;
					}
					
					//비밀번호 일치. form태그 가져오기
					let f = document.getElementById("myf");
					f.method="post";
					f.action = "update_form.do";
					f.submit();
					
				}
			}
		</script>
		
	</head>
	
	
	<body>
		<div id="main_box">
			<h1>::: 방명록 리스트 :::</h1>
			
			<div>
				<input type="button" value="글쓰기"
					   onclick="location.href='insert_form.do?page=${param.page}'">
			</div>
			
			<c:forEach var="vo" items="${list}">
			<div class="visit_box">
				<div class="type_content"><pre class="con">${vo.content}</pre>
				
					<div>
					<!-- 첨부된 이미지가 있는 경우에만 img태그를 사용 -->
					<c:if test="${ vo.filename ne 'no_file' }">
						<img src="${ pageContext.request.contextPath }/resources/upload/${vo.filename}"
							  width="330">
					</c:if>
					</div>
				
				</div>
				
				
				
				<div class="type_name">${vo.name}(${vo.ip})</div>
				<div class="type_regdate">${ vo.regdate }</div>
								
				<form id="myf">
					<input type="hidden" name="idx" value="${vo.idx}">
					<input type="hidden" name="pwd" value="${vo.pwd}">
					<input type="hidden" name="page" value="${ param.page }">
					<input type="hidden" name="filename" value="${ vo.filename }">
					
					비밀번호 : <input type="password" name="c_pwd">
					<input type="button" value="수정"
						   onclick="modify(this.form);">
					<input type="button" value="삭제"
						   onclick="del(this.form);">
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