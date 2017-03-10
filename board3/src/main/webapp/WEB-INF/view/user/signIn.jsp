<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<form method="post" action="/board3/user/doSignIn">
		<div class="userInfo">
			<input type="text" name="userId" placeholder="아이디를 입력하세요." /><br/>
			<input type="password" name="userPassword" placeholder="비밀번호를 입력하세요." /><br/>
		</div>
		<div class="btn">
			<input type="submit" value="SignIn!" />
		</div>
	</form>
	<div class="userHelp">
		<a href="/board3/user/signUp">회원가입</a> | 
		<a href="/board3/user/signUp">ID/비밀번호 찾기</a>
	</div>
