<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/common_header.jsp" />
<link rel="stylesheet" type="text/css" href="/board2/css/list_layout.css" />
<link rel="stylesheet" type="text/css" href="/board2/css/detail_layout.css" />
<link rel="stylesheet" type="text/css" href="/board2/css/write_layout.css" />
<script type="text/javascript">
	$().ready(function() {
		// id가 writeForm인 것 중에서 input type이 button인 것을 찾아서 클릭했을 때 실행해라.
		$("#writeForm").find("input[type=button]").click(function() {
			// attr() : 속성을 set하거나 get하는것 (속성을 재활용)
			/*$("#writeForm").attr("method", "post");
			$("#writeForm").attr("action", "/board/doWrite");*/
			$("#writeForm").attr( {
				"method" : "post",
				"action" : "/board2/doWrite"
			} );
			//id가 writeForm 인 것을 전송해라. (여기에서 submit()을 해주기 때문에 input type 에서 submit을 지운 것임. - 일반적인 전송 방식)
			$("#writeForm").submit();
		});
		
		// id가 writeForm 인 것에서 img를 찾아서 click을 실행한다.
		$("#writeForm"),find("img").click(function() {
			// 요청을 보낼 주소, 파라미터(1.객체로 보내는 방법, 2.form을 보내는 방법 두 가지가 있다.), 콜백 : url로 파라미터를 보내고 콜백에서 응답을 받는다.
			// AJAX는 form 으로 보낼 수 없다. 여기에서 보낸다.
			// AJAX는 파일을 보낼 수 없다. 반드시 form 으로 보내야 한다.
			$.post("/board2/doWrite", {
				"subjects" : $(".subjects").val(),
				"contents" : $(".contents").val()
			}, function(response) {
				alert("글쓰기가 잘 완료되었습니다.");
			});
		});
		
	});
</script>
	<div class="write">
		<h1>글 쓰기</h1>
		<hr/>
		<form id="writeForm">
			<!-- <input type="text" name="writers" placeholder="작성자명 입력하세요" /></br> -->
			<input type="text" class="subjects" name="subjects" placeholder="제목 입력하세요" /></br>
			<textarea name="contents" class="contents" placeholder="내용 입력하세요" ></textarea></br>
			<div>
				<input type="button" value="글 올리기"/>
				<img src="/board2/img/ic_note_add_black_24dp_2x.png" />
			</div>
		</form>
	</div><!-- 
	--><div class="login">
		<c:if test="${ empty sessionScope._USER_}">
			<jsp:include page="/view/users/signIn.jsp" />
		</c:if>
		<c:if test="${ not empty sessionScope._USER_}">
			${sessionScope._USER_.userName}님, 환영합니다!
		</c:if>
	</div>
<jsp:include page="/template/common_footer.jsp" />