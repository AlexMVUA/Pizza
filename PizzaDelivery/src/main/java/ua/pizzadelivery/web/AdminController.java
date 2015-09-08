package ua.pizzadelivery.web;

import java.beans.PropertyEditorSupport;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.pizzadelivery.domain.Order;
import ua.pizzadelivery.domain.Pizza;
import ua.pizzadelivery.service.OrderService;
import ua.pizzadelivery.service.PizzaService;


@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
    private PizzaService pizzaService;
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String goHomeAdminPage(Model model) {
        return "homeadmin";
    }
	
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String viewAllOrders(Model model) { 
		List<Order> orders = orderService.getAllOrders();		
        model.addAttribute("orders", orders );
        return "allorders";
    }
	
	@RequestMapping(value = "/pizzas", method = RequestMethod.GET)
    public String viewAllPizza(Model model) {
		model.addAttribute("pizzas", pizzaService.getAllPizzas());
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    model.addAttribute("roles", auth.getAuthorities().toString());
	    model.addAttribute("name", auth.getName()); 
        return "showpizzas";
    }
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "newpizza";
    }    
    
    @RequestMapping(value = "/addnew", method = RequestMethod.POST)
    public String addNewPizza(@ModelAttribute Pizza newPizza) {
        pizzaService.addPizza(newPizza);
        return "redirect:pizzas";
    }    
   
    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String remove(@RequestParam("pizzaid") Pizza pizza, 
            Model model) {             
        model.addAttribute("pizza", pizza);        
        return "removepizza";
    }
   
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String removeConfirm(@ModelAttribute Pizza pizza) {
        pizzaService.removePizza(pizza);
        return "redirect:pizzas";
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("pizzaid") Pizza pizza, 
            Model model) {      
    	
        model.addAttribute("pizza", pizza);        
        return "editpizza";
    }       
   
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editConfirm(@ModelAttribute Pizza updatedPizza) {      
        pizzaService.editPizza(updatedPizza);
        return "redirect:pizzas";
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
    
    private Pizza getPizzaById(Long id) {
    	if (id<=0) throw new IllegalArgumentException("ID<0");
    	Pizza pizza = pizzaService.getPizzaById(id);
    	if (pizza == null) 
              throw new NotFoundPizzaException("Pizza id" + id + " not found" );
    	return pizza;
    }
	
}
