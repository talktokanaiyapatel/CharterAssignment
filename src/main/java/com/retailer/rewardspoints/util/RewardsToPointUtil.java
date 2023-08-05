package com.retailer.rewardspoints.util;

import com.retailer.rewardspoints.constants.Constants;
import com.retailer.rewardspoints.entity.Transaction;
import com.retailer.rewardspoints.model.RewardPoints;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Utility class covering logic for points calculation.
 */
public class RewardsToPointUtil {
    public static RewardPoints getPointForUser(Set<Transaction> customerAllTransactions, Long customerId) {
        // POJO: Filing the result object with data.
        RewardPoints customerRewardPoints = new RewardPoints();
        customerRewardPoints.setCustomerId(customerId);
        if (!customerAllTransactions.isEmpty()) {
            //Mapping all transactions to the equivalent points
            //leveraging the Stream apis
            Map<Transaction, Long> mapAmountToPoint = customerAllTransactions.stream().collect(Collectors.toMap(transaction -> transaction, transaction -> calculateRewardsFromAmount(transaction.getTransactionAmount())));
            //Mapping all the data according to Month (full month name) and summing the points
            Map<String, Long> mapMonthToMonthlyPoint = mapAmountToPoint.entrySet().stream().collect(Collectors.groupingBy(item -> getMonth(item.getKey().getTransactionDate()), Collectors.summingLong(Map.Entry::getValue)));
            customerRewardPoints.setMonthlyRewards(mapMonthToMonthlyPoint);
            customerRewardPoints.setTransactionCount(customerAllTransactions.size());
            // Using stream reduce for total.
            customerRewardPoints.setTotalRewardPoints(mapMonthToMonthlyPoint.values().stream().reduce(0L, Long::sum));
        } else {
            customerRewardPoints.setMonthlyRewards(Collections.emptyMap());
            customerRewardPoints.setTransactionCount(customerAllTransactions.size());
            // Using stream reduce for total.
            customerRewardPoints.setTotalRewardPoints(0);
        }
        return customerRewardPoints;
    }

    /**
     * Below function converts the transaction amount into reward points.
     * It ignores the decimal parts.
     *
     * @param amount-transaction amount
     * @return converted-points
     */
    private static long calculateRewardsFromAmount(Double amount) {
        long rewards = 0L;
        // Ignoring the decimal parts.
        long remainingAmount = (long) Math.abs(amount);
        if (remainingAmount > Constants.THRESHOLD_100) {
            rewards = (remainingAmount - Constants.THRESHOLD_100) * Constants.POINTS_ABOVE_HUNDRED;
            remainingAmount = Constants.THRESHOLD_100;
        }
        if (remainingAmount > Constants.THRESHOLD_50) {
            rewards += (remainingAmount - Constants.THRESHOLD_50) * Constants.POINTS_ABOVE_FIFTY;
        }
        return rewards;
    }

    /**
     * Extracts the String Month from timestamp.
     *
     * @param timestamp transaction date
     * @return Month as String
     */
    private static String getMonth(Timestamp timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("MMMM");
        return format.format(timestamp);
    }
}
