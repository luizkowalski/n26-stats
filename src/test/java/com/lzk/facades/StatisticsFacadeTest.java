package com.lzk.facades;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lzk.components.DatastoreComponent;
import com.lzk.facade.StatisticsFacade;
import com.lzk.models.Statistic;
import com.lzk.models.Transaction;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatisticsFacadeTest {

	@Autowired private StatisticsFacade facade;
	@Autowired DatastoreComponent dataStore; // For setup the tests
	private Statistic stats;
	
	@Before
	public void setUp(){
		createTransactions();
		createOldTransactions();
		stats = facade.aggregate().andPresent();
	}
	
	@Test
	public void testAvg(){
		assertThat(stats.getAvg()).isEqualTo(18.75D);
	}
	
	@Test
	public void testMax(){
		assertThat(stats.getMax()).isEqualTo(30D);
	}
	
	@Test
	public void testMin(){
		assertThat(stats.getMin()).isEqualTo(10D);
	}
	
	@Test
	public void testSum(){
		assertThat(stats.getSum()).isEqualTo(150D);
	}
	
	@Test
	public void testCount(){
		assertThat(stats.getCount()).isEqualTo(8L);
	}
	
	@After
	public void coolDown(){
		dataStore.clearTransactions();
	}

	// These transactions should not influence in the value
	private void createOldTransactions() {
		List<Transaction> transactions = Arrays.asList(
				new Transaction(10D, timeAgo(64)),new Transaction(10D, timeAgo(100)),
				new Transaction(15D, timeAgo(64)),new Transaction(15D, timeAgo(67)),
				new Transaction(20D, timeAgo(78)),new Transaction(20D, timeAgo(90)),
				new Transaction(30D, timeAgo(89)),new Transaction(30D, timeAgo(88)));
		transactions.stream().forEach(t -> dataStore.addTransaction(t));
	}

	private void createTransactions() {
		List<Transaction> transactions = Arrays.asList(
				new Transaction(10D, timeAgo(20)),new Transaction(10D, timeAgo(11)),
				new Transaction(15D, timeAgo(22)),new Transaction(15D, timeAgo(22)),
				new Transaction(20D, timeAgo(44)),new Transaction(20D, timeAgo(44)),
				new Transaction(30D, timeAgo(55)),new Transaction(30D, timeAgo(55)));
		transactions.stream().forEach(t -> dataStore.addTransaction(t));
	}
	
	private long timeAgo(long time){
		return Instant.now().minusSeconds(time).toEpochMilli();
	}
}
