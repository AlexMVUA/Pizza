package ua.pizzadelivery.repository;

import ua.pizzadelivery.domain.AccumulativeCard;

public interface AccumulativeCardRepository {
	
	AccumulativeCard getAccumulativeCardById(Long id);
	Long save(AccumulativeCard accumulativeCard);
	void update(AccumulativeCard accumulativeCard);
	Double getAccumulativeSumByCustomerName(String userName);
}
