<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page import="transfermarketproject.Model.Team"%>
        
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transfer Market</title>
<style>
.alignCenter {
	text-align: center;
}
</style>
</head>
<body>
	<div class="alignCenter">Transfer Market</div>
	<br>
      <form method="POST" action="Save" class="alignCenter">
		<input type="text" name="name" placeholder="Name">
		<br><br>
		<input type="password" name="password" placeholder="Password" required>
		<br><br>
		<input type="text" name="userID" placeholder="userID" required>	
		<br>
		<br>
		<input type="radio" id="player" name="role" value="player" required>
		<label for="male">Player</label>
		<input type="radio" id="manager" name="role" value="manager">
		<label for="female">Manager</label>
		<br><br>
		<select name='lists'>  
		  	 <option id="None" value="None">None</option>
  			 <c:forEach var="list" items="${teams}">
    		   <option id="${list.getId()}" value="${list.getId()}">${list.getTeamName()}</option>   
 			 </c:forEach>
		</select>
		<br>    
		<c:set var="element" value="${message}"/>
		<c:out value = "${element}"/>
			<br>
		<input type = "submit" value = "Register">
	  </form>
		<br>
		<form action="Logout" method="Post" class="alignCenter">
			<input type="submit" value="Back to Login">
		</form>
</body>
</html>