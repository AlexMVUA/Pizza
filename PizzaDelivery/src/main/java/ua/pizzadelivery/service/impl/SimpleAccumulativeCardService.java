package ua.pizzadelivery.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.pizzadelivery.domain.AccumulativeCard;
import ua.pizzadelivery.domain.Address;
import ua.pizzadelivery.domain.Pizza;
import ua.pizzadelivery.domain.TotalOrderCostCalculator;
import ua.pizzadelivery.repository.AccumulativeCardRepository;
import ua.pizzadelivery.service.AccumulativeCardService;

@Service
public class SimpleAccumulativeCardService implements AccumulativeCardService {
	
	@Autowired
	AccumulativeCardRepository accumulativeCardRepository;
	
	@Autowired
	TotalOrderCostCalculator totalOrderCostCalculator;
	
	@Override
	public AccumulativeCard getAccumulativeCardById(Long id) {
		return accumulativeCardRepository.getAccumulativeCardById(id);
	}

	@Override
	public Double getAccumulativeCardSum(String userName) {
		return accumulativeCardRepository.getAccumulativeSumByCustomerName(userName);
	}

	@Override
	public void increaseAccumulativeCardTotalSum(
			AccumulativeCard accumulativeCard, Map<Pizza, Integer> pizzas) {

		Double totalPrice = totalOrderCostCalculator.calculateTotalOrderPrice(
								pizzas, 
								accumulativeCard.getAccumulativeSum());
		
		accumulativeCard.increaseAccumulativeSum(totalPrice);
		
		accumulativeCardRepository.update(accumulativeCard);
		
	}

}
