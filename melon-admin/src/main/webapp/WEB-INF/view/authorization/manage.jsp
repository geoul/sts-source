<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/melon-admin/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready(function() {
		
		// 1. 권한 목록 가져오기
		$.post("/melon-admin/authorization/all", {}, function(response) {
			// JSON Format String을 Javascript Object로 변환함.
			var authorization = JSON.parse(response);
			
			var authorizationDiv = $("#authorization");
			var parentAuthorizationId = $("#parentAuthorizationId");
			
			for(var i in authorization.authorizations) {
				console.log(authorization.authorizations[i].authorizationName);
				
				var eachAuthorization = $("<div id='" + authorization.authorizations[i].authorizationId + "'></div>");
				eachAuthorization.text(authorization.authorizations[i].authorizationName);
				eachAuthorization.data("parent", authorization.authorizations[i].parentAuthorizationId);
				authorizationDiv.append(eachAuthorization);
				
				var itemAuthorization = $("<option value='" + authorization.authorizations[i].authorizationId + "'>" + authorization.authorizations[i].authorizationName + "</option>");
				parentAuthorizationId.append(itemAuthorization);
			}
		});
		
		// $("#authorization").find("div").click(function() {});  // Dom 에서의 click 이벤트 방식
		$("#authorization").on("click", "div", function() {
		//	alert( $(this).text() );
		//	alert( $(this).data("parent") );
		$("#authorizationId").val( $(this).attr("id") );
			$("#authorizationName").val( $(this).text() );
			$("#parentAuthorizationId").val( $(this).data("parent") );
			
			$("#modifyBtn").remove();
			$("#deleteBtn").remove();
			
			var modifyBtn = $("<input type='button' id='modifyBtn' value='수정' />");
			var deleteBtn = $("<input type='button' id='deleteBtn' value='삭제' />");
			$("#registBtn").after(deleteBtn);
			$("#registBtn").after(modifyBtn);
			
		});
		
		$("#registForm").on("click", "#modifyBtn", function() {
			
			$.post("/melon-admin/authorization/modify", {
				// 파라미터 전달하는 부분
				"authorizationId": $("#authorizationId").val(),
				"authorizationName": $("#authorizationName").val(),
				"parentAuthorizationId": $("#parentAuthorizationId").val(),
			}, function(response) {
				var jsonObj = JSON.parse(response);
				if ( jsonObj.status == "success" ) {
					var modifiedAuth = $("#authorizationId").val();
					$("#" + modifiedAuth).text( $("#authorizationName").val() );
					$("#" + modifiedAuth).data("parent", $("#parentAuthorizationId").val());
					
					$("#parentAuthorizationId").find("option[value=" +modifiedAuth+"]").text( $("#authorizationName").val() );
				
					$("#deleteBtn").remove();
					$("#modifyBtn").remove();
					$("#authorizationId").val("");
					$("#authorizationName").val("");
					$("#parentAuthorizationId").val("");			
				}
			});
			
		});
		$("#registForm").on("click", "#deleteBtn", function() {
			
			$.post("/melon-admin/authorization/delete", {
				"authorizationId": $("#authorizationId").val()
			}, function(response) {
				var jsonObj = JSON.parse(response);
				if ( jsonObj.status == "success" ) {
					var deletedAuth = $("#authorizationId").val();
					$("#" + deletedAuth).remove();
					
					$("#parentAuthorizationId").find("option[value="+deletedAuth+"]").remove();
				
					$("#deleteBtn").remove();
					$("#modifyBtn").remove();
					$("#authorizationId").val("");
					$("#authorizationName").val("");
					$("#parentAuthorizationId").val("");
				}
			});
			
		});
		
		$("#registBtn").click(function() {
			$.post("/melon-admin/authorization/regist", {
				"authorizationName": $("#authorizationName").val(),
				"parentAuthorizationId": $("#parentAuthorizationId").val()
			}, function(response){
				//alert(response);
				var authorization = JSON.parse(response);
				console.log(authorization);
				console.log(authorization.authorization.authorizationName);
				
				var authorizationDiv = $("#authorization");
				var parentAuthorizationId = $("#parentAuthorizationId");
				
				var eachAuthorization = $("<div id='" + authorization.authorization.authorizationId + "'></div>");
				eachAuthorization.text(authorization.authorization.authorizationName);
				eachAuthorization.data("parent", authorization.authorization.parentAuthorizationId);
				authorizationDiv.append(eachAuthorization);
				
				var itemAuthorization = $("<option value='" + authorization.authorization.authorizationId + "'>" + authorization.authorization.authorizationName + "</option>");
				parentAuthorizationId.append(itemAuthorization);
				
			});
		});
		
	});
</script>
<title>Insert title here</title>
</head>
<body>

	<div id="regist">
		<form id="registForm">
			<input type="hidden" id="authorizationId" />
			<span>권한 명</span><br/>
			<input type="text" id="authorizationName" /> <br/>
			<br/>
			<span>상위 권한</span>
			<select id="parentAuthorizationId"></select> <br/>
			<br/>
			<input type="button" id="registBtn" value="저장" />
		</form>
	</div>
	<div id="authorization"></div>
</body>
</html>