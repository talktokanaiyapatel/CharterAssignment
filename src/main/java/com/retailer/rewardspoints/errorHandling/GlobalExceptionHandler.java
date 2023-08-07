package com.retailer.rewardspoints.errorHandling;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@EnableWebMvc
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Value("${points.generic.missing.handler.errormessage}")
    private String errormessage;

    @Value("${points.generic.missing.handler.suggestion}")
    private String suggestedAction;

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
                                                                   HttpStatus status, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorMessage(errormessage);
        response.setSuggestedAction(suggestedAction);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}