package com.n26.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;

import com.n26.model.Statistic;
import com.n26.model.Transaction;
import com.n26.service.StatisticsService;
import com.n26.service.TransactionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatisticsControllerTest extends RestControllerHelper {

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private TransactionService transactionService;

    @Before
    public void setUp() {
        transactionService.removeAllTransaction();
    }

    @Test
    public void testGetComputedStatisticsWithSuccessResponse() throws Exception {
        transactionService.removeAllTransaction();
        transactionService.addTransaction(new Transaction(new BigDecimal(8.5), new Long(System.currentTimeMillis() - 6000)));
        transactionService.addTransaction(new Transaction(new BigDecimal(15.5), new Long(System.currentTimeMillis() - 100)));
        transactionService.addTransaction(new Transaction(new BigDecimal(20.2), new Long(System.currentTimeMillis() - 500)));
        transactionService.addTransaction(new Transaction(new BigDecimal(5.8), new Long(System.currentTimeMillis() - 300)));
        transactionService.addTransaction(new Transaction(new BigDecimal(3.6), new Long(System.currentTimeMillis() - 500)));
        transactionService.addTransaction(new Transaction(new BigDecimal(2.4), new Long(System.currentTimeMillis() - 500)));
        transactionService.addTransaction(new Transaction(new BigDecimal(9.5), new Long(System.currentTimeMillis() - 500)));
        transactionService.addTransaction(new Transaction(new BigDecimal(12.5), new Long(System.currentTimeMillis() - 000)));

        final Statistic statisticsData = statisticsService.getStatistic();
        assertEquals(statisticsData.getSum().doubleValue(), 78.00, 0.0);
        assertEquals(statisticsData.getMax().doubleValue(), 20.20, 0.0);
        assertEquals(statisticsData.getMin().doubleValue(), 2.40, 0.0);
        assertEquals(statisticsData.getAvg().doubleValue(), 9.75, 0.00);
        assertEquals(statisticsData.getCount(), 8l);

        getStatistics().andExpect(status().isOk());
    }

    @Test
    public void testZeroStatistics() throws Exception {
        transactionService.removeAllTransaction();
        Statistic statisticsData = statisticsService.getStatistic();
        assertEquals(statisticsData.getSum().doubleValue(), 0, 0.0);
        assertEquals(statisticsData.getMax().doubleValue(), 0, 0.0);
        assertEquals(statisticsData.getMin().doubleValue(), 0, 0.0);
        assertEquals(statisticsData.getAvg().doubleValue(), 0, 0.0);
        assertEquals(statisticsData.getCount(), 0);

        getStatistics().andExpect(status().isOk());
    }


    private ResultActions getStatistics() throws Exception {
        return getMethod("/statistics");
    }
}
