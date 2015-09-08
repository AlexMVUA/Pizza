package ua.pizzadelivery.service;

import org.springframework.stereotype.Service;

import ua.pizzadelivery.domain.User;

@Service
public interface UserService {
	
	 Long addUser(User user);
	 User getUserById(Long id);
	 User editUser(User user);
	 void removeUser(User user);
}
