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

        <table border="1">
            <thead><tr>
                    <th>ID</th>
                    <th>Date</th>
                    <th>Customer</th>
                    <th>Order cost</th>
                    <th>Pizzas and Quantity</th>
                </tr></thead>

            <c:forEach var="order" items="${orders}">
                <tr>
                    <td>${order.id}</td> 
                    <td>${order.date}</td> 
                    <td>${order.customer.name} -                   
                    ${order.customer.accumulativeCard.address.city}, 
                    ${order.customer.accumulativeCard.address.street} 
                    ${order.customer.accumulativeCard.address.building} , ap. 
                    ${order.customer.accumulativeCard.address.apartment} <br/>
                    
                    </td>
                    <td>${order.totalCost}</td>
                    <td>
                    	<table>
                    		<c:forEach var="entry" items="${order.pizzas}">
                    		<tr>
                    			<td>${entry.key.name}
                    			(${entry.key.type}) - 
                    			${entry.key.price} USD
                    			</td>
                    			<td>${entry.value}pcs.</td>
                    		</tr>
                    		</c:forEach>
                    	</table>
                    </td>
                   
                </tr>            
            </c:forEach>
        </table>
       <br/>		
		<c:url var="logoutUrl" value="/logout"/>
		
		<table>
			<tr>
				<td>
				  <form method="post" action="${logoutUrl}" >             
             		<input type="submit" value="Log out" width="40" />
             		<input type="hidden" 
             			   name="${_csrf.parameterName}"
             			   value="${_csrf.token}" />
       			  </form>
        		</td>
        		<td width="25"></td>
        		<td>
        		  <form method="get" action="../admin/" >
            		<input type="submit" value="Home" width="40" />
            		
       			  </form>
				</td>
			</tr>
		</table>
    </body>
</html>