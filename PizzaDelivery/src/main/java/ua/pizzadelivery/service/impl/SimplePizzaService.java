package ua.pizzadelivery.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.pizzadelivery.domain.Pizza;
import ua.pizzadelivery.repository.PizzaRepository;
import ua.pizzadelivery.service.PizzaService;

@Service
public class SimplePizzaService implements PizzaService {

	@Autowired
    private PizzaRepository pizzaRepository;
    
    @Override
    public List<Pizza> getAllPizzas() {
        return pizzaRepository.getAllPizzas();
    }

    @Override
    public Long addPizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    @Override
    public Pizza getPizzaById(Long id) {
        return pizzaRepository.getPizzaById(id);
    }

	@Override
	public Pizza editPizza(Pizza pizza) {
		return pizzaRepository.update(pizza);
	}

	@Override
	public void removePizza(Pizza pizza) {
		pizzaRepository.remove(pizza);
	}
}
