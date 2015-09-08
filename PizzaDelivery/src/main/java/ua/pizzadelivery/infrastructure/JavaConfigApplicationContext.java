package ua.pizzadelivery.infrastructure;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import ua.pizzadelivery.infrastructure.ApplicationContext;

public class JavaConfigApplicationContext implements ApplicationContext {
	
	private final Config config;
	private final Map<String, Object> beans = new HashMap<>();
		
	public JavaConfigApplicationContext(Config config) {
		this.config = config;		
	}
	
	@Override
	public Object getBean(String beanName) throws Exception {
		Object bean = beans.get(beanName);
		if(bean != null) {
			return bean;
		}
		
		BeanBuilder beanBuilder = new BeanBuilder(beanName);
		beanBuilder.createObject();		
		beanBuilder.callInitMethod();
		beanBuilder.createProxy();
		return beanBuilder.getObject();
		
		/*Class<?> clazz = config.getImplementation(beanName);
		System.out.println("beanName = " + beanName);
		Constructor<?> constructor =  clazz.getConstructors()[0];
		Class<?>[] parameters = constructor.getParameterTypes();
		
		if (parameters.length == 0) {
			bean = clazz.newInstance();
			beans.put(beanName, bean);
			return bean;
		}
		Object[] objectsForConstructor = new Object[parameters.length];     

		
		for (int i = 0; i < parameters.length; i++) {
			String paramType = parameters[i].getSimpleName();			
			paramType = Character.toLowerCase(paramType.charAt(0)) + paramType.substring(1);			
			objectsForConstructor[i] = getBean(paramType);
						
		}			
		
		bean = constructor.newInstance(objectsForConstructor);
		beans.put(beanName, bean);
		return bean;	
		*/
		
	}

	class BeanBuilder {
		private Object  obj;
		private final String beanName;
		
		public BeanBuilder(String beanName) {
			this.beanName = beanName;
		}
		
		public void createObject()  throws Exception {
			Class<?> clazz = config.getImplementation(beanName);
			
			Constructor<?> constructor =  clazz.getConstructors()[0];
			Class<?>[] parameters = constructor.getParameterTypes();
			
			if (parameters.length == 0) {
				obj = clazz.newInstance();				
			} else {
				Object[] objectsForConstructor = new Object[parameters.length];
				for (int i = 0; i < parameters.length; i++) {
					String paramType = parameters[i].getSimpleName();			
					paramType = Character.toLowerCase(paramType.charAt(0)) + paramType.substring(1);			
					objectsForConstructor[i] = getBean(paramType);					
				}
				obj = constructor.newInstance(objectsForConstructor);
			}
			beans.put(beanName, obj);			
		}
		
		public void callInitMethod() throws Exception {
			Class<?> clazz = obj.getClass();
			Method method;
			try {
				method = clazz.getMethod("init");
			} catch (NoSuchMethodException e) {
				return;
			} 
			method.invoke(obj);
		}
		
		public void createProxy() {
			ProxyForBenchmarkAnnotation proxyForBenchmarkAnnotation 
            	= new ProxyForBenchmarkAnnotation();
			obj = proxyForBenchmarkAnnotation.checkAndCreateProxyObjBenchmark(obj);
			
		}
		
		public Object getObject() {
			return obj;
		}
	}
}
