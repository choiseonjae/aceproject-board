<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap Simple Data Table</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style type="text/css">
body {
	color: #666;
	background: #f5f5f5;
	font-family: 'Roboto', sans-serif;
}

table.table tr th, table.table tr td {
	border-color: #e9e9e9;
}

table.table-striped tbody tr:nth-of-type(odd) {
	background-color: #fcfcfc;
}

.table-wrapper {
	background: #fff;
	padding: 20px;
	margin: 30px 0;
	box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
}

.pagination {
	margin: 10px 0 5px;
}

.pagination li a {
	border: none;
	min-width: 30px;
	min-height: 30px;
	color: #999;
	margin: 0 2px;
	line-height: 30px;
	border-radius: 4px !important;
	text-align: center;
	padding: 0;
}

.pagination li a:hover {
	color: #666;
}

.pagination li.active a, .pagination li.active a.page-link {
	background: #59bdb3;
}

.pagination li.active a:hover {
	background: #45aba0;
}

.pagination li:first-child a, .pagination li:last-child a {
	padding: 0 10px;
}

.pagination li.disabled a {
	color: #ccc;
}

.pagination li i {
	font-size: 17px;
	position: relative;
	top: 1px;
	margin: 0 2px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="table-wrapper">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>#</th>
						<th>Title</th>
						<th>Contents</th>
						<th>Name</th>
						<th>Date</th>
						<th>View</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="board" items="${boards}">
						<tr>
							<td>${board.boardId}</td>
							<td>${board.title}</td>
							<td>${board.contents}</td>
							<td>${board.userId}</td>
							<td>${board.date}</td>
							<th>${board.view}</th>
						</tr>

					</c:forEach>
				</tbody>
			</table>
			<div class="text-center">
				<ul class="pagination justify-content-center">
					<li class="page-item disabled"><a href="#"><i
							class="fa fa-long-arrow-left"></i> Previous</a></li>
					<c:forEach var="i" begin="${ startPage }" end="${ startPage + 9 }">

						<c:choose>

							<c:when test="${i == currentPage}">
								<li class="page-item active"><a href="#" class="page-link"><c:out
											value="${i}" /></a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a href="./page?currentPage=${ i }"
									class="page-link"><c:out value="${i}" /></a></li>
							</c:otherwise>

						</c:choose>


					</c:forEach>

					<li class="page-item"><a href="#" class="page-link">Next <i
							class="fa fa-long-arrow-right"></i></a></li>

					<li class="page-item"><a href="boardForm.html" class="page-link">글쓰기</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>
