package ua.pizzadelivery.start;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.pizzadelivery.domain.AccumulativeCard;
import ua.pizzadelivery.domain.Customer;
import ua.pizzadelivery.domain.Order;
import ua.pizzadelivery.domain.Pizza;
import ua.pizzadelivery.domain.PizzaType;
import ua.pizzadelivery.repository.AccumulativeCardRepository;
import ua.pizzadelivery.repository.CustomerRepository;
import ua.pizzadelivery.repository.PizzaRepository;
import ua.pizzadelivery.repository.impl.JPAPizzaRepository;
import ua.pizzadelivery.service.OrderService;

public class SpringPizzaApp {

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext repositoryContext 
				= new ClassPathXmlApplicationContext("repositoryContext.xml");
		
		ConfigurableApplicationContext appContext 
				= new ClassPathXmlApplicationContext(
						new String[]{"appContext.xml"}, 
						repositoryContext);
		/*PizzaRepository pizzaRepository = 
				(PizzaRepository) appContext.getBean("pizzaRepository");
		System.err.println(pizzaRepository);
		
		OrderRepository orderRepository = 
				(OrderRepository) appContext.getBean("orderRepository");
		System.err.println(orderRepository);
		*/
		//System.out.println("appContext.getBean(orderService): " + appContext.getBean("orderService").getClass().getName());
		OrderService orderService = 
				(OrderService) appContext.getBean(OrderService.class);
		
		//OrderService orderService = (OrderService) appContext.getBean("orderService");
		//System.out.println(orderService);
		
		String[] beans = appContext.getBeanDefinitionNames();	
		System.out.println("Context started:");
		for (String str:beans) {
			System.out.println("in context: " + str);
		}
		
		Customer customer = new Customer(3L, "Juno");
		Order order1 = orderService.placeNewOrder(customer, 1L,3L,1L,1L,2L);
		Order order2 = orderService.placeNewOrder(customer, 2L,2L);
		System.out.println(order1);
		System.out.println(order2);
		
		PizzaRepository pizzaRepository 
			= (PizzaRepository) appContext.getBean("pizzaRepository", PizzaRepository.class);
		
		
		for(Pizza p : pizzaRepository.getAllPizzas()) {
			System.out.println(p);
		}
		
		//pizzaRepository.save(new Pizza( "Carribean", 10.5, PizzaType.Meat));
		
		CustomerRepository customerRepository 
		= (CustomerRepository) appContext.getBean("customerRepository", CustomerRepository.class);
		Customer customer1 = new Customer();
		customer1.setName("Mike");
		customerRepository.save(customer1);
		
		for(Customer c : customerRepository.getAllCustomers()) {
			System.out.println(c);
		}
		
		/*AccumulativeCardRepository accumulativeCardRepository 
				= (AccumulativeCardRepository) appContext.getBean("accumulativeCard", AccumulativeCardRepository.class);
		
			System.out.println(accumulativeCardRepository.getAccumulativeCardById(1L));
		*/
		
		
		appContext.close();
		repositoryContext.close();
	}

}
