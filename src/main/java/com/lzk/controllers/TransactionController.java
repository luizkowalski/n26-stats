package com.lzk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lzk.components.DatastoreComponent;
import com.lzk.models.Transaction;

@Controller
public class TransactionController {

	private DatastoreComponent dataStore;
	
	public TransactionController(@Autowired DatastoreComponent dataStore) {
		this.dataStore = dataStore;
	}
	
	@PostMapping("/transactions")
	public ResponseEntity<Void> addTransaction(@RequestBody Transaction transaction){
		dataStore.addTransaction(transaction);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
