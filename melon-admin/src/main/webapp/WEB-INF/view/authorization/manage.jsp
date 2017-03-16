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
			var auth = JSON.parse(response);
			// ViewAllAuthorizationServlet.java에서 authorizations 배열을 가져와야하기 때문에 똑같이 authorizations라고 적어준다.
			var authList = auth.authorizations;
			
			// 밑에 있는 authorization id의 div를 가져오겠다.
			var authorizationDiv = $("#authorization");
			// 밑에 있는 parentAuthorizationId id의 select를 가져오겠다.
			var parentAuthorizationId = $("#parentAuthorizationId");
			
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
				parentAuthorizationId.append(itemAuth);
			}
		});
		
		// $("#authorization").find("div").click(function() {});  // Dom 에서의 click 이벤트 방식
		// eachAuth를 클릭하면...
		$("#authorization").on("click", "div", function() {
		//	alert( $(this).text() );
		//	alert( $(this).data("parent") );
		
		// 내가 선택한 div의 id(권한의 id)를 hidden으로 된 authorizationId에 값을 집어넣겠다...
		$("#authorizationId").val( $(this).attr("id") );
			$("#authorizationName").val( $(this).text() );
			/*
			 * <select name='parentAuthorizationId' name='a'>
			 * 		<option value='값1'>이름</option>
			 * 		<option value='값2'>이름</option>
			 * </select>
			 * data-parent = 값1 (현재 선택된 option의 값을 값1 로 바꿔라.)
			 */
			 
			// data-parent 값을 가져오겠다...(교재 173 page) select에 parent의 값을 선택된 값으로 바꿔준다...
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
				// authorization/modify을 호출하면 DoAuthorization을 호출한다.  json을 파싱한다.
				var jsonObj = JSON.parse(response);
				// json의 상태값이 success라면 아래를 수행해라.
				if ( jsonObj.status == "success" ) {
					// 선택한 div의 id(권한의 id)를 가지고 오겠다...
					var modifiedAuth = $("#authorizationId").val();
					// id 가 modifiedAuth인 녀석의 텍스트를 바꿔주겠다.
					$("#" + modifiedAuth).text( $("#authorizationName").val() );
					$("#" + modifiedAuth).data("parent", $("#parentAuthorizationId").val());
					
					// 권한의 id를 찾아서 가지고 와서 텍스트를 바꿔준다.
					$("#parentAuthorizationId").find("option[value=" +modifiedAuth+"]").text( $("#authorizationName").val() );
				
					$("#deleteBtn").remove();
					$("#modifyBtn").remove();
					// 입력값을 초기화 시킨다.
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
					// div를 지운다...
					$("#" + deletedAuth).remove();
					
					// select를 지운다...
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
				var auth = JSON.parse(response);
				// json으로 파싱한 거에서 DoAuthorizationRegistActionServlet의 authorization을 가져오겠다.(authorizationVO가 나올것임.)
				var authInfo = auth.authorization;
				console.log(authorization);
				console.log(authInfo.authorizationName);
				
				var authorizationDiv = $("#authorization");
				var parentAuthorizationId = $("#parentAuthorizationId");
				
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