<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/common_header.jsp" />
<link rel="stylesheet" type="text/css" href="/board/css/list_layout.css" />
<link rel="stylesheet" type="text/css" href="/board/css/detail_layout.css" />
	<div class="detail">
		<h1>${board.subject}</h1>
		<hr/>
		<div class="author">
			<span>${board.writer}</span> /
			<span>${board.writeDate}</span> /
			<span>${board.likeCount}</span><br/>
		</div>
		<p>${board.content}</p>
		<hr/>
		<div class="controls">
			<a href="/board/modify?boardId=${board.boardId}">수정</a> | 
			<a href="/board/doDelete?boardId=${board.boardId}">삭제</a> | 
			<a href="/board/list">목록으로 돌아가기</a> | 
			<a href="/board/write">글쓰기</a>
		</div>
	</div><!--
		--><div class="login">
			<c:if test="${empty sessionScope._USER_}"><!-- sessionScope._USER_가 비어있다면 -->
				<jsp:include page="/view/users/signIn.jsp" />
			</c:if>
			<c:if test="${not empty sessionScope._USER_}">
				${sessionScope._USER_.userName}님, 환영합니다!
			</c:if>
		</div>
<jsp:include page="/template/common_footer.jsp" />