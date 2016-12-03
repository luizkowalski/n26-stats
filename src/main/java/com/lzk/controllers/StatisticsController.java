package com.lzk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.lzk.facade.StatisticsFacade;
import com.lzk.models.Statistic;

@Controller
public class StatisticsController {

	private StatisticsFacade facade;

	public StatisticsController(@Autowired StatisticsFacade facade) {
		this.facade = facade;
	}

	@GetMapping(path="/statistics", produces = "application/json")
	public ResponseEntity<Statistic> getStatistics(){
		return ResponseEntity.ok(facade.aggregate().andPresent());
	}
}
