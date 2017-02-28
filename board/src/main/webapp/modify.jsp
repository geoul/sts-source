<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>글수정</h1>
	<hr/>
	<!-- 이쪽으로 이 정보들을 보내라. method에 post를 하면 doPost로 전달한다. -->
	<form method="post" action="/board/doModify">
		<!-- 사용자에게 보이지 않지만 폼을 전달할 때 같이 전달되는 것 -->
		<input type="hidden" name="boardId" value="${board.boardId}" /> 
		<input type="text" name="writer" value="${board.writer}" placeholder="이름을 입력하세요." /><br /> 
		<input type="text" name="subject" value="${board.subject}" placeholder="제목을 입력하세요." /><br />
		<textarea name="content" placeholder="내용을 입력하세요.">${board.content}</textarea><br />
		<!-- textarea에는 엔터를 치면 안된다. 내용을 입력하는 것이기 때문에 엔터 친 만큼의 데이터가 이미 들어가버린다. -->
		<input type="submit" value="글수정" />
	</form>
</body>
</html>