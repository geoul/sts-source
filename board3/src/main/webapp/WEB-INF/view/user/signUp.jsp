<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form method="post" action="/board2/user/doSignUp">
		<input type="text" name="userId" placeholder="ID를 입력하세요." /><br/>
		<input type="password" name="userPassword" placeholder="비밀번호를 입력하세요." /><br/> 
		<input type="text" name="userName" placeholder="이름을 입력하세요." /><br/>
		<input type="submit" value="가입하기" />
	</form>
	
</body>
</html>