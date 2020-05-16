<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page import="transfermarketproject.Model.Manager"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transfer Market</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/User/header.jsp" %>
	<br>	
	<form action="/TransferMarketProject/Manager/ViewTeam" method="Get">
		<input type="submit" value="View Team">
	</form>
	<br>
	<form action="/TransferMarketProject/Manager/ViewAvailablePlayers" method="Get">
		<input type="submit" value="Players Available on Bid">
	</form>
	<br>
	<form action="/TransferMarketProject/Manager/CurrentPlayersBid" method="Get">
		<input type="submit" value="Current Players on Bid">
	</form>
	<br>
	<form action="/TransferMarketProject/Manager/WatchPlayers" method="Get">
		<input type="submit" value="Watch">
	</form>
	<br>
	<form action="/TransferMarketProject/Manager/SearchPlayers" method="Get">
		<input type="submit" value="Search">
	</form>
	<br>
	<form action="/TransferMarketProject/Manager/Statistics" method="Get">
		<input type="submit" value="Statistics">
	</form>	
</body>
</html>