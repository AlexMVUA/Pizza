package ua.pizzadelivery.infrastructure;

public class ObjectFactory {
	
	private static ObjectFactory instance;
	private Config config = new JavaConfig();
	
	private ObjectFactory() {	
		
	}
	
	public static ObjectFactory getInstance () {
		if (instance != null) {
			return instance;
		}
		instance = new ObjectFactory();
		return instance;
	}
	
	public Object createObject(String string) throws Exception {
		Class<?> clazz = config.getImplementation(string);
		return clazz.newInstance();
	}
	
}
