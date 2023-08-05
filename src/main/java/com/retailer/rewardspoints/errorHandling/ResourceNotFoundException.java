package com.retailer.rewardspoints.errorHandling;

/**
 * Exception to be thrown when no customer found
 */
public class ResourceNotFoundException extends Exception {
    Long customerId;

    public ResourceNotFoundException(final Long customerId, final String message) {
        super(message);
        this.customerId = customerId;
    }

}
