<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>PizzaDeliveryApp</title>
</head>
    <body>
        <h1>Hello! Welcome to our Pizzeria!</h1>
    <sec:authorize access="hasRole('ANONYMOUS')">	    
		<a href="jsp/registerCustomer">Registration</a> 
	</sec:authorize>
	<a href="jsp/pizza/">See menu</a> 	
	<sec:authorize access="hasRole('ANONYMOUS')">	
		<a href="login">Login</a> 
	</sec:authorize>
	<sec:authorize access="hasAnyRole('ADMIN', 'USER')">
		<a href="jsp/order/viewHistory">My orders history</a>        			  
       	<a href="login?logout">Log out</a> 
	</sec:authorize>
	
	
    </body>
</html>