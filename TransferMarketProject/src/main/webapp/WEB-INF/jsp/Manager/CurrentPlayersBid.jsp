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
<table>
	<tr>
		<th>
		Players
		</th>
		<th>
		Current Bid Amount
		</th>
		<th>
		Current Winning Team
		</th>
	</tr>
	<c:forEach var="lang" items="${players}">
	<tr>
	   	<td>
	   		 <c:out value = "${lang.getName()}"/>
	   	</td>
	   	<td>
	   		 <c:out value = "${lang.getCbAmount()}"/>
	   	</td>
	   	<td>
	   		 <c:out value = "${lang.getCbTeam().getTeamName()}"/>
	   	</td>
	   	<c:if test="${lang.getCbTeam() != null}">
	   		<td>
	   		<form id="form" action="accept" method="Post">
	   		<input type="hidden" name="selectedPlayer" value="${lang.getId()}">
	   		<input type="submit" name="submit" value="Accept">
	   		</form>
	   	</td>
	   	</c:if>
	   	<c:if test="${lang.getCbTeam() == null}">
	   		<td>
	   		<form id="form" action="withdraw" method="Post">
		   		<input type="hidden" name="selectedPlayer" value="${lang.getId()}">
		   		<input type="submit" name="submit" value="Withdraw">
	   		</form>
	   		</td>
	   	</c:if>
	</tr>
    </c:forEach>
   	</table>
</body>
</html>