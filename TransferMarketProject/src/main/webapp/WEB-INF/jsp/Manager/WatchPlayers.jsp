<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="transfermarketproject.Model.Watch"%>
<%@ page import="transfermarketproject.Model.Player"%>
   
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
		Player
		</th>
		<th>
		Available
		</th>
	</tr>
	<c:forEach var="lang" items="${players}">
	<tr>
	   	<td>
	   		 <c:out value = "${lang.getPlayer().getName()}"/>
	   	</td>
	   	<td>
	   		 <c:out value = "${!lang.getPlayer().getIsAvailable()}"/>
	   	</td>
	   	<td>
	   		<form action="RemoveWatch" method="Get">
	   			<input type="hidden" name="selectedPlayer" value="${lang.getId()}">
	   		 	<input type="submit" name="submit" value="Remove watch">
	   		</form>   	
	   	</td>	   	
	</tr>
    </c:forEach>
   	</table>
</body>
</html>