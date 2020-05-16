<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transfer Market</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/Player/header.jsp"%>
	<br>
	<c:set var="element" value="${player}" />
	<c:choose>
	
	<c:when test="${element.getIsAvailable() == true && element.getIsAccepted() == true}">
		You are signed up for ${element.getTeam().getTeamName()}
	</c:when>
	
	<c:when  test="${element.getIsAvailable() == true}">
		<b>Current Bid Amount</b> :  <c:out value="${element.getBasePrice()}" />
		<b>Current Winning Team:</b>
		<c:out value="${element.getTeam().getTeamName()}" />
		<form action="completeSign" method="Post">
			<input type="submit"
				value="Sign for ${element.getTeam().getTeamName()}" />
		</form>
	</c:when>
	<c:when test="${element.getIsAvailable() == false}">
		<b>Current Bid Amount</b> :  <c:out value="${element.getCbAmount()}" />
		<b>Current Winning Team:</b>
		<c:out value="${element.getCbTeam().getTeamName()}" />
		<form action="completeSign" method="Post">
			<input type="submit"
				value="Sign for ${element.getCbTeam().getTeamName()}" />
		</form>
	</c:when>
	</c:choose>

</body>
</html>