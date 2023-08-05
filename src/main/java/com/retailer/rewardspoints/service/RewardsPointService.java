package com.retailer.rewardspoints.service;

import com.retailer.rewardspoints.model.RewardPoints;


public interface RewardsPointService {
    RewardPoints getRewardsByCustomerId(Long customerId);
}
