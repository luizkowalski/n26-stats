package com.lzk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lzk.components.DatastoreComponent;

@Controller
public class StatisticsController {

	private DatastoreComponent datastoreComponent;
	
	public StatisticsController(@Autowired DatastoreComponent component) {
		this.datastoreComponent = component;
	}
	
//	@GetMapping("/statistics")
//	public ResponseEntity<Collection<Transaction>> getStatistics(){
//		return ResponseEntity.ok(datastoreComponent.getTransactions());
//	}
}
