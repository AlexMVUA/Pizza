<%@page language="java"  contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New customer registration</title>
    </head>
    <body>
    
    
    <h3>Please enter your data for registration</h3>
      <form method="post" action="registerCustomer" >       
       <table border="1" style="border-spacing: 5px;">
            
            <tr>
            	<td colspan="2" align="center">Input name (login)</td>
            </tr>
            <tr>
                <td>Name</td>
                <td><input type="text" name="name"/></td>                   
            </tr>
            <tr >
            	<td colspan="2" align="center">Input delivery address</td>
            </tr>
			<tr>
                <td>City</td>
                <td><input type="text" name="city"/></td>                   
            </tr>
            <tr>
                <td>Street</td>
                <td><input type="text" name="street"/></td>                   
            </tr>
            <tr>
                <td>Building</td>
                <td><input type="text" name="building"/></td>                   
            </tr>
            <tr>
                <td>Apartment</td>
                <td><input type="text" name="apartment"/></td>                   
            </tr>
            
            <tr >
            	<td colspan="2" align="center">Input password</td>
            </tr>
             <tr>
                <td>Password</td>
                <td><input type="password" name="password"/></td>                   
            </tr>            
             
            
        </table>
       	<br/>
       	  <sec:csrfInput/>
    		<input type="submit" value="Confirm Registration" />
     	</form>     
       	
       	<table>
			<tr>
				<td>
				<c:url var="logoutUrl" value="/logout"/>
				  <form method="post" action="${logoutUrl}" >             
             		<input type="submit" value="Log out" width="40" />
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