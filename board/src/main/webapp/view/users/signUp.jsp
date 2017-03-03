<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
<link rel="stylesheet" type="text/css" href="/board/css/signUp_layout.css" />
</head>
<body>
<div class="signUpForm">
	<form method="post" action="/board/user/doSignUp">
		<input type="text" name="userId" placeholder="ID를 입력하세요." />
		<input type="password" name="userPassword" placeholder="비밀번호를 입력하세요." />
		<input type="text" name="userName" placeholder="이름을 입력하세요." />
		<input type="submit" value="가입하기" />
	</form>
</div>
</div>
</body>
</html>