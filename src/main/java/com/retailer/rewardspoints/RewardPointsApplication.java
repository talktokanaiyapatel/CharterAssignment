package com.retailer.rewardspoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class RewardPointsApplication {

    public static void main(String[] args) {
        final Logger log =
                LoggerFactory.getLogger(RewardPointsApplication.class);
        log.info("Starting RewardPointsApplication...");
        SpringApplication.run(RewardPointsApplication.class, args);
        log.debug("Starting RewardPointsApplication in DEBUG mode...");
        log.info("Starting RewardPointsApplication with {} args.", Arrays.toString(args));

    }

}
