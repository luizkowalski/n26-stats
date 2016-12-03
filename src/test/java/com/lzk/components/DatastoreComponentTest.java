package com.lzk.components;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lzk.models.Transaction;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatastoreComponentTest {

	
	@Autowired private DatastoreComponent datastoreComponent;
	private Transaction t;
	
	@Before
	public void setUp(){
		t = new Transaction(22D, 1236781678L);
		datastoreComponent.addTransaction(t);
	}
		
	@Test
	public void testTrasactionsNotEmpty(){
		assertThat(datastoreComponent.getTransactions()).isNotEmpty();
	}
	
	@Test
	public void testTransactionListSize(){
		assertThat(datastoreComponent.getTransactions().size()).isEqualTo(1);
	}
	
	@Test
	public void testAddTransactionSameTimestamp(){
		Transaction transactionTwenty = new Transaction(20D, 1236781678L);
		datastoreComponent.addTransaction(transactionTwenty);
		
		assertThat(datastoreComponent.getTransactions().size()).isEqualTo(1);
	}
	
	@Test
	public void testAddTransactionSameTimestampAddToList(){
		Transaction transactionTwenty = new Transaction(20D, 1236781678L);
		datastoreComponent.addTransaction(transactionTwenty);

		assertThat(datastoreComponent.getTransactions().get(1236781678L).size()).isEqualTo(2);
	}
	
	@After
	public void coolDown() {
		datastoreComponent.clearTransactions();
	}
	
	
}
