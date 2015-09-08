package ua.pizzadelivery.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ua.pizzadelivery.domain.Order;
import ua.pizzadelivery.repository.OrderRepository;

@Repository("orderRepository")
public class JPAOrderRepository implements OrderRepository {
	
	@PersistenceContext(name = "HibernateMySQL")
	private EntityManager em;
	
	@Transactional
	@Override
	public Long save(Order order) {
		em.persist(order);
		return order.getId();
	}

	@Override
	public Order getOrderById(Long id) {
		return em.find(Order.class, id);
	}
	
	@Transactional
	@Override
	public List<Order> getAllOrders() {
		TypedQuery<Order> query 
			= em.createQuery("select o from Order o", Order.class);
		List<Order> list = query.getResultList();		
		return list;
	}

	@Override
	@Transactional
	public void update(Order order) {
		em.merge(order);
	}

	@Override
	public List<Order> getAllOrdersByCustomer(String name) {
			TypedQuery<Order> query 
				= em.createQuery("select o from Order o where o.customer.name=:name", Order.class);
			query.setParameter("name", name);
		List<Order> list = query.getResultList();
		return list;
	}

}
