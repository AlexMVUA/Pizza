package ua.pizzadelivery.web;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.pizzadelivery.domain.Customer;
import ua.pizzadelivery.domain.Order;
import ua.pizzadelivery.domain.Pizza;
import ua.pizzadelivery.domain.TotalOrderCostCalculator;
import ua.pizzadelivery.service.AccumulativeCardService;
import ua.pizzadelivery.service.AddressService;
import ua.pizzadelivery.service.CustomerService;
import ua.pizzadelivery.service.OrderService;
import ua.pizzadelivery.service.PizzaService;

@Controller
@RequestMapping("/order")
//@SessionAttributes("basket")
public class OrderController {

    @Autowired
    private OrderService orderService;
    
    @Autowired
    private PizzaService pizzaService;
    
    @Autowired
    private AddressService addressService;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private AccumulativeCardService accumulativeCardService;
    
    @Autowired
    private TotalOrderCostCalculator totalOrderCostCalculator;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewPizzasToOrder(Model model) {
        model.addAttribute("pizzas",
                pizzaService.getAllPizzas());
        return "order";
    }
    
    @RequestMapping(value = "/clearBasket", method = RequestMethod.POST)
    public String clearBasket(Model model, HttpSession httpSession) {
    	httpSession.setAttribute("basket", null);
    	//httpSession.removeAttribute("basket");
        return "redirect:";
    }
   
    @RequestMapping(value = "/previewOrder", method = RequestMethod.POST)
    public String seeBasket(Model model, HttpSession httpSession) {
    	Order order = (Order) httpSession.getAttribute("basket");
    	Double accumulativeCardSum = 
    			order.getCustomer().getAccumulativeCard().getAccumulativeSum();    	
    	if (null == accumulativeCardSum) {
    		accumulativeCardSum = 0.;
    	}    	
    	Double sum = 
    			totalOrderCostCalculator.calculateTotalOrderPrice(
    					order.getPizzas(),
    					accumulativeCardSum);
    	httpSession.setAttribute("sum", sum);
    	
        return "cart";
    }
    
    @RequestMapping(value = "/viewHistory", method = RequestMethod.GET)
    public String viewAllOrders(Model model) { 
    	Authentication auth = 
    			SecurityContextHolder.getContext().getAuthentication();
    	String userName = auth.getName();
		List<Order> orders = orderService.getAllOrdersByCustomer(userName);		
        model.addAttribute("orders", orders );
        return "history";
    }
    
    @Transactional
    @RequestMapping(value = "/confirmOrder", method = RequestMethod.POST)
    public String confirmOrder(
    		Model model,     		
    		HttpSession httpSession) {
    	
    	Order order = (Order) httpSession.getAttribute("basket");
    	orderService.placeOrder(order);
    	order.getTotalCost();
    	
    	accumulativeCardService.increaseAccumulativeCardTotalSum(
    			order.getCustomer().getAccumulativeCard(), 
    			order.getPizzas());
    	    	
    	return "redirect:";
    }    
    
    @RequestMapping(value = "/addPizzaToBasket", method = RequestMethod.POST)
    public String addNewPizza(
    		@RequestParam("pizzaid") Long id, 
            Model model,
            HttpSession httpSession) {
    	  	
    	Pizza addedPizza;    	    	
    	
    	addedPizza = pizzaService.getPizzaById(id);
    	Order basket = (Order) httpSession.getAttribute("basket");    	
    	
    	if (basket == null) {
    		basket = new Order();
    		Map<Pizza, Integer> map = new HashMap<>();
    		map.put(addedPizza, 1);
    		basket.setPizzas(map);
    	} else {
    		Map<Pizza, Integer> map = basket.getPizzas();
    		if (map.containsKey(addedPizza)) {
    			Integer amount = map.get(addedPizza);
    			map.put(addedPizza, amount + 1);
    		} else {
    			map.put(addedPizza, 1);
    		}
    	}    	
    	
    	Authentication auth = 
    			SecurityContextHolder.getContext().getAuthentication();
    	String userName = auth.getName();
    	Customer customer = customerService.getCustomerByName(userName);
    	basket.setCustomer(customer);    
    	
    	Double accumulativeCardSum =
    			accumulativeCardService.getAccumulativeCardSum(userName);
    	
    	httpSession.setAttribute("basket", basket);
    	
    	Double sum = totalOrderCostCalculator.calculateTotalOrderPrice(
    					basket.getPizzas(),
    					accumulativeCardSum);
    	basket.setTotalCost(sum);
    	httpSession.setAttribute("sum", sum);    	
    	
        return "redirect:";
    }    
}

