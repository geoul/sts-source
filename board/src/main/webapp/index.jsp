<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.ktds.jgu.board.biz.BoardBiz,
    				com.ktds.jgu.board.biz.BoardBizImpl,
    				com.ktds.jgu.board.vo.BoardVO,
    				java.util.List,
    				java.util.ArrayList" %>
    				<!-- import는 콤마로 구분한다 -->
    				<!-- Ctrl을 누르고 import한 것에 올렸을 때 밑줄이 생겨야 오타가 없는것이다 -->
    				<!-- Model 1 방식 (사용하지 않는다) -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	// Scriptlet 시험에 나온다.
	BoardBiz boardBiz = new BoardBizImpl();
	List<BoardVO> boardList = boardBiz.getAllArticles();
%>

<table>
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>좋아요</th>
	</tr>
	<%
		BoardVO board = null;
		for (int i = 0; i < boardList.size(); i++) {
			board = boardList.get(i);
			// for문을 돌면서 boardList에 있는 값을 넣어줌
	%>
	<!-- row를 만들어줘야함. -->
	<tr>
		<td><%= board.getBoardId() %></td>
		<td><%= board.getSubject() %></td>
		<td><%= board.getWriter() %></td>
		<td><%= board.getWriteDate() %></td>
		<td><%= board.getLikeCount() %></td>
	</tr>
	<%	} %>
</table>

</body>
</html>