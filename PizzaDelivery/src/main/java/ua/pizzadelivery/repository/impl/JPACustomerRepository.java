package ua.pizzadelivery.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ua.pizzadelivery.domain.Customer;
import ua.pizzadelivery.repository.CustomerRepository;

@Repository("customerRepository")
public class JPACustomerRepository implements CustomerRepository {
	
	@PersistenceContext(name = "HibernateMySQL")
	private EntityManager em;
	
	@Override
	public Customer getCustomerById(Long id) {
		return em.find(Customer.class, id);
	}

	@Override	
	public List<Customer> getAllCustomers() {
		TypedQuery<Customer> query 
			= em.createQuery("select c from Customer c", Customer.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public Long save(Customer customer) {
		em.persist(customer);		
		return customer.getId();
	}
	
	@Transactional
	@Override
	public void update(Customer customer) {
		em.merge(customer);
	}
	
	@Transactional
	@Override
	public Customer getCustomerByName(String name) {
		TypedQuery<Customer> query 
			= em.createQuery("select c from Customer c where  c.name = :name", Customer.class);
		query.setParameter("name", name);
		Customer customer = query.getSingleResult();
		return customer;
	}

}
