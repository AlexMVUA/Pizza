package ua.pizzadelivery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.pizzadelivery.domain.User;
import ua.pizzadelivery.repository.UserRepository;
import ua.pizzadelivery.service.UserService;

@Service
public class SimpleUserService implements UserService {
	
	@Autowired
    private UserRepository userRepository;
	
	@Override
	public Long addUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUserById(Long id) {
		return userRepository.getUserById(id);
	}

	@Override
	public User editUser(User user) {
		return userRepository.update(user);
	}

	@Override
	public void removeUser(User user) {
		userRepository.remove(user);
	}

}
