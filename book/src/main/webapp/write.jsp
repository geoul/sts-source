<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<hr/>
	<form method="post" action="/book/write">
		<input type="text" name="bookNm" placeholder="책 제목을 입력하세요." /><br/>
		<input type="text" name="bookSubNm" placeholder="부제를 입력하세요." /><br/>
		<input type="text" name="index" placeholder="목차를 입력하세요." /><br/>
		<input type="submit" value="책 등록" />
	</form>
</body>
</html>