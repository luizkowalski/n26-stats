package com.lzk.components;

import java.util.List;

import org.springframework.stereotype.Component;

import com.lzk.models.Transaction;

@Component
public class StatsAggregatorComponent {

	private Double sum;
	private Double avg;
	private Double max;
	private Double min;
	private Long count;

	public synchronized void aggreate(List<Transaction> validTransactions) {
		if(validTransactions == null || validTransactions.isEmpty())
			return;
		this.avg = validTransactions.parallelStream().mapToDouble(a -> a.getAmount()).average().getAsDouble();
		this.sum = validTransactions.parallelStream().mapToDouble(a -> a.getAmount()).sum();
		this.max = validTransactions.parallelStream().mapToDouble(a -> a.getAmount()).max().getAsDouble();
		this.min = validTransactions.parallelStream().mapToDouble(a -> a.getAmount()).min().getAsDouble();
		this.count = new Long(validTransactions.size());
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
