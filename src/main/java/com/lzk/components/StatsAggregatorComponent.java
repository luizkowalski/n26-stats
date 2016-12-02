package com.lzk.components;

import java.util.List;
import java.util.stream.DoubleStream;

import org.springframework.stereotype.Component;

import com.lzk.model.Transaction;

@Component
public class StatsAggregatorComponent {

	private Double sum;
	private Double avg;
	private Double max;
	private Double min;
	private Long count;

	public synchronized void aggreate(List<Transaction> validTransactions) {
		DoubleStream doubleStream = validTransactions.parallelStream().mapToDouble(a -> a.getAmount());
		this.avg = doubleStream.average().getAsDouble();
		this.sum = doubleStream.sum();
		this.max = doubleStream.max().getAsDouble();
		this.min = doubleStream.min().getAsDouble();
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
