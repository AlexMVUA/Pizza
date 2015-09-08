package ua.pizzadelivery.domain;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class TotalOrderCostCalculator {
	private final static Integer MAXIMUM_PIZZA_QUANTITY = 10;
	private final static Double OVERALL_DISCOUNT_FROM_ACUM_CARD_SUM = 0.1;
	private final static Double MAXIMUM_DISCOUNT = 0.5;
	
	public TotalOrderCostCalculator() {
		
	}
	
	public double calculateTotalOrderPrice(Map<Pizza, Integer> pizzas) {		
		double result = 0;		
		int totalPizzaQuantity = 0;		
		double orderMaximumPizzaPrice = 0;
		
		if (pizzas == null) {
			throw new NullPointerException();
		}
		
		if (pizzas.size() < 1) {
			throw new IllegalArgumentException("Order can't be without pizza");
		}
		
		for(Map.Entry<Pizza, Integer> entry : pizzas.entrySet()) {			
			if (entry.getKey() == null) {
				throw new NullPointerException("Order contain pizza with 'NULL' value");
			}
			
			double pizzaPrice = entry.getKey().getPrice();
			int pizzaQuantity = entry.getValue();
			
			if (pizzaQuantity <= 0) {
				throw new IllegalArgumentException(
						"Order can't contain with zero or negative value");
			}
			
			if (pizzaPrice > orderMaximumPizzaPrice) {
				orderMaximumPizzaPrice = pizzaPrice;
			}
			
			totalPizzaQuantity += pizzaQuantity;			
			result += pizzaQuantity * pizzaPrice;
		}
		
		if (totalPizzaQuantity > MAXIMUM_PIZZA_QUANTITY) {
			throw new IllegalArgumentException(
					"Order can't contain more then 10 pizzas");
		}
		
		if (totalPizzaQuantity > 4) {
			result = result - 0.3 * orderMaximumPizzaPrice;
		}
		
		return result;
	}
	
	public double calculateTotalOrderPrice(
			Map<Pizza, Integer> pizzas,
			Double accumulativeCardSum) {		
		double result = 0;		
		int totalPizzaQuantity = 0;
		
		if (pizzas == null) {
			throw new NullPointerException();
		}
		
		if (pizzas.size() < 1) {
			throw new IllegalArgumentException("Order can't be without pizza");
		}
		
		for(Map.Entry<Pizza, Integer> entry : pizzas.entrySet()) {			
			if (entry.getKey() == null) {
				throw new NullPointerException("Order contain pizza with 'NULL' value");
			}
			
			double pizzaPrice = entry.getKey().getPrice();
			int pizzaQuantity = entry.getValue();
			
			if (pizzaQuantity <= 0) {
				throw new IllegalArgumentException(
						"Order can't contain with zero or negative value");
			}
						
			totalPizzaQuantity += pizzaQuantity;			
			result += pizzaQuantity * pizzaPrice;
		}
		
		if (totalPizzaQuantity > MAXIMUM_PIZZA_QUANTITY) {
			throw new IllegalArgumentException(
					"Order can't contain more then 10 pizzas");
		}
		
		if (accumulativeCardSum == null) {
			accumulativeCardSum = 0.;
		}
		
		if (accumulativeCardSum < 0) {
			throw new IllegalArgumentException(
					"AccumulativeCard can't contain negative accumulative sum");
		}
		
		Double resultWithMaximumDiscount = result * MAXIMUM_DISCOUNT;
		
		result -= accumulativeCardSum*OVERALL_DISCOUNT_FROM_ACUM_CARD_SUM;		
		Double possibleTotalCostWithDiscountFromAccumulativeCard = result;
				
		if (possibleTotalCostWithDiscountFromAccumulativeCard
				< resultWithMaximumDiscount) {
			return resultWithMaximumDiscount;
		}		
		
		return possibleTotalCostWithDiscountFromAccumulativeCard;
	}	
}
