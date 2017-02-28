<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>책 수정</h1>
	<hr />
	<form method="post" action="/book/modify">
		<input type="hidden" name="bookId" value="${book.bookId}" />
		<input type="text" name="bookNm" value="${book.bookNm}" placeholder="책 제목을 입력하세요." />
		<input type="text" name="bookSubNm" value="${book.bookSubNm}" placeholder="책 부제를 입력하세요." />
		<input type="text" name="index" value="${book.index}" placeholder="목차를 입력하세요." />
		<input type="submit" value="책 수정" />
	</form>
	
</body>
</html>