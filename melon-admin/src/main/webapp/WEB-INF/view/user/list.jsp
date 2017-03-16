<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/melon-admin/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready(function() {
	//	$.post("/melon-admin/user/list", {}, function(response) {
	//		var user = JSON.parse(response);
	//		var userList = user.users;
	//		var userDiv = ${}
	//	});
	});
</script>
</head>
<body>
	<table>
		<tr>
			<th>번호</th>
			<th>ID</th>
			<th>이름</th>
			<th>Point</th>
			<th>비밀번호</th>
			<th>권한</th>
		</tr>
		<c:forEach items="${userList}" var="user" varStatus="index">		
			<tr>
				<td>${index.index}<br/></td>
					<div>
						<td>
							<a href="/melon-admin/user/list?userId=${user.userId}"></a>
						</td>
						<td>${user.userName}</td><br/>
						<td>${user.userPoint}</td><br/>
						<td>${user.userPassword}</td><br/>
						<td>${user.authorizationId}</td>
					</div>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<input type="button" value="회원추가"/> <br/>
	<input type="button" value="일괄수정"/> <br/>
	<input type="button" value="일괄삭제"/> <br/>
	
</body>
</html>