<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/melon/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready(function() {
		$("input[type=button]").click(function() {
			var writeDiv = $("<div id='writeDiv'></div>");
			writeDiv.css({
				position: 'absolute', // absolute : 절대위치 relation : 상대위치 (button이 클릭한 위치에서)
				top: '20px',
				left: '20px',
				border: '1px solid #333333',
				padding: '15px',
				'z-index': 3,  // z축 위로 올리는 것(화면에 겹치는 것) 숫자가 1보다 크면 무조건 위로 올라간다.
				'background-color': '#FFFFFF'  //-같은 기호가 들어가면 ''(따옴표)로 감싸준다.
			});
			// load()도 AJAX의 일부이다. 경로를 적어주면 보인다.
			writeDiv.load("/melon/artist/write");
			$(this).before(writeDiv);
		});
	});
</script>
<title>Artist List</title>
</head>
<body>

	<input type="button" value="아티스트 등록" />

	<p>${artistCount}명의 아티스트가 검색되었습니다.</p>
	<table>
		<tr>
			<th>번호</th>
			<th>아티스트 명</th>
			<th>데뷔 일자</th>
		</tr>
		<c:forEach items="${artistList}" var="artist">
			<tr>
				<td>
					<fmt:parseNumber>
						${fn:split(artist.artistId, '-')[2]}
					</fmt:parseNumber>
				</td>
				<td>
					<a href="/melon/album/list?artistId=${artist.artistId}">${artist.member}</a>
				</td>
				<td>${artist.debutDate}</td>
			</tr>
		</c:forEach>
	</table>
	
	<div>
		<form id="searchForm">
			${pager}
		</form>
	</div>

</body>
</html>