<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="utf-8" %>
<html>
	<head>
		<title>Home</title>
	</head>
	
	
	<body>
	<h1>
		Hello world!  
	</h1>
	
	<P>  The time on the server is ${serverTime}. </P>
	${ hello } <br>
	
	Spring은 java기반의 백엔드 개발을 쉽게 할 수 있도록 도와주는 오픈소스 프레임워크다.<br>
	스프링 이전의 EJB(엔터프라이즈 자바 빈즈)라는 프레임워크를 사용하고 있었는데, <br>
	이는 개발비용이 수억에서 수십억에 달하기 때문에 개인이 개발하는데 무리가 있었다.<br>
	그러나 스프링을 통해 EJB의 90%이상의 기능을 구현할 수 있으며, <br>
	오픈소스 기반으로 제작비용이 저렴하다는 장점이 있다.
	
	</body>
</html>
