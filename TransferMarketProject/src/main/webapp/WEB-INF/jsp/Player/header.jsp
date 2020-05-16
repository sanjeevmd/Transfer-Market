<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page import="transfermarketproject.Model.Manager"%>
<%@ page import="transfermarketproject.Model.Player"%>
<%@ page import="transfermarketproject.Model.Watch"%>
<%@ page import="transfermarketproject.Model.Team"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>

  	<c:set var="Manager" value='<%=(Player)session.getAttribute("user")%>'/>
    <div>
    <span>
        Welcome <c:out value = "${Manager.getName()}"/>
    </span>
	<form action="Logout" method="Post">
		<input type="submit" value="Logout">
	</form>
	<form action="/TransferMarketProject/Index/Login" method="Post">
			<input type="hidden" name="userName" value="${Manager.getUserId()}">
			<input type="hidden" name="password" value="${Manager.getPassword()}">
			<input type="hidden" name="role" value="Player">
			<input type="submit" value="Back to Login">
	</form>
    </div>
</body>
</html>