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
		var userId = "${param.userId}";
		$.post("/melon-admin/authorization/all", {}, function(response) {
			// JSON Format String을 Javascript Object로 변환함.
			var auth = JSON.parse(response);
			// ViewAllAuthorizationServlet.java에서 authorizations 배열을 가져와야하기 때문에 똑같이 authorizations라고 적어준다.
			var authList = auth.authorizations;
			
			// 밑에 있는 authorization id의 div를 가져오겠다.
			var authorizationDiv = $("#authorization");
			
			for(var i in authList) {
				console.log(authList[i].authorizationName);
				
				/*
				 * 인증 : Authentication (보통 Auth라고 쓰면 인증의 의미로 본다.) 
				 * 인증에 필요한 데이터를 Cridential 이라고 부른다.
				 * 인가 : Authorization
				 */
				
				 // 저장 버튼 밑에 텍스트로 권한을 보여주는 부분
				var eachAuth = $("<div id='" + authList[i].authorizationId + "'></div>");
				eachAuth.text(authList[i].authorizationName);
				// eachAuth.data에 parent의 이름으로 값을 넣어준다...
				eachAuth.data("parent", authList[i].parentAuthorizationId);
				authorizationDiv.append(eachAuth);
				
				/*
				 * option : select tag의 필수 하위 요소
				 * <select name='a'>
				 * 		<option value='값1'>이름</option>
				 * 		<option value='값2'>이름</option>
				 * </select>
				 * <select name='b'>
				 * 		<option value='값1'>이름</option>
				 * 		<option value='값2'>이름</option>
				 * </select>
				 * 위의 경우, 선택된 값이 서버로 전달된다.(a라는 이름으로 값1 이 전달된다.)
				 * value --> 서버로 전달될 값
				 * 이름 --> 사용자에게 보여질 의미있는 값
				 */
				
				var itemAuth = $("<option value='" + authList[i].authorizationId + "'>" + authList[i].authorizationName + "</option>");
				 beforeAuth.append(itemAuth);
			}
		
		$("#addAuth").click(function() {
			$.post("/melon-admin/authorization/regist", {
				"authorizationName": ${"#authorizationName"}.val(),
				"beforeAuth": ${"#beforeAuth"}.val()
			}, function(response) {
				var auth = JSON.parse(response);
				var authInfo = auth.authorization;
				console.log(authorization);
				console.log(authInfo.authorizationName);
				
				var authorizationDiv = $("#authorization");
				var parentAuthorizationId = $("#beforeAuth");
				
				var eachAuth = $("<div id='" + authInfo.authorizationId + "'></div>");
				eachAuth.text(authInfo.authorizationName);
				eachAuth.data("parent", authInfo.parentAuthorizationId);
				authorizationDiv.append(eachAuth);
				
				var itemAuthorization = $("<option value='" + authInfo.authorizationId + "'>" + authInfo.authorizationName + "</option>");
				parentAuthorizationId.append(itemAuthorization);
			});
		});
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
		<c:forEach items="${authList}" var="auth" varStatus="index">		
			<tr>
				<td>${index.index}<br/></td>
					<div>
						<td>
							<a href="/melon-admin/user/list?userId=${auth.userId}"></a>
						</td>
						<td>${auth.userName}</td><br/>
						<td>${auth.userPoint}</td><br/>
						<td>${auth.userPassword}</td><br/>
						<td>${auth.authorizationId}</td>
					</div>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<div id="update">
		<form id="updateForm">
			<input type="hidden" id="authorizationId" />
			<select id="beforeAuth">
				<option value="">권한없음</option>
				<c:forEach items="${authList}" var="auth">
					<!-- 사용자의 권한과 비교해야 함. -->
					<option value="${auth.authorizationId}">${auth.authorizationName}</option>
				</c:forEach>
			</select>
			<span>을</span>
			<select id="afterAuth">
				<option value="">권한없음</option>
				<c:forEach items="${authList}" var="auth" >
					<option value="${auth.authorizationId}">${auth.authorizationName}</option>
				</c:forEach>
			</select>
			<span>로</span>
			<input type="button" id="updateBtn" value="변경" />
		</form>
	</div>
	<div id="authorization"></div>
	
	<input type="button" id="addAuth" value="회원추가"/> <br/>
	<input type="button" id="modithAuth" value="일괄수정"/> <br/>
	<input type="button" id="deleteAuth" value="일괄삭제"/> <br/>
	
</body>
</html>