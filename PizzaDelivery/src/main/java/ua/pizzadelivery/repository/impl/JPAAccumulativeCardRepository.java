package ua.pizzadelivery.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ua.pizzadelivery.domain.AccumulativeCard;
import ua.pizzadelivery.domain.Customer;
import ua.pizzadelivery.repository.AccumulativeCardRepository;

@Repository("accumulativeCard")
public class JPAAccumulativeCardRepository implements AccumulativeCardRepository {
	
	@PersistenceContext(name = "HibernateMySQL")
	private EntityManager em;
	
	@Override
	public AccumulativeCard getAccumulativeCardById(Long id) {
		return em.find(AccumulativeCard.class, id);
	}
	
	@Transactional
	@Override
	public Long save(AccumulativeCard accumulativeCard) {
		em.persist(accumulativeCard);	
		return accumulativeCard.getId();
	}

	@Transactional
	@Override
	public void update(AccumulativeCard accumulativeCard) {
		em.merge(accumulativeCard);		
	}
	
	@Transactional
	@Override
	public Double getAccumulativeSumByCustomerName(String userName) {
		TypedQuery<Customer> query 
		= em.createQuery(
				"select c from Customer c "
				+ " where c.name = :name",
				Customer.class);
	query.setParameter("name", userName);
	Double accumulativeSum = query.getSingleResult().getAccumulativeCard().getAccumulativeSum();
	return accumulativeSum;
	}

}
