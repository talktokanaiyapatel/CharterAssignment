`# Rewards Point 
Problem Statement:

A retailer offers a rewards program to its customers, awarding points based on each recorded purchase.
A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction
(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).
Given a record of every transaction during a three month period, calculate the reward points earned for each customer per month and total

Assumptions:
- Transaction amount can be decimal. This program ignores decimal part for points conversion.
- This program delivers Monthly count for all transactions present. It just not limited to just 3 months. it will aggregate for all months present.
- This program ignores year part for points calculation. so transactions having different year but same month will be clubbed together.

Program behaviour:

- Exception is thrown if customer does not exists. But there is exception handler which responds with user friendly message and send link to get all customer present.
- User response had totalTransactioncount to help customer understand that how many transactions are there.
- Data is already encoded in two repository classes.Modify and add data in below two classes if you need additional data.
        - CustomerDataRepository.java
        - TransactionDataRepository
- Testcases are written in the com/retailer/rewardspoints/util/RewardsToPointUtilTest.java
- HealthCheck-Integration test are in src/test/java/HealthCheckTest.java
- Global Exception handler - If any other exceptions arise, they are handled globally. see com/retailer/rewardspoints/errorHandling/RestErrorHandler.java
- Java lambda and stream APIs have been used for calculations.
- Not used any authentication and authorisation mechanism. There are no tokens used for session.
- Since we are dealing with single domain, no need to use CORS headers.


How to Run:

- Go to https://github.com/talktokanaiyapatel/CharterAssignment
- Select master branch.
- click on Code (green button) and select 'Download ZIP'
- Extract zip file in local folder
- import folder in intellij
- Then run file: com/retailer/rewardspoints/RewardPointsApplication.java

Alternatively, You can use git url : https://github.com/talktokanaiyapatel/CharterAssignment.git to import project in intellij.

Sample Urls:

Existing customers:

http://localhost:8080/rewardPoints/customer/101/points

{"customerId":101,"totalRewardPoints":120,"transactionCount":6,"monthlyRewards":{"June":70,"July":50}}

http://localhost:8080/rewardPoints/customer/102/points

{"customerId":102,"totalRewardPoints":843,"transactionCount":5,"monthlyRewards":{"June":192,"May":1,"March":650}}

http://localhost:8080/rewardPoints/customer/103/points

{"customerId":103,"totalRewardPoints":805,"transactionCount":5,"monthlyRewards":{"June":800,"May":5,"April":0}}

http://localhost:8080/rewardPoints/customer/104/points

{"customerId":104,"totalRewardPoints":325,"transactionCount":6,"monthlyRewards":{"June":70,"May":249,"July":6}}

http://localhost:8080/rewardPoints/customer/1010/points (customer without transactions)
{"customerId":1010,"totalRewardPoints":0,"transactionCount":0,"monthlyRewards":{}}


**Unavailable customer:**

http://localhost:8080/rewardPoints/customer/1018/points

{"errorMessage":"CustomerID: 1018 Sorry We could not find this customer. Please add valid customer ID","suggestedAction":"Please add data into com.retailer.rewards.repository.CustomerRepository class for customer.Also add corresponding Transaction records for customer into com.retailer.rewards.repository.TransactionRepository class. currently available customer can be found @ http://localhost:8080/rewardPoints/customers/ "}


ALL CUSTOMER INFO: List all available customers.

http://localhost:8080/rewardPoints/customers

[{"customerId":1010,"customerName":"EmptyTest"},{"customerId":101,"customerName":"Amit"},{"customerId":102,"customerName":"Jack"},{"customerId":103,"customerName":"Ronaldo"},{"customerId":104,"customerName":"You"}]




`