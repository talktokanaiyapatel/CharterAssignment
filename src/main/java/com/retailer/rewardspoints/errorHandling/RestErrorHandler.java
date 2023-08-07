package com.retailer.rewardspoints.errorHandling;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global error handler for handling any type of error.
 */
@RestControllerAdvice
public class RestErrorHandler {

    @Value("${points.resource.not.found.customerid.message}")
    private String customerIdMessage;

    @Value("${points.resource.not.found.suggested.action.message}")
    private String suggestedAction;

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Object processCustomerNotFound(ResourceNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorMessage(customerIdMessage + ex.customerId + " " + ex.getMessage());
        response.setSuggestedAction(suggestedAction);
        return response;
    }
}