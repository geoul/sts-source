<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/common_header.jsp" />
<link rel="stylesheet" type="text/css" href="/board2/css/list_layout.css" />
<link rel="stylesheet" type="text/css" href="/board2/css/detail_layout.css" />
	<div class="detail">
		<h1>${boards.subject }</h1>
		<hr/>
		<div class="author">
			<span>${boards.writer }</span> /
			<span>${boards.writeDate }</span> /
			<span>${boards.likeCount }</span><br/>
		</div>
		<p>${boards.content }</p><br/>
		<hr/>
		<div class="controls">
			<a href="/board2/modify?boardId=${boards.boardId }">수정</a> | 
			<a href="/board2/doDelete?boardId=${ boards.boardId }">삭제</a> |
			<a href="/board2/list">목록으로 돌아가기</a> | 
			<a href="/board2/write">글쓰기</a>
		</div>
	</div><!-- 
	--><div class="login">
		<c:if test="${ empty sessionScope._USER_}">
			<jsp:include page="/view/users/signIn.jsp" />
		</c:if>
		<c:if test="${ not empty sessionScope._USER_}">
			${sessionScope._USER_.userName}님, 환영합니다!
		</c:if>
	</div>
<jsp:include page="/template/common_footer.jsp" />