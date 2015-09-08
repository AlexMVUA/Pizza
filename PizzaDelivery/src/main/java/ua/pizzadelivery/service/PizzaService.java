package ua.pizzadelivery.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ua.pizzadelivery.domain.Pizza;

@Service
public interface PizzaService {
	
	List<Pizza> getAllPizzas();
    Long addPizza(Pizza pizza);
    Pizza getPizzaById(Long id);
	Pizza editPizza(Pizza pizza);
	void removePizza(Pizza pizza);
}
