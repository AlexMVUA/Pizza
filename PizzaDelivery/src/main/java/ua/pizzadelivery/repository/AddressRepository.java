package ua.pizzadelivery.repository;

import ua.pizzadelivery.domain.Address;

public interface AddressRepository {
	
	Address getAddressById(Long id);
	Long save(Address address);
	void update(Address address);
}
