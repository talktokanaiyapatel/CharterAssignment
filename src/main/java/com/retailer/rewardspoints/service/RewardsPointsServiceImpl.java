package com.retailer.rewardspoints.service;

import com.retailer.rewardspoints.model.RewardPoints;
import com.retailer.rewardspoints.repository.TransactionDataRepository;
import com.retailer.rewardspoints.util.RewardsToPointUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RewardsPointsServiceImpl implements RewardsPointService {


    @Autowired
    TransactionDataRepository transactionRepository;

    /**
     * Creates the rewards-points (response object). It encapsulates interaction with transaction repository.
     *
     * @param customerId-customerID
     * @return rewards points
     */
    public RewardPoints getRewardsByCustomerId(Long customerId) {
        //Getting all data for individual customer based on ID.
        return RewardsToPointUtil.getPointForUser(transactionRepository.findAllByCustomerId(customerId),customerId);
    }
}