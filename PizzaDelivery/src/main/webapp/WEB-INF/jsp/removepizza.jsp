<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Remove pizza</title>
    </head>
    <body>
    <h3>Remove pizza</h3>
    
        <form action="remove" method="post">
                   <input type="hidden" name="id" value="${pizza.id}"/>
            Name : ${pizza.name}<br/>
            Type : ${pizza.type}<br/>
            Price : ${pizza.price}<br/>
            <sec:csrfInput />
            <input type="submit" value="Confirm remove"/><br/>
        </form>
        
        <table>
			<tr>
				<td>
				<c:url var="logoutUrl" value="/logout"/>
				  <form method="post" action="${logoutUrl}" >             
             		<input type="submit" value="Log out"/>
             		<input type="hidden" 
             			   name="${_csrf.parameterName}"
             			   value="${_csrf.token}" />
       			  </form>
        		</td>
        		<td width="25"></td>
        		<td>
        		  <form method="get" action="/PizzaDelivery/" >
            		<input type="submit" value="Home" width="40" />
            		
       			  </form>
				</td>
			</tr>
		</table>
        
    </body>
    
    
</html>