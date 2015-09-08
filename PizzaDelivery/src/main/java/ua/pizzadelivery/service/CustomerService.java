package ua.pizzadelivery.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ua.pizzadelivery.domain.Customer;

@Service
public interface CustomerService {
	
	List<Customer> getAllCustomers();	
	Customer getCustomerById(Long id);
	Long registerCustomer(Customer customer);
	Customer getCustomerByName(String name);
}
