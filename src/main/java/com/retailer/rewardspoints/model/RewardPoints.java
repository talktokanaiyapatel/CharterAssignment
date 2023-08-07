package com.retailer.rewardspoints.model;

import java.util.HashMap;
import java.util.Map;

/**
 * represents response object for user containing total points and monthly points data.
 */
public class RewardPoints {
    private long customerId;
    private long totalRewardPoints;
    private long transactionCount;
    // store monthly points to return to customer
    // key - value
    // eg January - 400
    private Map<String, Long> monthlyRewards;

    public RewardPoints() {
        this.monthlyRewards = new HashMap<>();
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getTotalRewardPoints() {
        return totalRewardPoints;
    }

    public void setTotalRewardPoints(long totalRewardPoints) {
        this.totalRewardPoints = totalRewardPoints;
    }

    public Map<String, Long> getMonthlyRewards() {
        return monthlyRewards;
    }

    public void setMonthlyRewards(Map<String, Long> monthlyRewards) {
        this.monthlyRewards = monthlyRewards;
    }

    public long getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(long transactionCount) {
        this.transactionCount = transactionCount;
    }

    /**
     * To facilitate the investigation
     *
     * @return returns string representation
     */
    @Override
    public String toString() {
        return "RewardPoints{" +
                "customerId=" + customerId +
                ", totalRewardPoints=" + totalRewardPoints +
                ", transactionCount=" + transactionCount +
                ", monthlyRewards=" + monthlyRewards +
                '}';
    }
}
