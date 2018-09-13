package com.n26.repository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.n26.model.Statistic;
import com.n26.model.Transaction;

@Component
public class Repository implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 2746086646272300347L;

    private List<Transaction> transactions = new ArrayList<>();

    private static final Long ONE_MINUTE_IN_MILLISEC = 60 * 1000l;

    public boolean addTransaction(Transaction transaction) {
        try {
            if (transactions == null) {
                transactions = new ArrayList<>();
            }

            transactions.add(transaction);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void removeAllTransaction() {
        transactions = new ArrayList<>();
    }

    public Statistic getStatistic() {
        Statistic s = new Statistic();
        List<Transaction> lastTransaction = getLastTransactions();
        if (lastTransaction != null && !lastTransaction.isEmpty()) {
            s.setCount(lastTransaction.size());
            s.setSum(new BigDecimal(lastTransaction.stream().mapToDouble(d -> d.getAmount().doubleValue()).sum())
                    .setScale(2, BigDecimal.ROUND_HALF_UP));
            s.setMax(new BigDecimal(lastTransaction.stream().mapToDouble(d -> d.getAmount().doubleValue())
                    .reduce(Double::max).getAsDouble()).setScale(2, BigDecimal.ROUND_HALF_UP));
            s.setMin(new BigDecimal(lastTransaction.stream().mapToDouble(d -> d.getAmount().doubleValue())
                    .reduce(Double::min).getAsDouble()).setScale(2, BigDecimal.ROUND_HALF_UP));

            if (s.getCount() > 0) {
                s.setAvg(s.getSum().divide(new BigDecimal(s.getCount()), BigDecimal.ROUND_HALF_UP).setScale(2));
            } else {
                s.setAvg(new BigDecimal(0).setScale(2));
            }
            return s;
        } else {
            s.setCount(0);
            s.setSum(new BigDecimal(0).setScale(2));
            s.setMax(new BigDecimal(0).setScale(2));
            s.setMin(new BigDecimal(0).setScale(2));
            s.setAvg(new BigDecimal(0).setScale(2));
        }
        return s;
    }

    private List<Transaction> getLastTransactions() {
        return findLast60MinutesTransaction(transactions.iterator(), isOlderThanOneMinute());
    }

    private Predicate<Transaction> isOlderThanOneMinute() {
        final ZonedDateTime utcTimeZone = ZonedDateTime.now(ZoneOffset.UTC);
        final long epochInMilliSec = utcTimeZone.toEpochSecond() * 1000;
        return transaction -> transaction.getTimestamp() > epochInMilliSec - ONE_MINUTE_IN_MILLISEC;
    }

    private List<Transaction> findLast60MinutesTransaction(final Iterator<Transaction> iterator,
            final Predicate<Transaction> predicate) {
        List<Transaction> lastMinutesList = new ArrayList<Transaction>();
        while (iterator.hasNext()) {
            final Transaction e = iterator.next();
            if (predicate.test(e)) {
                lastMinutesList.add(e);
            }
        }
        return lastMinutesList;
    }
}
