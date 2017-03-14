<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${music.title}</h1>
	<h3>${music.albumVO.artistVO.member}</h3>
	<hr/>
	<img 	src="/melon/album/post?albumId=${music.albumId}"
			width="150px" height="150px" /><br/>
	<video 	src="/melon/mp3/${music.albumId}/${music.mp3File}"
			controls="controls"></video>
</body>
</html>