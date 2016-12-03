package com.lzk.presenter;

import com.lzk.models.Statistic;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StatisticsPresenter {

	private Double sum;
	private Double avg;
	private Double max;
	private Double min;
	private Long count;
	
	
	public Statistic present(){
		return new Statistic(sum, avg, max, min, count);
	}

}
