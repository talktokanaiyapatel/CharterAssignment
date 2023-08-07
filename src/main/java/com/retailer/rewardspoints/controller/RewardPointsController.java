package com.retailer.rewardspoints.controller;

import com.retailer.rewardspoints.entity.Customer;
import com.retailer.rewardspoints.errorHandling.ResourceNotFoundException;
import com.retailer.rewardspoints.model.RewardPoints;
import com.retailer.rewardspoints.repository.CustomerDataRepository;
import com.retailer.rewardspoints.service.RewardsPointService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/rewardPoints")
public class RewardPointsController {

    final Logger log = LoggerFactory.getLogger(RewardPointsController.class);

    @Autowired
    RewardsPointService rewardsPointService;

    @Autowired
    CustomerDataRepository customerRepository;

    @Value("${points.resource.not.found.message}")
    private String resourceNotFoundMessage;

    /**
     * handler for returning points for single customer
     *
     * @param customerId - customer ID
     * @return rewards points by month for customer
     * @throws ResourceNotFoundException if no customer found
     */
    @GetMapping(value = "/customer/{customerId}/points", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RewardPoints> getRewardsByCustomerId(@PathVariable("customerId") Long customerId) throws ResourceNotFoundException {
        log.debug(" User sent customerId {} ", customerId);
        Customer customer = customerRepository.findByCustomerId(customerId);
        log.debug(" customer found in repository?   {} ", Objects.nonNull(customerId));
        if (customer == null) {
            log.debug(" customer not found. throwing ResourceNotFoundException");
            throw new ResourceNotFoundException(customerId, resourceNotFoundMessage);
        }
        RewardPoints customerRewardPoints = rewardsPointService.getRewardsByCustomerId(customerId);
        log.debug(" Returning Rewards points object for customer {} with data {}", customerId, customerRewardPoints);
        return new ResponseEntity<>(customerRewardPoints, HttpStatus.OK);
    }

    /**
     * handler for getting all customer info.
     *
     * @return customer list
     */
    @GetMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllCustomer() {
        log.info("Calling getAllCustomers...");
        List<Customer> returnList = customerRepository.getAllCustomer();
        log.debug(" Returning getAllCustomers with data {}", returnList);
        return new ResponseEntity<>(returnList, HttpStatus.OK);
    }

}
