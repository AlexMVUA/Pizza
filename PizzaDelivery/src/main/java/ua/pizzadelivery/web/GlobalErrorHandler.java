package ua.pizzadelivery.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalErrorHandler {

    //@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundPizzaException.class)
    public ModelAndView exceptionHandelr(
            Exception exception,
            HttpServletRequest req) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("ex", exception);
        
        model.addObject("url", req.getRequestURL());
        return model;
    }
}