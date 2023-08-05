package com.retailer.rewardspoints.errorHandling;

/**
 * Class represents error response to be sent to customer
 */
public class ExceptionResponse {

    private String errorMessage;
    private String suggestedAction;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getSuggestedAction() {
        return suggestedAction;
    }

    public void setSuggestedAction(String suggestedAction) {
        this.suggestedAction = suggestedAction;
    }
}
