package ua.pizzadelivery.repository;

import java.util.List;

import ua.pizzadelivery.domain.Customer;

public interface CustomerRepository {
	Customer getCustomerById(Long id);	
	List<Customer> getAllCustomers();
	Long save(Customer customer);
	void update(Customer customer);
	Customer getCustomerByName(String name);
}
