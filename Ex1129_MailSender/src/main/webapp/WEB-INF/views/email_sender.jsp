<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		
		<script src="/mail/resources/js/httpRequest.js"></script>
	
		<script>
			function mailCheck(f){
				
				let userEmail = f.userEmail.value;
				let url = "mailCheck.do";
				let param = "email=" + encodeURIComponent(userEmail);
				
				sendRequest(url, param, resultMail, "post")
			}
			
			let res;
			function resultMail(){
				if( xhr.readyState == 4 && xhr.status == 200 ){
					let data = xhr.responseText;
					alert("인증코드가 이메일로 전송됐습니다.");
					
					let check_input = document.getElementById("check_input");
					check_input.disabled = false; //인증번호 입력란 활성화
					
					res = data;
				}
			}
			
			function change_input(){
				let check_input = document.getElementById("check_input");
				let mail_check_warn =
						document.getElementById("mail_check_warn");
				
				if( check_input.value == res ){
					mail_check_warn.innerHTML = "인증성공";
				}else{
					mail_check_warn.innerHTML = "인증번호 불일치";
				}
				
			}
		</script>
	</head>
	
	<body>
		<form>
			<input name="userEmail">
			<input type="button" value="본인인증"
				   onclick="mailCheck(this.form);">
		
			<input id="check_input" placeholder="인증번호 6자리"
				   disabled="disabled">
			
			<input type="button" value="인증번호 확인"
				   onclick="change_input();">
		
			<div id="mail_check_warn"></div>
		</form>
	</body>
</html>


