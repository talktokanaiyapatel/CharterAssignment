package com.retailer.rewardspoints.repository;

import com.retailer.rewardspoints.entity.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Acts as database for Transaction data
 */
@Repository
public class TransactionDataRepository {
    Map<Long, Set<Transaction>> transactionsCatalog;

    TransactionDataRepository() {
        transactionsCatalog = new HashMap<>();
        addToCatalogue(new Transaction(20021L, 101L, getTimeStamp("23/06/2023 19:34:05.123"), 0));
        addToCatalogue(new Transaction(20031L, 101L, getTimeStamp("24/07/2023 18:48:05.123"), 50));
        addToCatalogue(new Transaction(20041L, 101L, getTimeStamp("23/07/2023 12:48:05.123"), 75));
        addToCatalogue(new Transaction(20051L, 101L, getTimeStamp("24/06/2023 10:45:05.123"), 110));
        addToCatalogue(new Transaction(20061L, 101L, getTimeStamp("23/06/2001 19:34:05.123"), 0));
        addToCatalogue(new Transaction(20071L, 101L, getTimeStamp("23/07/2002 12:48:05.123"), 75));

        addToCatalogue(new Transaction(2001L, 102L, getTimeStamp("23/06/2023 18:56:05.123"), 100));
        addToCatalogue(new Transaction(2002L, 102L, getTimeStamp("24/06/2023 12:23:05.122"), 120));
        addToCatalogue(new Transaction(2003L, 102L, getTimeStamp("20/03/2023 18:44:20.097"), 400));
        addToCatalogue(new Transaction(2004L, 102L, getTimeStamp("24/05/2023 02:48:21.098"), 51));
        addToCatalogue(new Transaction(2005L, 102L, getTimeStamp("23/06/2023 05:48:23.123"), 101));

        addToCatalogue(new Transaction(2006L, 103L, getTimeStamp("24/05/2023 18:48:32.123"), 5));
        addToCatalogue(new Transaction(2007L, 103L, getTimeStamp("15/04/2023 18:44:05.122"), 10));
        addToCatalogue(new Transaction(2008L, 103L, getTimeStamp("03/05/2023 18:34:05.121"), 55));
        addToCatalogue(new Transaction(2009L, 103L, getTimeStamp("23/06/2023 18:52:05.100"), 150));
        addToCatalogue(new Transaction(20010L, 103L, getTimeStamp("12/06/2023 18:33:05.123"), 400));
        addToCatalogue(new Transaction(20011L, 104L, getTimeStamp("02/06/2023 18:25:20.123"), 42));
        addToCatalogue(new Transaction(20012L, 104L, getTimeStamp("15/05/2023 18:05:19.123"), 175));
        addToCatalogue(new Transaction(20013L, 104L, getTimeStamp("02/05/2023 20:06:18.123"), 15));
        addToCatalogue(new Transaction(20014L, 104L, getTimeStamp("01/07/2023 22:08:10.123"), 56));
        addToCatalogue(new Transaction(20015L, 104L, getTimeStamp("25/05/2023 23:09:16.123"), 99));
        addToCatalogue(new Transaction(20016L, 104L, getTimeStamp("14/06/2023 05:01:17.123"), 110));
    }

    // returns set of transaction. Never returns null.
    public Set<Transaction> findAllByCustomerId(Long customerId) {
        return transactionsCatalog.get(customerId) != null ? transactionsCatalog.get(customerId) : Collections.emptySet();
    }

    private Timestamp getTimeStamp(String stringTime) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
        Date date;
        try {
            date = dateFormat.parse(stringTime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        long time = date.getTime();
        return new Timestamp(time);
    }

    private void addToCatalogue(Transaction transaction) {
        transactionsCatalog.putIfAbsent(transaction.getCustomerId(), new HashSet<>());
        transactionsCatalog.get(transaction.getCustomerId()).add(transaction);
    }
}
