package ua.pizzadelivery.infrastructure;

public interface Config {
	Class<?> getImplementation(String beanName);
}
