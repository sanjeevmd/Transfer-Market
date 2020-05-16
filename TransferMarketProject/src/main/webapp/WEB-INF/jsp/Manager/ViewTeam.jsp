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
	<h3>Team Players</h3>
	<table>
	<tr>
		<th>
			Player
		</th>
		<th>
			Base Price
		</th>
		<th>
			Bid Last Date
		</th>
		<th>
		</th>
	</tr>
	<c:forEach var="lang" items="${players}">
	<tr>
	   	<td>
	   		 <c:out value = "${lang.getName()}"/>
	   	</td>
	   	<form action="movePlayer" method="get">
	   	<td>
	   		<input type="text" required name="basePrice" value="${lang.getBasePrice()}">
	   	</td>
	   	<td>
	   		<input type="date" required name="bidLastDate">
	   	</td>
	   	<td>
	   	<input type="hidden" name="selectedPlayer" value="${lang.getId()}">
	    <input type="submit" name="submit" value="Release" >
	   	</td>
	    </form>	   
	</tr>
    </c:forEach>
   	</table>
</body>
</html>