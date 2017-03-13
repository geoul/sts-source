<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
<script type="text/javascript" src="/melon/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="/melon/static/js/json2.js"></script>
<script>
$().ready(function() {
	$("#userId").keyup(function() {
		$.post("/melon/user/checkDuplicate",
				{
					"userId" : $("#userId").val()
				},
				function(response){
					var jsonObj = JSON.parse(response);
					console.log(JSON);
					console.log(jsonObj);
					
					// 페이지가 깜빡거리지 않아도 응답(response)을 바로 받아올 수 있는 것이 AJAX 이다.
					if ( jsonObj.duplicated ) {
						$("#signUpForm").text("이미 사용 중인 아이디 입니다.");
					}
					else {
						$("#signUpForm").text("사용 가능한 아이디 입니다.");
					}
				});
	});
	
	$("#signUpForm").find("input[type=button]").click(function() {
		
		//필수 입력값 체크
		if ( $("#userId").val() == "" ) {
			alert("아이디를 입력하세요!!");
			$("#userId").focus();
			return;  //반환시킨다. return값이 없으면 종료시킨다.
		}
		
		if ( $("#userPassword").val().length <= 7 ) {
			alert("비밀번호는 8자리 이상으로 입력하세요.")
			$("#userPassword").focus();
			return;
		}
		
		if ( $("#userName").val() == "" ) {
			alert("이름을 입력하세요!!");
			$("#userName").focus();
			return;
		}
		
		// 마지막으로 아이디가 중복이 되는지 체크한다.
		$.post("/melon/user/checkDuplicate", {
			"userId": $("#userId").val()
		}, function(response) {
			
			//JSON으로 바꿔주는 작업
			var jsonObj = JSON.parse(response);
			
			if ( response.duplicated ) {
				alert("입력한 ID는 이미 사용 중입니다. \n다른 ID를 사용해 주세요.");
				return;
			}
			else {
				$("#signUpForm").attr({
					"action": "/melon/user/signUp",
					"method": "post"
				});
				$("#signUpForm").submit();
			}
			
		});
		
		
	});
});
</script>
</head>
<body>
	<c:if test="${not empty param.errorCode}"> <!-- errorCode가 있다면 -->
		<div>
			<c:choose> <!-- if - else if - else if - else 와 같다 -->
				<c:when test="${param.errorCode == '0'}">
					ID는 필수 입력값 입니다.
				</c:when>
				<c:when test="${param.errorCode == '1'}">
					Password는 필수 입력값 입니다.
				</c:when>
				<c:when test="${param.errorCode == '2'}">
					Name은 필수 입력값 입니다.
				</c:when>
				<c:when test="${param.errorCode == '3'}">
					이미 사용중인 ID 입니다.
				</c:when>
				<c:otherwise>
					???
				</c:otherwise>
			</c:choose>
		</div>
	</c:if>
	<form id="signUpForm">
		<input type="text" name="userId" id="userId" placeholder="ID를 입력하세요." /><br/>
		<span id="duplicateState"></span>
		<br/>
		<input type="text" name="userPassword" id="userPassword" placeholder="PW를 입력하세요." /><br/>
		<input type="text" name="userName" id="userName" placeholder="이름을 입력하세요." /><br/>
		<input type="submit" value="가입하기" />
	</form>
	
</body>
</html>