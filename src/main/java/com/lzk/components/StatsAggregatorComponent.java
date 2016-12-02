package com.lzk.components;

import java.util.List;

import org.springframework.stereotype.Component;

import com.lzk.model.Statistics;
import com.lzk.model.Transaction;
import com.lzk.presenter.StatsPresenter;

import lombok.extern.java.Log;

@Component
@Log
public class StatsAggregatorComponent {

	private Double sum;
	private Double avg;
	private Double max;
	private Double min;
	private Long count;

	public synchronized void aggreate(List<Transaction> validTransactions) {
		log.info("Valid transactions: "+validTransactions);
		this.avg = validTransactions.parallelStream().mapToDouble(a -> a.getAmount()).average().getAsDouble();
		this.sum = validTransactions.parallelStream().mapToDouble(a -> a.getAmount()).sum();
		this.max = validTransactions.parallelStream().mapToDouble(a -> a.getAmount()).max().getAsDouble();
		this.min = validTransactions.parallelStream().mapToDouble(a -> a.getAmount()).min().getAsDouble();
		this.count = new Long(validTransactions.size());
	}
	
	public Statistics getResult(){
		return new StatsPresenter(sum, avg, max, min, count).present();
	}
		
	public Double getSum() {
		return sum;
	}
	
	public Double getAvg() {
		return avg;
	}
	
	public Double getMax() {
		return max;
	}
	
	public Double getMin() {
		return min;
	}
	
	public Long getCount() {
		return count;
	}

}
