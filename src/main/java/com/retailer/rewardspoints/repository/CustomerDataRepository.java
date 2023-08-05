package com.retailer.rewardspoints.repository;

import com.retailer.rewardspoints.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Acts as database for customer data
 */
@Repository
public class CustomerDataRepository {
    Map<Long, Customer> customerSet;

    CustomerDataRepository() {
        customerSet = new HashMap<>();
        customerSet.put(101L, new Customer(101L, "Amit"));
        customerSet.put(102L, new Customer(102L, "Jack"));
        customerSet.put(103L, new Customer(103L, "Ronaldo"));
        customerSet.put(104L, new Customer(104L, "You"));
        customerSet.put(1010L, new Customer(1010L, "EmptyTest"));
    }

    public Customer findByCustomerId(Long customerId) {
        return customerSet.get(customerId);
    }

    public List<Customer> getAllCustomer() {
        return new ArrayList<>(customerSet.values());
    }
}
