package pl.barwinscy.planeshifter.login_module.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.barwinscy.planeshifter.login_module.exceptions.UserNotMatchedException;
import pl.barwinscy.planeshifter.login_module.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(Exception exception, WebRequest webRequest) {
        return new ResponseEntity<Object>("Resource Not Found", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotMatchedException.class)
    public ResponseEntity<Object> handlePasswordNotMatchedException(UserNotMatchedException exception, WebRequest webRequest) {
        return new ResponseEntity<Object>(exception.getErrorList() , new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
    }
}
