<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<table>
				<thead>
					<tr> 
						<th>No</th>
						<td>${board.boardId}</td>
						<th>Title</th>
						<td>${board.title}</td>
					</tr>
					<tr>
						<th>Name</th>
						<td>${board.userId}</td>
						<th>Date</th>
						<td>${board.date}</td>
						<th>View</th>
						<td>${board.view}</td>
					</tr>
				</thead>
				<tbody>
					<td>${board.contents}</td>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>