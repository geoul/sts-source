<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/board/css/common_layout.css" />
</head>
<body>
<div id="wrapper">
	<div id="nav">
		<a href="/board/user/signIn">로그인</a> | <a href="/board/user/signUp">회원가입</a> | <a href="/board/user/logout">로그아웃</a>
	</div>
	<div id="content">
		<table>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>좋아요</th>
			</tr>
			
			<c:forEach items="${articleList}" var="article">
				<tr>
					<td>${article.boardId}</td>
					<td>
						<a href="/board/detail?boardId=${article.boardId}">${article.subject}</a>
					</td>
					<td>${board.user.userName}(${article.writer})</td>
					<td>${article.writeDate}</td>
					<td>${article.likeCount}</td>
				</tr>
			</c:forEach>
			<a href="/board/write">글쓰기</a>
			<!-- 상대주소 : protocol과 host를 제외시킨 것 -->
		</table>
	</div>
</div>
	

</body>
</html>