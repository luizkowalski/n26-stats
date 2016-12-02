package com.lzk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.lzk.components.StatsAggregatorComponent;
import com.lzk.model.Statistics;

@Controller
public class StatisticsController {

	private StatsAggregatorComponent aggregator;
	
	public StatisticsController(@Autowired StatsAggregatorComponent aggregator) {
		this.aggregator = aggregator;
	}
	
	@GetMapping("/statistics")
	public ResponseEntity<Statistics> getStatistics(){
		return ResponseEntity.ok(aggregator.getResult());
	}
}
