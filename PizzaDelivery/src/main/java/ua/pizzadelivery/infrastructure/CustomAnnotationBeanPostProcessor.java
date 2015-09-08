package ua.pizzadelivery.infrastructure;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class CustomAnnotationBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		Object proxyObj =  new ProxyForBenchmarkAnnotation().checkAndCreateProxyObjBenchmark(bean);
		return proxyObj;
	}

	@Override
	public Object postProcessBeforeInitialization(final Object bean, String beanName)
			throws BeansException {		
		return bean;
	}
	
}
