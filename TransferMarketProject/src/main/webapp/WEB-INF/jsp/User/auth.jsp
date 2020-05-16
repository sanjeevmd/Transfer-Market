<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="ISO-8859-1">
<title>Transfer Market</title>
</head>
<style>
.alignCenter {
	text-align: center;
}
</style>
<body>
	<div class="alignCenter">Transfer Market</div>
	<form action="Login" method="Post" class="alignCenter">
		<br>
		<input type="text" name="userName" id="userName" placeholder="Enter email ID" required>
		<br><br>
		<input type="password" name="password" id="password" placeholder="Enter password" required>
		<br><br>
		<input type="radio" id="player" name="role" value="player" required>
		<label for="male">Player</label>
		<input type="radio" id="manager" name="role" value="manager">
		<label for="female">Manager</label>
		<br>
		    <c:set var="element" value="${message}"/>
			<c:out value = "${element}"/>
		<br>
		<input type="submit" name="submit" value="Login"> 
	</form>
	<br>
	<form action="Register" method="Get" class="alignCenter">
		<input type="submit" name="submit" value="Register"> 
	</form>
</body>
</html>