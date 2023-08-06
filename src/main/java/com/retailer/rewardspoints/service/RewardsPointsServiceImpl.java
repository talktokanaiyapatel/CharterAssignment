package com.retailer.rewardspoints.service;

import com.retailer.rewardspoints.entity.Transaction;
import com.retailer.rewardspoints.model.RewardPoints;
import com.retailer.rewardspoints.repository.TransactionDataRepository;
import com.retailer.rewardspoints.util.RewardsToPointUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RewardsPointsServiceImpl implements RewardsPointService {
    final Logger log = LoggerFactory.getLogger(RewardsPointsServiceImpl.class);

    @Autowired
    TransactionDataRepository transactionRepository;

    /**
     * Creates the rewards-points (response object). It encapsulates interaction with transaction repository.
     *
     * @param customerId-customerID
     * @return rewards points
     */
    public RewardPoints getRewardsByCustomerId(Long customerId) {
        log.info(" calling rewards service for customer {}",customerId);
        // Fetching all transactions for customer
        Set<Transaction> transactionsSet = transactionRepository.findAllByCustomerId(customerId);
        log.debug("Fetched all transaction for user: {} all transactions: {}",customerId,transactionsSet);
        //Getting all data for individual customer based on ID.
        return RewardsToPointUtil.getPointForUser(transactionsSet,customerId);
    }
}