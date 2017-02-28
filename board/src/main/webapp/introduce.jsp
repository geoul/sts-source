<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 시험에 나온다 -->
<!-- 
	1. c: if
	2. c:foreach
	3. c:choose ~ c:when ~ c:otherwise (switch와 같다)
	4. c:set
	5. c:out
	6. c:import
 -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
${introduce}
	<ul>
		<li>이름
			<ol>
				<li>${name}</li>
			</ol>
		</li>
		<li>나이
			<ol>
				<li>${age}</li>
			</ol>
		</li>
		<li>집
			<ol>
				<li>${home}</li>
			</ol>
		</li>
		<li>취미
			<ol>
				<li>${hobby}</li>
			</ol>
		</li>
		<li>좋아하는 것
			<ol>
				<li>${like}</li>
			</ol>
		</li>
	</ul>
	
	<!-- items에 반복시킬 것을 넣는다. var에는 꺼내올 값을 넣는다.(단, 이름이 똑같은 것은 쓸 수 없다)-->
	<c:forEach items="${introduceList}" var="intro">
	
		${intro.name} <br/>
		${intro.age} <br/>
		${intro.home} <br/>
		${intro.hobby} <br/>
		${intro.like} <br/>
	
	</c:forEach>

</body>
</html>