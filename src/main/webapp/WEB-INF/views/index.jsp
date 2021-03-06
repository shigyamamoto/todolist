<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="{{ str_replace('_', '-', app()->getLocale()) }}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>todolist</title>

    <!-- Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Londrina+Solid:wght@100;300;400;900&display=swap" rel="stylesheet">

    <!-- Styles -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
		<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />">

</head>
<body>
	<div class="container-fluid">
		<header class="">
			<div>
				<h1>
					<a href="/todolist/tasks">
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-archive" viewBox="0 0 16 16">
							<path d="M0 2a1 1 0 0 1 1-1h14a1 1 0 0 1 1 1v2a1 1 0 0 1-1 1v7.5a2.5 2.5 0 0 1-2.5 2.5h-9A2.5 2.5 0 0 1 1 12.5V5a1 1 0 0 1-1-1V2zm2 3v7.5A1.5 1.5 0 0 0 3.5 14h9a1.5 1.5 0 0 0 1.5-1.5V5H2zm13-3H1v2h14V2zM5 7.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5z"/>
						</svg>
						todolist
					</a>
				</h1>
			</div>
		</header>
		<main>
			<h2>タスク一覧</h2>
			<div>
				<a href="/todolist/tasks/create">
					<button type="button" class="btn btn-outline-success">新規作成</button>
				</a>
			</div>
			<ul class="list-group mt-3">
				<c:forEach var="task" items="${tasks}">
					<li class="list-group-item">
						<div class="container-fluid">
							<div class="row">
								<div class="col-sm">${task.getName()}</div>
								<div class="col-sm">
									<div class="row">
										<div class="col-sm">
											<a href="${pageContext.request.contextPath}/tasks/${task.getId()}/edit">
												<button type="button" class="btn btn-outline-primary btn-sm edit">編集</button>
											</a>
										</div>
										<div class="col-sm">
											<form method="post" action="/todolist/tasks/${task.getId()}">
												<input type="hidden" name="_method" value="delete">
						      			<button type="submit" class="btn btn-outline-danger btn-sm delete">削除</button>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</li>
				</c:forEach>
			</ul>
		</main>
	</div>
</body>
</html>
