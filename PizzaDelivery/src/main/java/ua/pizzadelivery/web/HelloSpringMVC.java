package ua.pizzadelivery.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.pizzadelivery.service.CustomerService;
import ua.pizzadelivery.service.PizzaService;


@Controller("helloController")
public class HelloSpringMVC {
	
	@Autowired
	PizzaService pizzaService;
	@Autowired
	CustomerService customerService;
	
	
	/*@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hello Spring MVC";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String handleDefaultRequest(Model model) {
		model.addAttribute("msg", new Date());
		return "hello";
	}
	*/
	@RequestMapping(value = "/pizzas", method = RequestMethod.GET)
	public String viewPizzas(Model model) {
				
		model.addAttribute("pizzas", 
				pizzaService.getAllPizzas());
		model.addAttribute("customers", 
				customerService.getAllCustomers());
		return "pizzas";
	}
}
