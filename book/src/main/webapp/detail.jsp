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
	<hr/>
	<span>${book.bookNm }</span>
	<span>${book.bookSubNm}</span>
	<span>${book.index}</span>
	<br/>
	
	<a href="/book/modify?bookId=${book.bookId}">수정 | 삭제 | 목록으로 돌아가기 | 책 등록</a>
</body>
</html>