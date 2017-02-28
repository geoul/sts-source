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

	<h1>${board.subject}</h1>
	<hr/>
	<span>${board.writer}</span> /
	<span>${board.writeDate}</span> /
	<span>${board.likeCount}</span>
	<br/>
	<p>
		${board.content}
	</p>
	<hr/>
	<a href="/board/modify?boardId=${board.boardId}">수정</a> | 삭제 | 목록으로 돌아가기 | 글쓰기
	
</body>
</html>