package com.retailer.rewardspoints.controller;

import com.retailer.rewardspoints.entity.Customer;
import com.retailer.rewardspoints.errorHandling.ResourceNotFoundException;
import com.retailer.rewardspoints.model.RewardPoints;
import com.retailer.rewardspoints.repository.CustomerDataRepository;
import com.retailer.rewardspoints.service.RewardsPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rewardPoints")
public class RewardPointsController {

    @Autowired
    RewardsPointService rewardsPointService;

    @Autowired
    CustomerDataRepository customerRepository;

    /**
     * handler for returning points for single customer
     *
     * @param customerId - customer ID
     * @return rewards points by month for customer
     * @throws ResourceNotFoundException if no customer found
     */
    @GetMapping(value = "/customer/{customerId}/points", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RewardPoints> getRewardsByCustomerId(@PathVariable("customerId") Long customerId) throws ResourceNotFoundException {
        Customer customer = customerRepository.findByCustomerId(customerId);
        if (customer == null) {
            throw new ResourceNotFoundException(customerId, "Sorry We could not find this customer. Please add valid customer ID");
        }
        RewardPoints customerRewardPoints = rewardsPointService.getRewardsByCustomerId(customerId);
        return new ResponseEntity<>(customerRewardPoints, HttpStatus.OK);
    }

    /**
     * handler for getting all customer info.
     *
     * @return customer list
     */
    @GetMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllCustomer() {
        return new ResponseEntity<>(customerRepository.getAllCustomer(), HttpStatus.OK);
    }

}
