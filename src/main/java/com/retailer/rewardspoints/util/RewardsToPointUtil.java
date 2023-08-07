package com.retailer.rewardspoints.util;

import com.retailer.rewardspoints.constants.Constants;
import com.retailer.rewardspoints.entity.Transaction;
import com.retailer.rewardspoints.model.RewardPoints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    final static Logger log = LoggerFactory.getLogger(RewardsToPointUtil.class);

    public static RewardPoints getPointForUser(Set<Transaction> customerAllTransactions, Long customerId) {
        // POJO: Filing the result object with data.
        log.info("Entering getPointForUser with customer {}", customerId);
        log.debug(" Entering getPointForUser with customerid {} and Transactions {}", customerId, customerAllTransactions);
        RewardPoints customerRewardPoints = new RewardPoints();
        customerRewardPoints.setCustomerId(customerId);
        if (!customerAllTransactions.isEmpty()) {
            log.info(" In the if block. customer has {} transactions ", customerAllTransactions.size());
            /* Below line creates Mapping of all transactions to the equivalent points leveraging the Stream apis
               For example, for customerid = 102, Below Map has below value...
              MAP(Key)----------------------------------------------------------------------------------------------------------------Value(points)
              Transaction{transactionId=2004, customerId=102, transactionDate=2023-05-24 02:48:21.098, transactionAmount=51.0}     =>   1
              Transaction{transactionId=2003, customerId=102, transactionDate=2023-03-20 18:44:20.097, transactionAmount=400.0     =>   650
              Transaction{transactionId=2001, customerId=102, transactionDate=2023-06-23 18:56:05.123, transactionAmount=100.0}    =>   50
              Transaction{transactionId=2005, customerId=102, transactionDate=2023-06-23 05:48:23.123, transactionAmount=101.0}    =>   52
              Transaction{transactionId=2002, customerId=102, transactionDate=2023-06-24 00:23:05.122, transactionAmount=120.0}    =>   90
            */
            Map<Transaction, Long> mapAmountToPoint = customerAllTransactions.stream().collect(Collectors.toMap(transaction -> transaction, transaction -> calculateRewardsFromAmount(transaction.getTransactionAmount())));
            log.debug(" For user {} Transaction has this mapping for points {}", customerId, mapAmountToPoint);
            //Mapping all the data according to Month (full month name) and summing the points
            /*
              eg. if you take above data as sample for customer id =102, Below map will have below as sample values
                 KEY(Month)      VALUE(Total Points for month)
                "June":          192     derived from points [50 + 52 + 90]
                "May":           1,
                "March":         650
             */
            Map<String, Long> mapMonthToMonthlyPoint = mapAmountToPoint.entrySet().stream().collect(Collectors.groupingBy(item -> getMonth(item.getKey().getTransactionDate()), Collectors.summingLong(Map.Entry::getValue)));
            log.debug(" For user {} Aggregate Month to Points Map {}", customerId, mapMonthToMonthlyPoint);
            customerRewardPoints.setMonthlyRewards(mapMonthToMonthlyPoint);
            customerRewardPoints.setTransactionCount(customerAllTransactions.size());
            // Using stream reduce for total.
            customerRewardPoints.setTotalRewardPoints(mapMonthToMonthlyPoint.values().stream().reduce(0L, Long::sum));
        } else {
            log.info("in else loop. No transaction found for user {} ", customerId);
            customerRewardPoints.setMonthlyRewards(Collections.emptyMap());
            customerRewardPoints.setTransactionCount(customerAllTransactions.size());
            // Using stream reduce for total.
            customerRewardPoints.setTotalRewardPoints(0);
        }
        log.debug(" Exiting getPointForUser with customerid {} and returned RewardPoints {}", customerId, customerRewardPoints);
        /*
          Below variable holds aggregate values to be returned to client.
          eg. considering customer id=102 and above data, it has values like below representation..
             {
              "customerId": 102,
              "totalRewardPoints": 843,
              "transactionCount": 5,
              "monthlyRewards": {
                "June": 192,
                "May": 1,
                "March": 650
              }
            }
         */
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
        log.debug("Calling calculateRewardsFromAmount with Amount {}", amount);
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
        log.debug("Exiting calculateRewardsFromAmount with points {}", rewards);
        return rewards;
    }

    /**
     * Extracts the String Month from timestamp.
     *
     * @param timestamp transaction date
     * @return Month as String
     */
    private static String getMonth(Timestamp timestamp) {
        log.debug("Calling getMonth with Timestamp {} ", timestamp);
        SimpleDateFormat format = new SimpleDateFormat("MMMM");
        String month = format.format(timestamp);
        log.debug("Exiting getMonth with Month {} ", month);
        return month;
    }
}
