<%@page  language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
                    <td>
                        <form method="post" action="addPizzaToBasket" >
                            <input type="hidden" name="pizzaid" value="${pizza.id}" />
                            <input type="submit" value="Add to basket" />
                            <sec:csrfInput/>
                        </form>        
                    </td>

                </tr>            
            </c:forEach>
        </table>
            
        
        <c:if test="${basket != null}">
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
        
        <table>  
        	<tr>
        		<td>
        			<form method="post" action="clearBasket" >        	
        				<input type="submit" value="Clear Basket" />
        				<sec:csrfInput/>
        			</form>          		
        		</td>
        		<td width="75"></td>
        		<td>
        			<form method="post" action="previewOrder" >        	
        				<input type="submit" value="See basket and make order" />
        				<sec:csrfInput/>
        			</form>     
        		</td>
        	</tr>          
        
        </table>
        </c:if>

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