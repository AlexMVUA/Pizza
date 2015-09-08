package ua.pizzadelivery.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import ua.pizzadelivery.domain.Pizza;
import ua.pizzadelivery.service.PizzaService;


@Controller
@RequestMapping("/pizzas")
public class PizzaRESTController  extends AbstractPizzaController {
	
	@Autowired
	private PizzaService pizzaService;
	
	@RequestMapping(method = RequestMethod.GET, value = "hello")
	@ResponseBody
	public ResponseEntity<String> hello() {
		//return "Hello from RESTController";
		return new ResponseEntity<>("Hello from RESTController", HttpStatus.I_AM_A_TEAPOT);
	}
	
	@RequestMapping(value="pizza/{pizzaId}", method = RequestMethod.GET)
	public ResponseEntity<Pizza> getPizzaById(@PathVariable("pizzaId") Pizza pizza) {	
		if (pizza == null) {
			return new ResponseEntity<>( HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ResponseEntity<List<Pizza>> viePizzas() {	
		List<Pizza> list = getAllPizzas();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@RequestMapping(
			value="pizza/{pizzaId}",
			method = RequestMethod.PUT,
			headers = "Content-Type=application/json")
	public ResponseEntity<?> editPizza(			
			@RequestBody Pizza pizza) {	
		if (pizza == null) {
			return new ResponseEntity<>( HttpStatus.NOT_FOUND);
		}
		pizzaService.editPizza(pizza);		
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
	
	@RequestMapping(
			value="pizza/{pizzaId}",
			method = RequestMethod.DELETE,
			headers = "Content-Type=application/json")
	public ResponseEntity<?> removePizza(			
			@RequestBody Pizza pizza) {	
		if (pizza == null) {
			return new ResponseEntity<>( HttpStatus.NOT_FOUND);
		}
		deletePizza(pizza);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(
			value="/pizza",
			method = RequestMethod.POST,
			headers = "Content-Type=application/json")
	public ResponseEntity<?> createNewPizza(
			@RequestBody Pizza pizza,
			UriComponentsBuilder builder) {	
		pizzaService.addPizza(pizza);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				builder.path("/pizza/{id}")
				.buildAndExpand(pizza.getId()).toUri());
		
		
		return new ResponseEntity<>(headers, HttpStatus.CREATED);		
	}
	
	/*@RequestMapping(value="pizza/{pizzaList}", method = RequestMethod.GET)
	public ResponseEntity<List<Pizza>> getPizzaList(@PathVariable("pizzaIdList") List<Pizza> list) {		
		return new ResponseEntity<>(list, HttpStatus.FOUND);
	}*/
	
	
}
