package ua.pizzadelivery.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ua.pizzadelivery.domain.Pizza;
import ua.pizzadelivery.repository.PizzaRepository;

@Repository("pizzaRepository")
public class JPAPizzaRepository implements PizzaRepository {
	
	@PersistenceContext(name = "HibernateMySQL")
	private EntityManager em;
	
	@Override
	//@Transactional
	public List<Pizza> getAllPizzas() {
		TypedQuery<Pizza> query 
			= em.createQuery("select p from Pizza p", Pizza.class);
		return query.getResultList();
	}	
	
	@Override
	public Pizza getPizzaById(Long id) {
		return em.find(Pizza.class, id);
	}

	@Override
	@Transactional
	public Long save(Pizza pizza) {
		em.persist(pizza);	
		return pizza.getId();
	}

	@Override
	@Transactional
	public Pizza update(Pizza pizza) {
		return em.merge(pizza);
	}
		
	@Override
	@Transactional
	public void remove(Pizza pizza) {
		em.remove(em.contains(pizza) ? pizza : em.merge(pizza));		
	}
}
