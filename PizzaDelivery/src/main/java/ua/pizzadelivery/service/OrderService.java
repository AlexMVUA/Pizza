package ua.pizzadelivery.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import ua.pizzadelivery.domain.Customer;
import ua.pizzadelivery.domain.Order;
import ua.pizzadelivery.domain.Pizza;

@Service
public interface OrderService {
	Order placeNewOrder(Customer customer, Long ... pizzasID);
	List<Order> getAllOrders();	
	List<Order> getAllOrdersByCustomer(String name);
	Order getOrderById(Long id);
    Long placeOrder(Order order);
    Long placeOrder(Customer customer, Map<Pizza, Integer> pizzas);
}
