package ua.pizzadelivery.repository;

import java.util.List;

import ua.pizzadelivery.domain.Pizza;

public interface PizzaRepository {

	Pizza getPizzaById(Long id);
	List<Pizza> getAllPizzas();
	Long save(Pizza pizza);
	Pizza update(Pizza pizza);
	void remove(Pizza pizza);
}
