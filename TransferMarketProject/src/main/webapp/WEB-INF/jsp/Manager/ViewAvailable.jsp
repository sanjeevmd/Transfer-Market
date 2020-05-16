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
	<table>
		<tr>
		<th>
		Players
		</th>
		<th>
		Bid Amount
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
	   <form action="bidPlayer" method="post">
	   	<td>
	   	<input type="text" name="bidAmount" required>
	   	</td>
	   	<td>
	   		 <c:out value = "${lang.getCbAmount()}"/>
	   	</td>
	   	 	<td>
	   		 <c:out value = "${lang.getCbTeam().getTeamName()}"/>
	   	</td>
	   	<td>
	   			 <input type="hidden" name="selectedPlayer" value="${lang.getId()}">
	   			 <input type="submit" name="submit" value="Bid">
	   	</td>
	    </form>		
	</tr>
    </c:forEach>
   	</table>
</body>
</html>