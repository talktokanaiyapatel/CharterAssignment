package com.retailer.rewardspoints.entity;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * POJO: represents Transaction entity in system
 */
public class Transaction {
    private Long transactionId;

    private final Long customerId;

    private final Timestamp transactionDate;

    private final double transactionAmount;

    public Transaction(Long transactionId, Long customerId, Timestamp transactionDate, double transactionAmount) {
        this.transactionId = transactionId;
        this.customerId = customerId;
        this.transactionDate = transactionDate;
        this.transactionAmount = transactionAmount;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(transactionId, that.transactionId) && Objects.equals(customerId, that.customerId) && Objects.equals(transactionDate, that.transactionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, customerId, transactionDate);
    }

}