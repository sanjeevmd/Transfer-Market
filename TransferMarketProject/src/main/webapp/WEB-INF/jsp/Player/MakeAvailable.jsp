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
  	<c:choose>
	    <c:when test="${message == '1'}">
	  		Not applicable either you are part of team or you have already placed a bid 
	    </c:when>    
	    <c:otherwise>
	     	<form action="apply" method="Post">
	     		Bid Last Date
	     		<input type="date" name="bidLastDate" required>
	     		<br>
	     		Base Amount
	     		<input type="text" name="baseAmount">
	     		<br>
	     		<input type="submit" value="Apply for bid">
	     	</form>        
	    </c:otherwise>
	</c:choose>
</body>
</html>