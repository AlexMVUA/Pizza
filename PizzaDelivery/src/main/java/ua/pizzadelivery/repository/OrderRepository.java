package ua.pizzadelivery.repository;

import java.util.List;

import ua.pizzadelivery.domain.Order;

public interface OrderRepository {
    
    List<Order> getAllOrders();    
    Order getOrderById(Long id);   
    Long save(Order order);	
    void update(Order order);
	List<Order> getAllOrdersByCustomer(String name);
    
}