<%--
  Created by IntelliJ IDEA.
  User: Eugene_M
  Date: 14-авг-18
  Time: 10:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" uri="http://utils.com" %>
<html>
<head>
	<title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<table cellspacing="0" border="1">
	<thead>Meals</thead>
	<tbody>
	<tr>
		<th>description</th>
		<th>dateTime</th>
		<th>calories</th>
	</tr>
	<c:forEach items="${requestScope.meals}" var="meal">
		<c:choose>
			<c:when test="${meal.exceed == true}">
				<tr style="color: #d31218">
			</c:when>
			<c:otherwise>
				<tr style="color: #068109">
			</c:otherwise>
		</c:choose>
			<td>${meal.description}</td>
			<td>${u:dtf(meal.dateTime, 'dd.MM.yyyy hh:mm')}</td>
			<td>${meal.calories}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</body>
</html>
