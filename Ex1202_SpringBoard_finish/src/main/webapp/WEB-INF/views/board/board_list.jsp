<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		
		<style>
			a{text-decoration:none;
			  color:black;}
		</style>
		
		<script>
		
			window.onload = function() {
				let search = document.getElementById("search");
				let search_text = document.getElementById("search_text");
				
				let search_arr = ["all", "subject", "name", "content", "name_subject_content"];
				for(let i = 0; i < search_arr.length; i++){
					if( '${param.search}' === search_arr[i]){
						search[i].selected = true;
						search_text.value = '${param.search_text}';
						break;
					}
				} //for
			}
			
			function search() {
				//검색할 카테고리
				let search = document.getElementById("search").value;
				//검색할 검색어
				let search_text = document.getElementById("search_text").value;
				
				//전체보기가 아닐 때는 반드시 검색어가 포함되어 있어야 한다.
				if(search != "all" && search_text === ''){
					alert("검색할 내용을 입력하세요");
					return;
				}

				location.href = "list.do?search="+search+
								"&search_text="+encodeURIComponent(search_text)+
								"&page=1";
			}
		</script>
		
	</head>
	
	<body>
		<table width="700" align="center">
			<tr>
				<td><img src="/bbs/resources/img/title_04.gif"></td>
			</tr>
			
			<tr>
				<th width="50">번호</th>
				<td><img src="/bbs/resources/img/td_bg_01.gif"></td>
				
				<th width="300">제목</th>
				<td><img src="/bbs/resources/img/td_bg_01.gif"></td>
				
				<th width="90">작성자</th>
				<td><img src="/bbs/resources/img/td_bg_01.gif"></td>
				
				<th width="90">작성일</th>
				<td><img src="/bbs/resources/img/td_bg_01.gif"></td>
				
				<th width="60">조회수</th>
				
			</tr>
			
			<c:forEach var="vo" items="${list}">
			<tr>
				<td align="center">${vo.idx}</td>
				<td><img src="/bbs/resources/img/td_bg_01.gif"></td>
				
				
				<td>
					<!-- 댓글은 들여쓰기 한다 -->
					<c:forEach begin="1" end="${vo.depth}">&nbsp;</c:forEach>
					
					<!-- 댓글 기호 표기 -->
					<c:if test="${ vo.depth ne 0 }">ㄴ</c:if>
					
					<c:if test="${ vo.del_info ne -1 }">
						<a href="view.do?idx=${ vo.idx }&page=${ empty param.page ? 1 : param.page }&search=${param.search}&search_text=${param.search_text}">
							${ vo.subject }	
						</a>
					</c:if>
					
					<c:if test="${ vo.del_info eq -1 }">
						<font color=gray>이미 삭제된 게시글 입니다.</font>
					</c:if>
								
				</td>
				
				<td><img src="/bbs/resources/img/td_bg_01.gif"></td>
				
				<td>
					${ vo.name }<br>
					(${ vo.ip })
				</td>
				<td><img src="/bbs/resources/img/td_bg_01.gif"></td>
				
				<td>${ vo.regdate }</td>
				<td><img src="/bbs/resources/img/td_bg_01.gif"></td>
				
				<td align="center">${ vo.readhit }</td>
				
			</tr>
			
			</c:forEach>
			
			<tr>
				<td colspan="9" align="center">
					${ pageMenu }
				</td>
			</tr>
			
			<tr>
				<td colspan="9" align="center">
					<!-- 검색기능 -->
					<select id="search">
						<option value="all">::: 전체보기 :::</option>
						<option value="subject">제목</option>
						<option value="name">이름</option>
						<option value="content">내용</option>
						<option value="name_subject_content">이름+제목+내용</option>						
					</select>
					
					<input id="search_text">
					<input type="button" value="검색" onclick="search();">
				</td>
			</tr>
			
			<tr>
				<td colspan="9" align="right">
					<!-- 새글 등록버튼 -->
					<img src="/bbs/resources/img/btn_reg.gif"
						 style="cursor:pointer"
						 onclick="location.href='write_form.do'">
				</td>
			</tr>			
			
		</table>
	</body>
</html>























