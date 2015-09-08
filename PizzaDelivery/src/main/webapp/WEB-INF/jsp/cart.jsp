<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm order</title>
    </head>
    <body>
    <h3>Products in your basket</h3>
    
		 <table border="1">
            <thead><tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Type</th>
                    <th>Price</th>
                    <th>Amount</th>
                </tr></thead>

            <c:forEach var="entry" items="${basket.pizzas}">
                <tr>
                    <td>${entry.key.id}</td> 
                    <td>${entry.key.name}</td> 
                    <td>${entry.key.type}</td>
                    <td>${entry.key.price}</td>                 
					<td>${entry.value}</td>
                </tr>            
            </c:forEach>
        </table>
       <p>
       Total sum: ${basket.totalCost}
       
       </p>
      <form method="post" action="confirmOrder" >       
       
       	<br/>
       	  <sec:csrfInput/>
    		<input type="submit" value="Confirm Order" />
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