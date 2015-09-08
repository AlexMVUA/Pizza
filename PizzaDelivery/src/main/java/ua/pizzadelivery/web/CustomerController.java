package ua.pizzadelivery.web;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.pizzadelivery.domain.AccumulativeCard;
import ua.pizzadelivery.domain.Address;
import ua.pizzadelivery.domain.Customer;
import ua.pizzadelivery.domain.User;
import ua.pizzadelivery.domain.UserRole;
import ua.pizzadelivery.service.CustomerService;
import ua.pizzadelivery.service.UserService;

@Controller
public class CustomerController {
	
    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;      
    
    @RequestMapping(value = "/registerCustomer", method = RequestMethod.POST)
    public String confirmCustomerRegistration( 
    		@RequestParam String name,
    		@RequestParam String city,
    		@RequestParam String street,
    		@RequestParam String building,
    		@RequestParam String apartment,
    		@RequestParam String password,
    		HttpSession httpSession) {
    	
    	Address address = new Address();
    	address.setCity(city);     	
    	address.setStreet(street);
    	address.setBuilding(building);
    	address.setApartment(apartment);
    	
    	AccumulativeCard accumulativeCard = new AccumulativeCard();
    	accumulativeCard.setAddress(address);
    	accumulativeCard.setAccumulativeSum(0.);
    	
    	Customer customer = new Customer();
    	customer.setAccumulativeCard(accumulativeCard);
    	customer.setName(name);    	
    	
    	Set<UserRole> roles = new HashSet<>();
    	roles.add(UserRole.ROLE_USER);
    	
    	User user = new User();
    	user.setPassword(password);     	
    	user.setUserRoles(roles);
    	user.setCustomer(customer);    	
    	
    	userService.addUser(user);    	
    	
    	return "redirect:../jsp/order/";
    }          
    
    @RequestMapping(value = "/registerCustomer", method = RequestMethod.GET)
    public String makeOrder(Model model, HttpSession httpSession) {
    	   	
        return "newcustomer";
    }
}

