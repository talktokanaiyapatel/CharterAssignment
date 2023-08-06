package com.retailer.rewardspoints.errorHandling;

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

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Object processCustomerNotFound(ResourceNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorMessage("CustomerID: " + ex.customerId + " " + ex.getMessage());
        response.setSuggestedAction("Please add data into com.retailer." +
                "rewards.repository.CustomerDataRepository class for customer." +
                "Also add corresponding Transaction records for customer into com.retailer.rewards.repository.TransactionDataRepository class. " +
                "currently available customer can be found @ http://localhost:8080/rewardPoints/customers/ ");
        return response;
    }
}