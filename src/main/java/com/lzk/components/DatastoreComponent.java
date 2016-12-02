package com.lzk.components;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lzk.model.Transaction;

@Component
public class DatastoreComponent {
	
	private ConcurrentNavigableMap<Long, List<Transaction>> transactions;
	private StatsAggregatorComponent aggregator;
	
	public DatastoreComponent(@Autowired StatsAggregatorComponent aggreator) {
		this.transactions = new ConcurrentSkipListMap<>();
		this.aggregator = aggreator;
	}
	
	public synchronized void addTransaction(Transaction t){
		List<Transaction> transactionAtGivenTime = transactions.get(t.getTimestamp());
		if(transactionAtGivenTime == null) transactionAtGivenTime = new ArrayList<>();
		transactionAtGivenTime.add(t);
		transactions.put(t.getTimestamp(), transactionAtGivenTime);
		aggregate();
	}

	private void aggregate() {
		List<Transaction> validTransactions = new ArrayList<>();
		transactions.tailMap(sixtySeconds())
						.values()
						.parallelStream()
						.forEach(list -> validTransactions.addAll(list));
		aggregator.aggreate(validTransactions);
	}
	
	private Long sixtySeconds(){
		return Instant.now().minusSeconds(60L).toEpochMilli();
	}
	
	
	
}
