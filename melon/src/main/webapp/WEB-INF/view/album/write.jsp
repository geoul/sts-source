<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/melon/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready(function() {
		$("#writeForm").find("input[type=button]").click(function() {
			$("#writeForm").attr({
				"action" : "/melon/album/write?artistId=${param.artistId}", 
				"method" : "post"
			});
			$("#writeForm").submit();
		});
	});
</script>
</head>
<body>
	<!-- id는 자바스크립트가 쓰는 것이고, name은 서버로 전달할 때 쓰는 것이다. -->
	<!-- form 엘리먼트에서 file 타입이 있을 경우(파일을 보낼 때) enctype을 반드시 추가 해줘야 한다.(파일 보내는 것을 AJAX가 할 수 없다.) -->
	<form id="writeForm" enctype="multipart/form-data">
		<input type="text" name="albumName" placeholder="앨범 명을 입력하세요." /><br/>
		<input type="date" name="releaseDate" placeholder="발매일을 선택하세요." /><br/>
		<input type="text" name="publisher" placeholder="발매사"/><br/>
		<input type="text" name="entertainment" placeholder="소속사" /><br/>
		<input type="text" name="genre" placeholder="장르" /><br/>
		<input type="file" name="post" placeholder="앨범 포스트를 선택하세요." accept=".gif, .jpg, .png"/><br/>
	
		<input type="button" value="등록" />
	</form>
</body>
</html>