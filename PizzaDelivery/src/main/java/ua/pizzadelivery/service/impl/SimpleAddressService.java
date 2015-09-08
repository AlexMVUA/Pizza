package ua.pizzadelivery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.pizzadelivery.domain.Address;
import ua.pizzadelivery.repository.AddressRepository;
import ua.pizzadelivery.service.AddressService;

@Service
public class SimpleAddressService implements AddressService {
	
	@Autowired
    private AddressRepository addressRepository;
	
	@Override
	public Address getAddressById(Long id) {
		return addressRepository.getAddressById(id);
	}

}
