package ua.pizzadelivery.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

import ua.pizzadelivery.domain.Customer;
import ua.pizzadelivery.domain.Order;
import ua.pizzadelivery.domain.Pizza;
import ua.pizzadelivery.repository.OrderRepository;
import ua.pizzadelivery.repository.PizzaRepository;
import ua.pizzadelivery.service.OrderService;

@Service(/*value = "orderService"*/)
public class SimpleOrderService implements OrderService {
	
	@Autowired
	private PizzaRepository pizzaRepository ;
	@Autowired
	private OrderRepository orderRepository;
	
	
	
	@Override
	//@Benchmark
	public Order placeNewOrder(Customer customer, Long ... pizzasID) {
		List<Pizza> pizzas = new ArrayList<>();
		Map<Pizza, Integer> pizzaMap = new HashMap<>();
		
		for(Long id : pizzasID){
			pizzas.add(pizzaRepository.getPizzaById(id));  // get Pizza from predifined in-memory list
		}
		
		for (Long id : pizzasID) {
			Pizza pizza = pizzaRepository.getPizzaById(id);
			if (pizzaMap.containsKey(pizza)) {
				Integer quantity = pizzaMap.get(pizza);
				pizzaMap.replace(pizza, quantity, quantity + 1);
			} else {
				pizzaMap.put(pizza, 1);
			}
		}
		
		Order newOrder = new Order();
		//Order newOrder = getNewOrder();
		newOrder.setCustomer(customer);
		newOrder.setList(pizzas);	
		newOrder.setPizzas(pizzaMap);
		orderRepository.save(newOrder);  // set Order Id and save Order to in-memory list
		return newOrder;
	}
	
	/*
	@Lookup(value = "order")
	protected Order getNewOrder() {
		return null;
	}
*/

	@Override
	public Order getOrderById(Long id) {
		return orderRepository.getOrderById(id);
	}

	@Override	
	public Long placeOrder(Order order) {

		if (order.getPizzas().isEmpty()) {
			throw new IllegalArgumentException();
		}

		return orderRepository.save(order);
	}
	
	@Override	
	public Long placeOrder(Customer customer, Map<Pizza, Integer> pizzas) {
		Order newOrder = new Order(customer, pizzas);		
		return orderRepository.save(newOrder);
	}
	
	/*
	@Override	
	public Order createNewOrder(Customer customer) {
		Order order = new Order();
		order.setCustomer(customer);		
		return order;
	}
	*/
	
	@Override
	public List<Order> getAllOrders() {
		return orderRepository.getAllOrders();
	}

	@Override
	public List<Order> getAllOrdersByCustomer(String name) {
		return orderRepository.getAllOrdersByCustomer(name);
	}


}
