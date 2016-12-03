package com.lzk.components;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

import org.springframework.stereotype.Component;

import com.lzk.model.Transaction;

import lombok.extern.java.Log;

@Component
@Log
public class DatastoreComponent {
	
	private ConcurrentNavigableMap<Long, List<Transaction>> transactions;
	
	public DatastoreComponent() {
		this.transactions = new ConcurrentSkipListMap<>();
	}
	
	public synchronized void addTransaction(Transaction t){
		log.info("Adding transaction: "+t);
		List<Transaction> transactionAtGivenTime = transactions.get(t.getTimestamp());
		if(transactionAtGivenTime == null) transactionAtGivenTime = new ArrayList<>();
		transactionAtGivenTime.add(t);
		transactions.put(t.getTimestamp(), transactionAtGivenTime);
	}

	public ConcurrentNavigableMap<Long, List<Transaction>> getTransactions() {
		return transactions;
	}
	
}
