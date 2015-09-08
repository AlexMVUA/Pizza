package ua.pizzadelivery.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ua.pizzadelivery.domain.Pizza;
import ua.pizzadelivery.domain.PizzaType;
import ua.pizzadelivery.repository.PizzaRepository;

public class JPAPizzaRepositoryITTest extends ITRepositoryTestsTemplate {
	
	@Autowired
	private PizzaRepository pizzaRepository;
	
	@Test
	public void testSave() {
		Pizza pizza = new Pizza();
		pizza.setName("TEST PIZZA");
		pizza.setPrice(20.);
		pizza.setType(PizzaType.SEA);
		
		Long pizzaId = pizzaRepository.save(pizza);
		System.out.println("pizzaId" + pizzaId);
		assertNotNull(pizzaId);
	}
	
}
