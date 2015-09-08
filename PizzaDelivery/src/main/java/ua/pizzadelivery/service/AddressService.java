package ua.pizzadelivery.service;

import org.springframework.stereotype.Service;

import ua.pizzadelivery.domain.Address;

@Service
public interface AddressService {	
   
    Address getAddressById(Long id);
}
