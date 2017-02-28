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
	<table>
		<tr>
			<th>제목</th>
			<th>부제</th>
			<th>목차</th>
		</tr>
		
		<c:forEach items="${bookList}" var="book">
			<tr>
				<td>${book.bookId}</td>
				<td>
					<a href="book/detail?bookId=${book.bookId}">${book.bookName}</a>
				</td>
				<td>${book.bookSubName}</td>
				<td>${book.bookIndex}</td>
			</tr>
		</c:forEach>
	</table>
	
	<a href="/book/write">책 등록</a>
</body>
</html>