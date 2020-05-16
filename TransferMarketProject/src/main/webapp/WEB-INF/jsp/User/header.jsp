<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page import="transfermarketproject.Model.Manager"%>
<%@ page import="transfermarketproject.Model.Player"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
  <c:set var="element" value="${player}"/>
    <div>
    <span>
        Welcome <c:out value = "${element.getName()}"/>
    </span>
	<form action="Logout" method="Post">
		<input type="submit" value="Logout">
	</form>
    </div>
</body>
</html>