<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/template/common_header.jsp" />
<link rel="stylesheet" type="text/css" href="/board2/css/list_layout.css" />
<link rel="stylesheet" type="text/css" href="/board2/css/detail_layout.css" />
<link rel="stylesheet" type="text/css" href="/board2/css/write_layout.css" />
	<div class="write">
		<h1>글 수정</h1>
		<hr/>
		<form method="post" action="/board2/doModify">
			<input type="hidden" name="boardId" value="${board.boardId }"/>
			<input type="text" name="subjects" value="${board.subject }" placeholder="제목 입력하세요" /></br>
			<textarea name="contents"  placeholder="내용 입력하세요" >${board.content }</textarea></br>
			<div>
				<input type="submit" value="글 수정"/>
			</div>
		</form>
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