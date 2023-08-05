package com.retailer.rewardspoints.errorHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Global error handler for handling any type of error.
 */
@ControllerAdvice
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

    /**
     * Default global exception handler
     */
    @ExceptionHandler({Exception.class, Error.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object unknownExceptions(){
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorMessage(" Something went Wrong !!");
        response.setSuggestedAction("Please check your URL or input data.");
        return response;
    }
}