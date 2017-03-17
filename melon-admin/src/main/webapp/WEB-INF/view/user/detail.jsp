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
		$("#auth").val("${user.authorizationId}");
		
		$("#pointBtn").click(function() {
			var buttonText = $(this).val();
			
			if ( buttonText == "변경하기" ) {
				$(this).val("변경완료");
				$("#point").removeAttr("disabled");
			}
			else if ( buttonText == "변경완료" ) {
				$(this).val("변경하기");
				// disabled 속성을 다시 disabled로 바꿔라.
				$("#point").attr("disabled", "desabled");
			}
		});
		
		$("#passwordBtn").click(function() {
			var buttonText = $(this).val();
			
			if( buttonText == "변경하기" ) {
				$(this).val("변경완료");
				$("#password").removeAttr("disabled");
			}
			else if ( buttonText == "변경완료" ) {
				$(this).val("변경하기");
				$("#password").attr("disabled", "disabled");
			}
		});
		
		
		$("#authBtn").click(function() {
			var buttonText = $(this).val();
			
			if ( buttonText == "변경하기" ) {
				$(this).val("변경완료");
				$("#auth").removeAttr("disabled");
			}
			else if ( buttonText == "변경완료" ) {
				$.post("/melon-admin/user/modify", {
					"authorizationId": $("#auth").val(),
					"userId": userId
				}, function(response) {
					var jsonObj = JSON.parse(response);
					if (jsonObj.status == "success") {
						$("#authBtn").val("변경하기");
						
						// disabled 속성을 다시 disabled로 바꿔라.
						$("#auth").attr("disabled", "disabled");
					}
				});
			}
		});
	});
</script>
</head>
<body>
	
	${user.userId} <br/>
	${user.userName} <br/>
	
	<form id="modifyForm">
		<input type="text" id="point" disabled="disabled" val="${user.userPoint}"/>
		<input type="button" id="pointBtn" value="변경하기" />
		<br/>
		<input type="password" id="password" disabled="disabled" />
		<input type="button" id="passwordBtn" value="변경하기" />
		<br/>
		<select id="auth" disabled="disabled">
			<c:forEach items="${authList}" var="auth">
			<!-- 사용자의 권한과 비교해야 함. -->
			<option value="${auth.authorizationId}">${auth.authorizationName}</option>
			</c:forEach>
		</select>
		<input type="button" id="authBtn" value="변경하기" />
	</form>
	
</body>
</html>