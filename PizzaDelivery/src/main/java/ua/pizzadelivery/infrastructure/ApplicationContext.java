package ua.pizzadelivery.infrastructure;

public interface ApplicationContext {

	public Object getBean(String beanName) throws Exception;

}
