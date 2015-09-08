package ua.pizzadelivery.domain;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ua.pizzadelivery.domain.Pizza;
import ua.pizzadelivery.domain.PizzaType;
import ua.pizzadelivery.domain.TotalOrderCostCalculator;
import ua.pizzadelivery.service.PizzaService;

@RunWith(MockitoJUnitRunner.class)
public class TotalOrderCostCalculatorTest {
	
	private static double DELTA= 0.005;
	
	@Mock
	private PizzaService pizzaService;	
		
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateTotalOrderPriceThrowExceptionOrderWithoutPizza() {		
		Map<Pizza, Integer> pizzas = new HashMap<>();
		TotalOrderCostCalculator totalOrderCostCalculator = new TotalOrderCostCalculator();
		totalOrderCostCalculator.calculateTotalOrderPrice(pizzas);						
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateTotalOrderPriceThrowExceptionQuantityPizzaExceedMaximum() {			
		Map<Pizza, Integer> pizzas = new HashMap<>();
		int exceedMaximumQuantity = 11;
		double pizzaPrice = 22.3;
		pizzas.put(new Pizza(1L, "Margarita", pizzaPrice, PizzaType.MEAT), exceedMaximumQuantity);
		TotalOrderCostCalculator totalOrderCostCalculator = new TotalOrderCostCalculator();
		totalOrderCostCalculator.calculateTotalOrderPrice(pizzas);						
	}
	
	@Test
	public void testCalculateTotalOrderPriceOrderOnePizza() {
		//Preparing input data and 
		Map<Pizza, Integer> pizzas = new HashMap<>();
		double pizzaPrice = 22.3;
		pizzas.put(new Pizza(1L, "Margarita", pizzaPrice, PizzaType.MEAT), 1);
		double expectedPrice = 22.3;
		
		//invoking testing method
		TotalOrderCostCalculator calculator = new TotalOrderCostCalculator();
		double price = calculator.calculateTotalOrderPrice(pizzas);
				
		assertEquals(expectedPrice, price, DELTA);		
	}
		
	@Test
	public void testCalculateTotalOrderPriceOrderTenPizza() {
		//Preparing input data and 
		Map<Pizza, Integer> pizzas = new HashMap<>();
		double pizzaPrice = 10;
		int quantity = 10;
		pizzas.put(new Pizza(1L, "Margarita", pizzaPrice, PizzaType.MEAT), quantity);
		double expectedPrice = 97;
		
		//invoking testing method
		TotalOrderCostCalculator calculator = new TotalOrderCostCalculator();
		double price = calculator.calculateTotalOrderPrice(pizzas);
				
		assertEquals(expectedPrice, price, DELTA);		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateTotalOrderPriceThrowExceptionOrderContainNegativeQuantity() {			
		Map<Pizza, Integer> pizzas = new HashMap<>();
		int negativeQuantity = -1;
		double pizzaPrice = 22.3;
		pizzas.put(new Pizza(1L, "Margarita", pizzaPrice, PizzaType.MEAT), negativeQuantity);
		TotalOrderCostCalculator totalOrderCostCalculator = new TotalOrderCostCalculator();
		totalOrderCostCalculator.calculateTotalOrderPrice(pizzas);						
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateTotalOrderPriceThrowExceptionOrderContainZeroQuantity() {			
		Map<Pizza, Integer> pizzas = new HashMap<>();
		int zeroQuantity = 0;
		double pizzaPrice = 22.3;
		pizzas.put(new Pizza(1L, "Margarita", pizzaPrice, PizzaType.MEAT), zeroQuantity);
		TotalOrderCostCalculator totalOrderCostCalculator = new TotalOrderCostCalculator();
		totalOrderCostCalculator.calculateTotalOrderPrice(pizzas);						
	}
	
	@Test(expected = NullPointerException.class)
	public void testCalculateTotalOrderPriceThrowExceptionOrderContainPizzaWithNull() {			
		Map<Pizza, Integer> pizzas = new HashMap<>();		
		pizzas.put(null, 1);
		TotalOrderCostCalculator totalOrderCostCalculator = new TotalOrderCostCalculator();
		totalOrderCostCalculator.calculateTotalOrderPrice(pizzas);						
	}
	
	@Test(expected = NullPointerException.class)
	public void testCalculateTotalOrderPriceThrowExceptionOrderContainNullPizzas() {			
		Map<Pizza, Integer> pizzas = null;
		
		TotalOrderCostCalculator totalOrderCostCalculator = new TotalOrderCostCalculator();
		totalOrderCostCalculator.calculateTotalOrderPrice(pizzas);						
	}
	
	@Test
	public void testCalculateTotalOrderPriceWithThirtyPercentDiscount() {			
		Map<Pizza, Integer> pizzas = new HashMap<>();
		double pizzaPrice = 10;
		double maximumPrice = 10;
		double expectedPrice;
		double price;
		pizzas.put(new Pizza(1L, "Margarita", pizzaPrice, PizzaType.MEAT), 1);
		pizzas.put(new Pizza(1L, "Second", pizzaPrice, PizzaType.MEAT), 1);
		pizzas.put(new Pizza(1L, "Third", pizzaPrice, PizzaType.MEAT), 1);
		pizzas.put(new Pizza(1L, "Fourth", pizzaPrice, PizzaType.MEAT), 1);
		pizzas.put(new Pizza(1L, "Fifth", maximumPrice, PizzaType.SEA), 1);
		expectedPrice = 47;
		TotalOrderCostCalculator totalOrderCostCalculator = new TotalOrderCostCalculator();
		price = totalOrderCostCalculator.calculateTotalOrderPrice(pizzas);	
		
		assertEquals(expectedPrice, price, DELTA);
	}	
	
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateTotalOrderPriceWithNegativeAccumulativeCardSumThrowsException() throws Exception {
		
		Map<Pizza, Integer> pizzas = new HashMap<>();
		Double accumulativeCardSum = -100.;
		TotalOrderCostCalculator totalOrderCostCalculator = new TotalOrderCostCalculator();
		
		when(pizzaService.getPizzaById(anyLong())).thenReturn(new Pizza(0L, "Pizza", 10.0, PizzaType.MEAT));
		
		pizzas.put(pizzaService.getPizzaById(0L), 10);
		
		totalOrderCostCalculator.calculateTotalOrderPrice(pizzas, accumulativeCardSum);
	}
	
	@Test
	public void testCalculateTotalOrderPriceWithPositiveAccumulativeCardSumAndMaximumDiscount() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		Double accumulativeCardSum = 1000000.;
		when(pizzaService.getPizzaById(anyLong())).thenReturn(new Pizza(0L, "Pizza", 10.0, PizzaType.MEAT));
		
		pizzas.put(pizzaService.getPizzaById(0L), 10);
		
		Double expectedtotalOrderCost = 50.;
		TotalOrderCostCalculator totalOrderCostCalculator = new TotalOrderCostCalculator();
		Double totalOrderCost = 
				totalOrderCostCalculator.calculateTotalOrderPrice(pizzas, accumulativeCardSum);

		assertEquals(expectedtotalOrderCost, totalOrderCost, DELTA);
	}
		
	@Test
	public void testCalculateTotalOrderPriceWithPositiveAccumulativeCardSum() {
		
		Map<Pizza, Integer> pizzas = new HashMap<>();
		Double accumulativeCardSum = 100.;
		when(pizzaService.getPizzaById(anyLong())).thenReturn(new Pizza(0L, "Pizza", 10.0, PizzaType.MEAT));
		
		pizzas.put(pizzaService.getPizzaById(0L), 10);
		
		Double expectedtotalOrderCost = 90.;
		TotalOrderCostCalculator totalOrderCostCalculator = new TotalOrderCostCalculator();
		Double totalOrderCost = 
				totalOrderCostCalculator.calculateTotalOrderPrice(pizzas, accumulativeCardSum);

		assertEquals(expectedtotalOrderCost, totalOrderCost, DELTA);
	}
	
	@Test
	public void testCalculateTotalOrderPriceWithZeroOnAccumulativeCardSum() {
		
		Map<Pizza, Integer> pizzas = new HashMap<>();
		Double accumulativeCardSum = 0.;
		when(pizzaService.getPizzaById(anyLong())).thenReturn(new Pizza(0L, "Pizza", 10.0, PizzaType.MEAT));
		
		pizzas.put(pizzaService.getPizzaById(0L), 10);
		
		Double expectedtotalOrderCost = 100.;
		TotalOrderCostCalculator totalOrderCostCalculator = new TotalOrderCostCalculator();
		Double totalOrderCost = 
				totalOrderCostCalculator.calculateTotalOrderPrice(pizzas, accumulativeCardSum);

		assertEquals(expectedtotalOrderCost, totalOrderCost, DELTA);
	}	
	
	@Test
	public void testCalculateTotalOrderPriceWithNullAccumulativeCardSum() {
		Map<Pizza, Integer> pizzas = new HashMap<>();
		Double accumulativeCardSum = null;
		when(pizzaService.getPizzaById(anyLong())).thenReturn(new Pizza(0L, "Pizza", 10.0, PizzaType.MEAT));
		
		pizzas.put(pizzaService.getPizzaById(0L), 10);
		
		Double expectedtotalOrderCost = 100.;
		TotalOrderCostCalculator totalOrderCostCalculator = new TotalOrderCostCalculator();
		Double totalOrderCost = 
				totalOrderCostCalculator.calculateTotalOrderPrice(pizzas, accumulativeCardSum);

		assertEquals(expectedtotalOrderCost, totalOrderCost, DELTA);
	}
	
}
