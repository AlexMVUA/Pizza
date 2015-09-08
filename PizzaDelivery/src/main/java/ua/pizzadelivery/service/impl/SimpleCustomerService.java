package ua.pizzadelivery.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.pizzadelivery.domain.Customer;
import ua.pizzadelivery.repository.CustomerRepository;
import ua.pizzadelivery.service.CustomerService;

@Service
public class SimpleCustomerService implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.getAllCustomers();
	}

	@Override
	public Customer getCustomerById(Long id) {
		return customerRepository.getCustomerById(id);
	}

	@Override
	public Long registerCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer getCustomerByName(String name) {
		return customerRepository.getCustomerByName(name);
	}
	

}
