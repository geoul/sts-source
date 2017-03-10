<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/melon/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready(function() {
		$("#writeForm").find("input[type=button]").click(function() {
			$.post("/melon/artist/write", 
					{
						'artistName' : $("#artistName").val(),
						'debutDate' : $("#debutDate").val(),
 						'debutTitle' : $("#debutTitle").val()
					},
					function(response) {
						if( response == 'OK' ) {
							location.reload();  // 화면 새로고침
						}	
						else {
							alert("아티스트 등록을 실패했습니다.\n관리자에게 문의하세요.");
						}
					});
		});
});
</script>
</head>
<body>

	<form id="writeForm">
		<input type="text" id="artistName" placeholder="아티스트 이름" /><br/>
		<input type="date" id="debutDate" placeholder="데뷔 날짜" /><br/>
		<input type="text" id="debutTitle" placeholder="데뷔 곡" /><br/>
		<input type="button" value="등록" />
	</form>
	
</body>
</html>