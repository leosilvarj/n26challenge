package com.n26.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n26.model.Statistic;
import com.n26.repository.Repository;

@Service
public class StatisticsService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3036872073412129015L;

	@Autowired
	Repository statisticsRepository;
	
	public Statistic getStatistic() {
		return statisticsRepository.getStatistic();
	}
}
