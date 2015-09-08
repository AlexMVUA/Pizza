package ua.pizzadelivery.start;

import ua.pizzadelivery.domain.Customer;
import ua.pizzadelivery.domain.Order;
import ua.pizzadelivery.infrastructure.ApplicationContext;
import ua.pizzadelivery.infrastructure.Config;
import ua.pizzadelivery.infrastructure.JavaConfig;
import ua.pizzadelivery.infrastructure.JavaConfigApplicationContext;
import ua.pizzadelivery.service.OrderService;

public class PizzaApp {

	public static void main(String[] args) throws Exception {
		Customer customer = new Customer (1L, "John");        
		
		Config config = new JavaConfig();
		ApplicationContext context = new JavaConfigApplicationContext(config);
		OrderService orderService = (OrderService) context.getBean("orderService");
		System.out.println("orderService " + orderService);
		
		Order order = orderService.placeNewOrder(customer, 2L, 3L,2L,2L,2L,2L,2L,3L,1L);

		System.out.println(order);

	}

}
