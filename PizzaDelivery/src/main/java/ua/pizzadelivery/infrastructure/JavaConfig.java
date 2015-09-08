package ua.pizzadelivery.infrastructure;

import java.util.HashMap;
import java.util.Map;

import ua.pizzadelivery.repository.impl.TestOrderRepository;
import ua.pizzadelivery.repository.impl.TestPizzaRepository;
import ua.pizzadelivery.service.impl.SimpleOrderService;

public class JavaConfig implements Config {
	
	private final Map<String, Class<?>> map = new HashMap<>();
	{
		map.put("orderRepository", TestOrderRepository.class);
		map.put("pizzaRepository", TestPizzaRepository.class);		
		map.put("orderService", SimpleOrderService.class);
	}
	@Override
	public Class<?> getImplementation(String beanName) {
		return map.get(beanName);
	}

}
