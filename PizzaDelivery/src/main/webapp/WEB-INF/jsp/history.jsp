<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pizzas List</title>
    </head>
    <body>
	
	<h3>Orders history</h3>
	
	<c:if test="${empty orders}">
		<h4>You haven't made any order yet</h4>
	</c:if>
	<p>Total sum on accumulative card : 
		<b>${orders[0].customer.accumulativeCard.accumulativeSum}</b>
	</p>
	
	 <c:forEach var="order" items="${orders}">
        <table border="1" width="500">
            <thead>
            <tr bgcolor="#F0F0F0">
            	<td colspan="1">
            		${order.date}
            	</td>
            </tr>
            <tr>
                    <th width="40%">Pizza name</th>
                    <th width="25%">Pizza type</th>
                    <th width="15%">Price</th>
                    <th width="20%">Quantity</th>                   
                </tr></thead>
		   <c:set var="totalPizzaAmount" scope="page" value="0"/>
		   <c:set var="orderWithoutDiscount" scope="page" value="0"/>
           <c:forEach var="entry" items="${order.pizzas}">
                <tr>
                    <td>${entry.key.name}</td>
                    <td>${entry.key.type}</td>
                    <td>${entry.key.price}</td>
                    <td>${entry.value}</td>	
          <c:set var="totalPizzaAmount" 
          		 value="${totalPizzaAmount + entry.value}" />     
          <c:set var="orderWithoutDiscount"           		 
          		 value="${orderWithoutDiscount + entry.key.price * entry.value}"/>
                </tr>    
               </c:forEach>        
          	<tr bgcolor="#F0F0F0">
            	<td colspan="4">            		
            		<table width="100%">
            			<tr>
            				<td width="50%">Total cost(with discount): ${order.totalCost}</td>
            				<td width="10%"/>
            				<td width="40%">Pizzas quantity: ${totalPizzaAmount}</td>
            				
            			</tr> 
            			<tr>
            				<td>Total cost(without discount): ${orderWithoutDiscount}</td>
            				<td />
            				<td>Discount: 
            				<fmt:formatNumber 
            					value="${orderWithoutDiscount-order.totalCost}"
            					maxFractionDigits="1" />
            				
            				</td>            				
            			</tr>            		
            		</table>          		
            	</td>
            </tr>
        </table>
        <br/>
        </c:forEach>
       
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
        		  <form method="get" action="/PizzaDelivery/" >
            		<input type="submit" value="Home" width="40" />
            		
       			  </form>
				</td>
			</tr>
		</table>
    </body>
</html>