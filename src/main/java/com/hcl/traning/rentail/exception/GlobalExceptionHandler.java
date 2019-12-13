package com.hcl.traning.rentail.exception;

import javax.persistence.NoResultException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.hcl.traning.rentail.model.ErrorCustom;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorCustom> handle(NoHandlerFoundException ex){
        String message = "HTTP " + ex.getHttpMethod() + " for " + ex.getRequestURL() + " is not supported.";        
        ErrorCustom error = new ErrorCustom( HttpStatus.NOT_FOUND.value(), message);
        
        return new ResponseEntity<ErrorCustom>(error, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorCustom> handle(ConstraintViolationException ex){
        String message = "SQL Exception: " + ex.getMessage();        
        ErrorCustom error = new ErrorCustom( HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
        
        return new ResponseEntity<ErrorCustom>(error, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(NoResultException.class)
    public ResponseEntity<ErrorCustom> handle(NoResultException ex){
        String message = "Error: " + ex.getMessage();        
        ErrorCustom error = new ErrorCustom( HttpStatus.NOT_FOUND.value(), message);
        
        return new ResponseEntity<ErrorCustom>(error, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorCustom> handle(IllegalArgumentException ex){
        String message = "Error: " + ex.getMessage();        
        ErrorCustom error = new ErrorCustom( HttpStatus.BAD_REQUEST.value(), message);
        
        return new ResponseEntity<ErrorCustom>(error, HttpStatus.NOT_FOUND);
    }
}
