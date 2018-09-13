package com.n26.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.n26.dto.StatisticDto;
import com.n26.model.Statistic;
import com.n26.service.StatisticsService;

@RestController
public class StatisticsController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6346339821844945825L;
    @Autowired
    StatisticsService statisticsService;

    @RequestMapping(value = "/statistics", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public StatisticDto getStatistics() {
        return converterToDto(statisticsService.getStatistic());
    }

    private StatisticDto converterToDto(Statistic statistic) {
        StatisticDto dto = new StatisticDto();
        dto.setAvg(statistic.getAvg().toString());
        dto.setCount(statistic.getCount());
        dto.setMax(statistic.getMax().toString());
        dto.setMin(statistic.getMin().toString());
        dto.setSum(statistic.getSum().toString());
        return dto;
    }

}
