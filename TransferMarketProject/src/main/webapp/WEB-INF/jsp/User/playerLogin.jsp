<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page import="transfermarketproject.Model.Player"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transfer Market</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/User/header.jsp" %>
	<br>
	<form action="/TransferMarketProject/Player/MakeAvailable" method="Get">
		<input type="submit" value="Apply for Bid">
	</form>
	<form action="/TransferMarketProject/Player/ViewBids" method="Get">
		<input type="submit" value="View Bids">
	</form>
</body>
</html>