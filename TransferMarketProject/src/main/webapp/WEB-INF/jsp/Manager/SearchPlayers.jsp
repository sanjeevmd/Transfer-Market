<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transfer Market</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/Manager/header.jsp" %>
<br>
<form action="SearchPlayers">
	<input type="text" name="Search">
	<input type="submit" name="Submit" value="Submit">
</form>
<table>
	<tr>
		<th>
		Players
		</th>
	</tr>
	<c:forEach var="lang" items="${players}">
	<tr>
	   	<td>
	   		 <c:out value = "${lang.getName()}"/>
	   	</td>
	   	<td>
		   	<form id="form" action="AddWatch">
		   		 <button type="submit" name="selectedPlayer" form="form" value="${lang.getId()}">
	   		 Add Watch</button>
		   	</form>
	   	</td>
	</tr>
    </c:forEach>
</table>
</body>
</html>