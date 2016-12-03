package com.lzk.components;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.lzk.models.Transaction;

@Component
public class StatsAggregatorComponent {

	private Double sum = 0D;
	private Double avg = 0D;
	private Double max = 0D;
	private Double min = 0D;
	private Long count = 0L;

	public synchronized void aggreate(List<Transaction> validTransactions) {
		if(validTransactions == null || validTransactions.isEmpty())
			return;

		DoubleSummaryStatistics stats = validTransactions
				.parallelStream().collect(Collectors.summarizingDouble(Transaction::getAmount));

		this.avg = stats.getAverage();
		this.sum = stats.getSum();
		this.max = stats.getMax();
		this.min = stats.getMin();
		this.count = stats.getCount();
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
