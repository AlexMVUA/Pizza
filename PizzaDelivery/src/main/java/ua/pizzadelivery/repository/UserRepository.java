package ua.pizzadelivery.repository;

import ua.pizzadelivery.domain.User;

public interface UserRepository {

	Long save(User user);
	User update(User user);
	User getUserById(Long id);
	void remove(User user);
	
}
