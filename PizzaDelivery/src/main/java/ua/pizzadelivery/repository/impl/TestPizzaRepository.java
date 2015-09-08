package ua.pizzadelivery.repository.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import ua.pizzadelivery.domain.Pizza;
import ua.pizzadelivery.domain.PizzaType;
import ua.pizzadelivery.repository.PizzaRepository;

public class TestPizzaRepository implements PizzaRepository {
	
	private List<Pizza> pizzas;
	
	/* (non-Javadoc)
	 * @see ua.epam.rd.pizzadeliveryservice.repository.PizzaRepository#getPizzaByID(java.lang.Integer)
	 */
	@Override
	//@Benchmark
	public Pizza getPizzaById(Long id) {		
		for (Pizza p : pizzas) {
			if(p.getId().equals(id)) {
				return p;
			}
		}		
		return null;
	}
	
	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}
	
	@PostConstruct
	public void init() {
		pizzas = Arrays.asList(			
			new Pizza(2L, "Fruit", 15., PizzaType.VEGETARIAN),
			new Pizza(3L, "TunePizza", 25., PizzaType.SEA),
			new Pizza(1L, "Saliami", 22.3, PizzaType.MEAT)			
		);		
	}

	@Override
	public List<Pizza> getAllPizzas() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long save(Pizza pizza) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Pizza update(Pizza pizza) {
		throw new UnsupportedOperationException();		
	}

	@Override
	public void remove(Pizza pizza) {
		throw new UnsupportedOperationException();		
	}

	
}
