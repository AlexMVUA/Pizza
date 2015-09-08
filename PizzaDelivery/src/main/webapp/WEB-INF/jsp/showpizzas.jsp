<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pizzas List</title>
    </head>
    <body>

		<h3>Pizzeria Menu</h3>
		
        <table border="1">
            <thead><tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Type</th>
                    <th>Price</th>
                </tr></thead>

            <c:forEach var="pizza" items="${pizzas}">
                <tr>
                    <td>${pizza.id}</td> 
                    <td>${pizza.name}</td> 
                    <td>${pizza.type}</td>
                    <td>${pizza.price}</td>
                   <sec:authorize access="hasRole('ADMIN')"> 
                    <td>
                        <form method="get" action="edit" >
                            <input type="hidden" name="pizzaid" value="${pizza.id}" />
                            <input type="submit" value="Edit" />
                        </form>        
                    </td>
					<td>
                        <form method="get" action="remove" >
                            <input type="hidden" name="pizzaid" value="${pizza.id}" />
                            <input type="submit" value="Remove" />
                        </form>        
                    </td>                    
                   </sec:authorize> 
                </tr>            
            </c:forEach>
        </table>
        <sec:authorize access="hasRole('ADMIN')">
       		<a href="create"> Create new pizza </a> <br/>
		</sec:authorize>
		
		<sec:authorize access="hasAnyRole('ADMIN', 'USER')">
			<p>User name: ${name}<br>		
			   Roles: ${roles}
			</p>
		</sec:authorize>
		
		
		<sec:authorize access="isAnonymous()">					
			<p>Hello, Guest!<br/>
			<b>To make order you must register:</b></p>
		</sec:authorize>
		<table>
			<tr>			
				<td>
					<sec:authorize access="isAnonymous()">
						<form method="get" action="/PizzaDelivery/" >
            				<input type="submit" value="Home" />
       					</form>
					</sec:authorize>
				</td>
				<td><sec:authorize access="hasAnyRole('ADMIN', 'USER')">
						<form method="get" action="../order/" >
            				<input type="submit" value="Make Order" />
       					</form>
					</sec:authorize>					
				</td>
				<td>
					<sec:authorize access="isAnonymous()">					
						<form method="get" action="../registerCustomer" >
            				<input type="submit" value="Register" />
       					</form>
					</sec:authorize>
				</td>
				<td>
					<sec:authorize access="hasAnyRole('ADMIN', 'USER')">
						<c:url var="logoutUrl" value="/logout"/>
						<form method="post" action="${logoutUrl}" >             
             				<input type="submit" value="Log out" />
             				<input type="hidden" 
             					name="${_csrf.parameterName}"
             					value="${_csrf.token}" />
        	</form>
		</sec:authorize>
				
				</td>
		   </tr>
		</table>
		
		
    </body>
</html>