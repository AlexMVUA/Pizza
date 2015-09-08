package ua.pizzadelivery.web;

import java.beans.PropertyEditorSupport;

import org.springframework.security.access.annotation.Secured;
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

import ua.pizzadelivery.domain.Pizza;

@Controller
@RequestMapping("/pizza")
public class PizzaController extends AbstractPizzaController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewPizzas(Model model) {
        model.addAttribute("pizzas", getAllPizzas());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("roles", auth.getAuthorities().toString());
        model.addAttribute("name", auth.getName());        
        return "showpizzas";
    }
    
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "newpizza";
    }
    
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/addnew", method = RequestMethod.POST)
    public String addNewPizza(@ModelAttribute Pizza newPizza) {
        pizzaService.addPizza(newPizza);
        return "redirect:";
    }
    
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String remove(@RequestParam("pizzaid") Pizza pizza, 
            Model model) {             
        model.addAttribute("pizza", pizza);        
        return "removepizza";
    }
    
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String removeConfirm(@ModelAttribute Pizza pizza) {
        deletePizza(pizza);
        return "redirect:";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("pizzaid") Pizza pizza, 
            Model model) {      
    	
        model.addAttribute("pizza", pizza);        
        return "editpizza";
    }   
    
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editConfirm(@ModelAttribute Pizza updatedPizza) {      
        pizzaService.editPizza(updatedPizza);
        return "redirect:";
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

