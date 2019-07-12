<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>게시판</title>

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- 내꺼  -->
<link href="./css/BackGround.css" rel="stylesheet" type="text/css">


</head>
<body>
	<div class="container">
		<div class="table-wrapper">

			<li>Title - ${board.title}</li>
			<li>Name - ${board.userId}</li>
			<li>Date - ${board.date}</li>
			<li>View - ${board.view}</li>
			<p></p>
			${board.contents}
			<p></p>
			<%-- if 문 --%>
			<c:choose>
				<%-- 내가 로그인한 id와 게시글을 적은 user id와 같은지 확인 --%>
				<c:when test="${board.userId == sessionScope.id}">
					<ul class="pagination justify-content-center">
						<li class="page-item">
							<form action="./board" method="post">
								<input type="submit" value="삭제"> <input type=hidden
									name="type" value="delete"> <input type=hidden
									name="boardId" value="${board.boardId}">
							</form>

						</li>
					</ul>
					<ul class="pagination justify-content-center">
						<li class="page-item">
							<form action="./board" method="post">
								<input type="submit" value="수정"> <input type=hidden
									name="type" value="replace"> <input type=hidden
									name="boardId" value="${board.boardId}">
							</form>
						</li>
					</ul>
				</c:when>

			</c:choose>

		</div>
	</div>

</body>
</html>