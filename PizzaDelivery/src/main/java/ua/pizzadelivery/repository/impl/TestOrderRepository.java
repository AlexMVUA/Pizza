package ua.pizzadelivery.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import ua.pizzadelivery.domain.Order;
import ua.pizzadelivery.infrastructure.Benchmark;
import ua.pizzadelivery.repository.OrderRepository;

@Repository
public class TestOrderRepository implements OrderRepository {
	
	private List<Order> orders = new ArrayList<>();
	
	/* (non-Javadoc)
	 * @see ua.epam.rd.pizzadeliveryservice.repository.OrderRepository#saveOrder(ua.epam.rd.pizzadeliveryservice.domain.Order)
	 */
	@Override
	@Benchmark
	public Long save(Order order) {			
		orders.add(order);
		return order.getId();
	}

	@Override
	public Order getOrderById(Long id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Order> getAllOrders() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void update(Order order) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Order> getAllOrdersByCustomer(String name) {
		throw new UnsupportedOperationException();
	}
}
