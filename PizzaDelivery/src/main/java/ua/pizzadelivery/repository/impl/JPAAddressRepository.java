package ua.pizzadelivery.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ua.pizzadelivery.domain.Address;
import ua.pizzadelivery.repository.AddressRepository;

@Repository("addressRepository")
public class JPAAddressRepository implements AddressRepository {
	
	@PersistenceContext(name = "HibernateMySQL")
	private EntityManager em;
	
	@Override
	public Address getAddressById(Long id) {
		return em.find(Address.class, id);
	}
	
	@Override
	@Transactional
	public Long save(Address address) {
		em.persist(address);
		return address.getId();
	}
	
	@Transactional
	@Override
	public void update(Address address) {
		em.merge(address);
	}

}
