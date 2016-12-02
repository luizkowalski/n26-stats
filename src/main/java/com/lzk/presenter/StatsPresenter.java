package com.lzk.presenter;

import com.lzk.model.Statistics;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StatsPresenter {

	private Double sum;
	private Double avg;
	private Double max;
	private Double min;
	private Long count;
	
	
	public Statistics present(){
		return new Statistics(sum, avg, max, min, count);
	}

}
