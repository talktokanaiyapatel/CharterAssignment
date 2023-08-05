package com.retailer.rewardspoints.util;

import com.retailer.rewardspoints.entity.Transaction;
import com.retailer.rewardspoints.model.RewardPoints;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RewardsToPointUtilTest {
    Transaction trans100;
    Transaction trans10;
    Transaction trans0;
    Transaction trans50;
    Transaction trans51;
    Transaction trans199;
    Transaction trans1;
    Transaction trans49;
    Transaction trans300;
    Transaction transDecimal5;
    Transaction transDecimal50;
    Transaction transDecimal55;
    Transaction transDecimal100;
    Transaction transDecimal300;
    Transaction trans99;
    Transaction trans101;

    @BeforeEach
    void setUp() {
        trans100 = new Transaction(201L,101L,getTimeStamp("23/06/2023 19:34:05.123"),100);
        trans10 = new Transaction(202L,101L,getTimeStamp("24/06/2023 19:34:05.123"),10);
        trans0 = new Transaction(203L,101L,getTimeStamp("25/06/2023 19:34:05.123"),0);
        trans50 = new Transaction(204L,101L,getTimeStamp("12/04/2023 19:34:05.123"),50);
        trans51 = new Transaction(205L,101L,getTimeStamp("13/06/2023 19:34:05.123"),51);
        trans99 = new Transaction(2050L,101L,getTimeStamp("13/07/2023 19:34:05.123"),99);
        trans101 = new Transaction(2051L,101L,getTimeStamp("17/07/2023 19:34:05.101"),101);
        trans199 = new Transaction(206L,101L,getTimeStamp("14/06/2023 19:34:05.123"),199);
        trans1 = new Transaction(207L,101L,getTimeStamp("15/05/2023 19:34:05.123"),1);
        trans49 = new Transaction(208L,101L,getTimeStamp("16/06/2023 19:34:05.123"),49);
        trans300 = new Transaction(209L,101L,getTimeStamp("21/06/2023 19:34:05.123"),300);
        transDecimal5 = new Transaction(2010L,101L,getTimeStamp("23/11/2023 19:34:05.124"),5.5);
        transDecimal50 = new Transaction(2011L,101L,getTimeStamp("23/11/2023 19:34:05.113"),50.3);
        transDecimal55 = new Transaction(212L,101L,getTimeStamp("23/12/2023 19:34:05.111"),55.6);
        transDecimal100 = new Transaction(2023L,101L,getTimeStamp("23/06/2023 19:34:05.122"),100.6);
        transDecimal300 = new Transaction(2045L,101L,getTimeStamp("23/06/2023 19:34:05.121"),300.5);
    }
    @Test
    void getPointForUserTestZeroTransaction() {
        Set<Transaction> customerAllTransactions = new HashSet<>();
        RewardPoints rewardPoints = RewardsToPointUtil.getPointForUser(customerAllTransactions,101L);
        assertEquals( rewardPoints.getTransactionCount(),0L);
        assertEquals( rewardPoints.getTotalRewardPoints(),0L);
        assertEquals( rewardPoints.getMonthlyRewards().size(),0L);
        assertEquals( rewardPoints.getCustomerId(),101L);
    }
    @Test
    void getPointForUserTestSingleTransactionWithZeroAmount() {
        Set<Transaction> customerAllTransactions = new HashSet<>();
        customerAllTransactions.add(trans0);
        RewardPoints rewardPoints = RewardsToPointUtil.getPointForUser(customerAllTransactions,101L);
        assertEquals( rewardPoints.getTransactionCount(),1L);
        assertEquals( rewardPoints.getTotalRewardPoints(),0L);
        assertEquals( rewardPoints.getMonthlyRewards().size(),1L);
        assertEquals( rewardPoints.getCustomerId(),101L);
    }
    @Test
    void getPointForUserTestSingleTransactionWith50Amount() {
        Set<Transaction> customerAllTransactions = new HashSet<>();
        customerAllTransactions.add(trans50);
        RewardPoints rewardPoints = RewardsToPointUtil.getPointForUser(customerAllTransactions,101L);
        assertEquals( rewardPoints.getTransactionCount(),1L);
        assertEquals( rewardPoints.getTotalRewardPoints(),0L);
        assertEquals( rewardPoints.getMonthlyRewards().size(),1L);
    }
    @Test
    void getPointForUserTestSingleTransactionWith51Amount() {
        Set<Transaction> customerAllTransactions = new HashSet<>();
        customerAllTransactions.add(trans51);
        RewardPoints rewardPoints = RewardsToPointUtil.getPointForUser(customerAllTransactions,101L);
        assertEquals( rewardPoints.getTransactionCount(),1L);
        assertEquals( rewardPoints.getTotalRewardPoints(),1L);
        assertEquals( rewardPoints.getMonthlyRewards().size(),1L);
    }
    @Test
    void getPointForUserTestSingleTransactionWith49Amount() {
        Set<Transaction> customerAllTransactions = new HashSet<>();
        customerAllTransactions.add(trans49);
        RewardPoints rewardPoints = RewardsToPointUtil.getPointForUser(customerAllTransactions,101L);
        assertEquals( rewardPoints.getTransactionCount(),1L);
        assertEquals( rewardPoints.getTotalRewardPoints(),0L);
        assertEquals( rewardPoints.getMonthlyRewards().size(),1L);
    }
    @Test
    void getPointForUserTestSingleTransactionWith100Amount() {
        Set<Transaction> customerAllTransactions = new HashSet<>();
        customerAllTransactions.add(trans100);
        RewardPoints rewardPoints = RewardsToPointUtil.getPointForUser(customerAllTransactions,101L);
        assertEquals( rewardPoints.getTransactionCount(),1L);
        assertEquals( rewardPoints.getTotalRewardPoints(),50L);
        assertEquals( rewardPoints.getMonthlyRewards().size(),1L);
    }
    @Test
    void getPointForUserTestSingleTransactionWith99Amount() {
        Set<Transaction> customerAllTransactions = new HashSet<>();
        customerAllTransactions.add(trans99);
        RewardPoints rewardPoints = RewardsToPointUtil.getPointForUser(customerAllTransactions,101L);
        assertEquals( rewardPoints.getTransactionCount(),1L);
        assertEquals( rewardPoints.getTotalRewardPoints(),49L);
        assertEquals( rewardPoints.getMonthlyRewards().size(),1L);
    }
    @Test
    void getPointForUserTestSingleTransactionWith101Amount() {
        Set<Transaction> customerAllTransactions = new HashSet<>();
        customerAllTransactions.add(trans101);
        RewardPoints rewardPoints = RewardsToPointUtil.getPointForUser(customerAllTransactions,101L);
        assertEquals( rewardPoints.getTransactionCount(),1L);
        assertEquals( rewardPoints.getTotalRewardPoints(),52L);
        assertEquals( rewardPoints.getMonthlyRewards().size(),1L);
    }
    @Test
    void getPointForUserTestSingleTransactionWith300Amount() {
        Set<Transaction> customerAllTransactions = new HashSet<>();
        customerAllTransactions.add(trans300);
        RewardPoints rewardPoints = RewardsToPointUtil.getPointForUser(customerAllTransactions,101L);
        assertEquals( rewardPoints.getTransactionCount(),1L);
        assertEquals( rewardPoints.getTotalRewardPoints(),450L);
        assertEquals( rewardPoints.getMonthlyRewards().size(),1L);
    }
    @Test
    void getPointForUserTestSingleTransactionWithDecimal5() {
        Set<Transaction> customerAllTransactions = new HashSet<>();
        customerAllTransactions.add(transDecimal5);
        RewardPoints rewardPoints = RewardsToPointUtil.getPointForUser(customerAllTransactions,101L);
        assertEquals( rewardPoints.getTransactionCount(),1L);
        assertEquals( rewardPoints.getTotalRewardPoints(),0L);
        assertEquals( rewardPoints.getMonthlyRewards().size(),1L);
    }
    @Test
    void getPointForUserTestSingleTransactionWithDecimal50() {
        Set<Transaction> customerAllTransactions = new HashSet<>();
        customerAllTransactions.add(transDecimal50);
        RewardPoints rewardPoints = RewardsToPointUtil.getPointForUser(customerAllTransactions,101L);
        assertEquals( rewardPoints.getTransactionCount(),1L);
        assertEquals( rewardPoints.getTotalRewardPoints(),0L);
        assertEquals( rewardPoints.getMonthlyRewards().size(),1L);
    }
    @Test
    void getPointForUserTestSingleTransactionWithDecimal55() {
        Set<Transaction> customerAllTransactions = new HashSet<>();
        customerAllTransactions.add(transDecimal55);
        RewardPoints rewardPoints = RewardsToPointUtil.getPointForUser(customerAllTransactions,101L);
        assertEquals( rewardPoints.getTransactionCount(),1L);
        assertEquals( rewardPoints.getTotalRewardPoints(),5L);
        assertEquals( rewardPoints.getMonthlyRewards().size(),1L);
    }
    @Test
    void getPointForUserTestSingleTransactionWithDecimal300() {
        Set<Transaction> customerAllTransactions = new HashSet<>();
        customerAllTransactions.add(transDecimal300);
        RewardPoints rewardPoints = RewardsToPointUtil.getPointForUser(customerAllTransactions,101L);
        assertEquals( rewardPoints.getTransactionCount(),1L);
        assertEquals( rewardPoints.getTotalRewardPoints(),450L);
        assertEquals( rewardPoints.getMonthlyRewards().size(),1L);
    }
    @Test
    void getPointForUserTestMultipleTransactionSameMonth() {
        Set<Transaction> customerAllTransactions = new HashSet<>();
        customerAllTransactions.add(trans10);
        customerAllTransactions.add(trans51);
        RewardPoints rewardPoints = RewardsToPointUtil.getPointForUser(customerAllTransactions,101L);
        assertEquals( rewardPoints.getTransactionCount(),2L);
        assertEquals( rewardPoints.getTotalRewardPoints(),1L);
        assertEquals( rewardPoints.getMonthlyRewards().size(),1L);
        assertTrue( rewardPoints.getMonthlyRewards().containsKey("June"));
        assertEquals(rewardPoints.getMonthlyRewards().get("June"), 1L);
    }
    @Test
    void getPointForUserTestMultipleTransactionAllDifferentMonthTest1() {
        Set<Transaction> customerAllTransactions = new HashSet<>();
        customerAllTransactions.add(trans50);
        customerAllTransactions.add(trans51);
        customerAllTransactions.add(trans99);
        RewardPoints rewardPoints = RewardsToPointUtil.getPointForUser(customerAllTransactions,101L);
        assertEquals( rewardPoints.getTransactionCount(),3L);
        assertEquals( rewardPoints.getTotalRewardPoints(),50L);
        assertEquals( rewardPoints.getMonthlyRewards().size(),3L);
        assertTrue( rewardPoints.getMonthlyRewards().containsKey("June"));
        assertTrue( rewardPoints.getMonthlyRewards().containsKey("April"));
        assertTrue( rewardPoints.getMonthlyRewards().containsKey("July"));
        assertEquals(rewardPoints.getMonthlyRewards().get("June"), 1L);
        assertEquals(rewardPoints.getMonthlyRewards().get("April"), 0L);
        assertEquals(rewardPoints.getMonthlyRewards().get("July"), 49L);
    }
    @Test
    void getPointForUserTestMultipleTransactionAllDifferentMonthTest2() {
        Set<Transaction> customerAllTransactions = new HashSet<>();
        customerAllTransactions.add(trans300);
        customerAllTransactions.add(transDecimal55);
        customerAllTransactions.add(trans99);
        RewardPoints rewardPoints = RewardsToPointUtil.getPointForUser(customerAllTransactions,101L);
        assertEquals( rewardPoints.getTransactionCount(),3L);
        assertEquals( rewardPoints.getTotalRewardPoints(),504L);
        assertEquals( rewardPoints.getMonthlyRewards().size(),3L);
        assertTrue( rewardPoints.getMonthlyRewards().containsKey("June"));
        assertTrue( rewardPoints.getMonthlyRewards().containsKey("December"));
        assertTrue( rewardPoints.getMonthlyRewards().containsKey("July"));
        assertEquals(rewardPoints.getMonthlyRewards().get("June"), 450L);
        assertEquals(rewardPoints.getMonthlyRewards().get("December"), 5L);
        assertEquals(rewardPoints.getMonthlyRewards().get("July"), 49L);
    }
    private Timestamp getTimeStamp(String stringTime) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
        Date date ;
        try {
            date = dateFormat.parse(stringTime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        long time = date.getTime();
        return new Timestamp(time);
    }
}