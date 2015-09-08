package ua.pizzadelivery.web;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(
        value = HttpStatus.NOT_FOUND, 
        reason = "Pizza not found")
public class NotFoundPizzaException extends RuntimeException {

    public NotFoundPizzaException(String string) {
        super(string);
    }
    
}