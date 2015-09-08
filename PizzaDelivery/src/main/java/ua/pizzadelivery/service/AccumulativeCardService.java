package ua.pizzadelivery.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import ua.pizzadelivery.domain.AccumulativeCard;
import ua.pizzadelivery.domain.Pizza;

@Service
public interface AccumulativeCardService {	
	
	AccumulativeCard getAccumulativeCardById(Long id);
	Double getAccumulativeCardSum(String userName);
	void increaseAccumulativeCardTotalSum(AccumulativeCard accumulativeCard,
			Map<Pizza, Integer> pizzas);
}
