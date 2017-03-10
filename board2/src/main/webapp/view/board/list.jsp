<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/common_header.jsp" />
<link rel="stylesheet" type="text/css" href="/board2/css/list_layout.css" />
<script type="text/javascript" src="/board2/js/jquery-3.1.1.min.js"></script>

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
					<c:forEach items="${ boards }" var="board" >
					<tr>
						<td>${board.boardId }</td>
						<td>
							<a href="/board2/detail?boardId=${board.boardId }">${board.subject }</a>
						</td>
						<td>${board.user.userName}(${board.writer})</td>
						<td>${board.writeDate }</td>
						<td>${board.likeCount }</td>
					</tr>
					</c:forEach>
				</table>
				<a href="/board2/write" >글 작성하기</a>
				<div>
					<form method="post" id="searchForm">
						${pager}
					</form>
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







