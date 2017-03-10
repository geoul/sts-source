<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Album List</title>
<script type="text/javascript" src="/melon/static/js/jquery-3.1.1.min.js" ></script>
<script type="text/javascript">
	$().ready(function() {
		$("input[type=button]").click(function() {
			// 파라미터로 넘어온 아티스트 중에 아티스트 아이디를 write에 붙여주겠다.
			window.open("/melon/album/write?artistId=${param.artistId}", "앨범 등록", "resizable=no, scrollbars=yes, toolbar=no, width=300px, height=500px, menubar=no");
		});
	});
</script>
</head>
<body>

	<input type="button" value="앨범 등록" /><br/>

	<table>
		<tr>
		<!-- varStatus : 자바에서의 i값과 비슷하다. -->
			<c:forEach items="${albumList}" var="album" varStatus="index">
				<td>
					${index.index}<br/>
					<div>
					<!-- albumId를 보고 패턴이 보이니까 다음 앨범이 뭔지 알 수 있으니까 album.artistId도 같이 조건을 걸어준다.(보안 강화) -->
						<a href="/melon/music/list?albumId=${album.albumId}&artistId=${album.artistId}">
							<img src="/melon/album/post?albumId=${album.albumId}" width="150px" height="150px" /><br/>
						</a>
						${album.albumName}<br/>
						${album.artistVO.member}
					</div>
				</td>
				
				<c:if test="${(index.index + 1) % 5 == 0}">
					</tr>
					<tr>
				</c:if>
			</c:forEach>
		</tr>
	</table>

</body>
</html>