<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/common_header.jsp"/>
<link rel="stylesheet" type="text/css" href="/board/css/list_layout.css" />
<script type="text/javascript" src="/board/js/jquery-3.1.1.min.js"></script>
		<div class="grid">
		총 ${count}건의 게시글이 검색되었습니다.
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
						<td>${article.writer}</td>
						<td>${article.writeDate}</td>
						<td>${article.likeCount}</td>
					</tr>
				</c:forEach>
				
			</table>
		
		<a href="/board/write">글쓰기</a>
		<div>
			<form method="post" id="searchForm"> <!-- action을 적지 않으면 스스로에게 요청한다. -->
				${pager}
			</form>
		</div>
		<!-- 상대주소 : protocol과 host를 제외시킨 것 -->
		</div><!--
		--><div class="login">
			<c:if test="${empty sessionScope._USER_}"><!-- sessionScope._USER_가 비어있다면 -->
				<jsp:include page="/view/users/signIn.jsp" />
			</c:if>
			<c:if test="${not empty sessionScope._USER_}">
				${sessionScope._USER_.userName}님, 환영합니다!
			</c:if>
		</div>
<jsp:include page="/template/common_footer.jsp"/>