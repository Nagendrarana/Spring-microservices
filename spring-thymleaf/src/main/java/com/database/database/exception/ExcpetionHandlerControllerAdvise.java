package com.database.database.exception;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;




@ControllerAdvice
public class ExcpetionHandlerControllerAdvise {


    private static final Logger log = LoggerFactory.getLogger(ExcpetionHandlerControllerAdvise.class);
    //adding our own Specific Exception handler for ResourceNotFound
    @ExceptionHandler(ResourceNotFoundException.class)
    //Setting the value for Response Status to not found
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    //making a method which return ExceptionResponse Object
    public @ResponseBody ExceptionResponse handleResourceNotFound(final ResourceNotFoundException exception,
                                                                  final HttpServletRequest request) {
        //creating a object of our exception Response class
        ExceptionResponse error = new ExceptionResponse();
        //Setting the value for error
        error.setErrorMessage(exception.getMessage());
        //Setting the value for URI
        error.callerURL(request.getRequestURI());
        //Logging the error
        log.info("Error is coming from ResourceNotFoundException");
        log.error("Error = {} and it came from {}",exception.getMessage(),request.getRequestURI());
        return error;
    }
    //ExceptionHandler for all the exceptions
    @ExceptionHandler(Exception.class)
    //Setting Response status to Internal_server_error
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ExceptionResponse handleException(final Exception exception,
                                                           final HttpServletRequest request) {
        //Creating the object and setting its value and returning it
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.callerURL(request.getRequestURI());
        log.info("Error is Coming from Exception class");
        log.error("Error = {} and it came from {}",exception.getMessage(),request.getRequestURI());

        return error;
    }

}


