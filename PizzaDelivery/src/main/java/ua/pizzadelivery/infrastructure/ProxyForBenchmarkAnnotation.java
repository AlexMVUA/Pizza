package ua.pizzadelivery.infrastructure;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class ProxyForBenchmarkAnnotation {
	/*Object obj;
	
	ProxyForBenchmarkAnnotation(Object object) {
		this.obj = object;
	}
	*/
	
	public Object checkAndCreateProxyObjBenchmark(Object obj) throws IllegalArgumentException, SecurityException {
		Class<?> clazz = obj.getClass();
		
		for(Method method : clazz.getMethods()) {
			if (method.isAnnotationPresent(Benchmark.class)) {
				return createProxyObj(obj);
			}
		}
		return obj;
	}
	
	public Object createProxyObj(final Object o) throws IllegalArgumentException {
		final Class<?> type = o.getClass();
		
		return Proxy.newProxyInstance(type.getClassLoader(),
				type.getInterfaces(), 
				new InvocationHandler() {

					@Override
					public Object invoke(Object proxy,
							Method method, 
							Object[] args) throws Throwable  {
						if(type.getMethod(method.getName(), method.getParameterTypes())
						.isAnnotationPresent(Benchmark.class)) {

							System.out.println("Benchmark start: " + method.getName());
							long start = System.nanoTime();

							Object retVal = method.invoke(o, args);

							long end = System.nanoTime() - start;
							System.out.println("Elapsed time: " + end);
							System.out.println("Benchmark finish. " + method.getName());
							return retVal;
						} else {
							return method.invoke(o, args);
						}
					}
				});
	}

}
