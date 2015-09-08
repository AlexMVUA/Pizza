package ua.pizzadelivery.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ua.pizzadelivery.domain.User;
import ua.pizzadelivery.repository.UserRepository;

@Repository("userRepository")
public class JPAUserRepository implements UserRepository {
	
	@PersistenceContext(name = "HibernateMySQL")
	private EntityManager em;
	
	@Transactional
	@Override	
	public Long save(User user) {
		em.persist(user);
		return user.getId();
	}
	
	@Transactional
	@Override
	public User update(User user) {
		return em.merge(user);
	}

	@Override
	public User getUserById(Long id) {
		return em.find(User.class, id);
	}

	@Override
	public void remove(User user) {
		em.remove(em.contains(user) ? user : em.merge(user));
	}

}
