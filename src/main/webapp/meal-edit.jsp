<%--
  Created by IntelliJ IDEA.
  User: Eugene_M
  Date: 15-авг-18
  Time: 11:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Meal edit</title>
</head>
<body>
<form action="meals" method="post">
	<table>
		<thead>Meal edit</thead>
		<tbody>
		<tr>
			<td>description</td>
			<td><input type="text" name="description" value="${param.description}"></td>
		</tr>
		<tr>
			<td>dateTime</td>
			<td><input type="datetime-local" name="dateTime" value="${param.dateTime}"></td>
		</tr>
		<tr>
			<td>calories</td>
			<td><input type="number" name="calories" value="${param.calories}"></td>
		</tr>
		</tbody>
	</table>
	<button type="submit">submit</button>
</form>
</body>
</html>
