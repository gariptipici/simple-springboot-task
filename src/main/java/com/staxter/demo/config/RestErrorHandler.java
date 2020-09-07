package com.staxter.demo.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.staxter.demo.exception.UserAlreadyExistsException;
import com.staxter.demo.exception.UserDoesNotExistException;
import com.staxter.demo.exception.WrongPasswordException;

@ControllerAdvice
public class RestErrorHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public Object processValidationError(UserAlreadyExistsException ex) {
        return ex.toString();
    }
    
    @ExceptionHandler(WrongPasswordException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public Object processValidationError(WrongPasswordException ex) {
        return ex.toString();
    }
    
    @ExceptionHandler(UserDoesNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Object processValidationError(UserDoesNotExistException ex) {
        return ex.toString();
    }
}
