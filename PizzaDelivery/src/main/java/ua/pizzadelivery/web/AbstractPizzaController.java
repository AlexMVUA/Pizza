package ua.pizzadelivery.web;

import java.beans.PropertyEditorSupport;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import ua.pizzadelivery.domain.Pizza;
import ua.pizzadelivery.service.PizzaService;

public abstract class AbstractPizzaController {
	
	@Autowired
    protected PizzaService pizzaService;
  
    protected Pizza getPizzaById(Long id) {
    	if (id<=0) throw new IllegalArgumentException("ID<0");
    	Pizza pizza = pizzaService.getPizzaById(id);
    	if (pizza == null) 
              throw new NotFoundPizzaException("Pizza id" + id + " not found" );
    	return pizza;
    }
    
    protected List<Pizza> getAllPizzas() {    	
    	List<Pizza> list = pizzaService.getAllPizzas();    	
    	return list;
    }
        
    protected void deletePizza(Pizza pizza) {
    	pizzaService.removePizza(pizza);
    }
    
    
    @InitBinder
    private void pizzaBinder(WebDataBinder binder) {
    	binder.registerCustomEditor(
    			Pizza.class,
    			new PropertyEditorSupport(){
    		
    				@Override
    				public void setAsText(String pizzaId) {
    					Pizza pizza = null;
    					if (pizzaId != null && !pizzaId.trim().isEmpty()) {
    						Long id = Long.valueOf(pizzaId);
    						pizza = getPizzaById(id);
    					}
    					setValue(pizza);
    				}
    	});
    }
}
