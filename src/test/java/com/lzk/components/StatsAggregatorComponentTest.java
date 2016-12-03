package com.lzk.components;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lzk.models.Transaction;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatsAggregatorComponentTest {

	@Autowired StatsAggregatorComponent statsAgg;
	private List<Transaction> transactionsToBeAggregated;
	private Long timestamp;
	
	@Before
	public void setUp(){
		timestamp = Instant.now().minusSeconds(20L).toEpochMilli();
		transactionsToBeAggregated = Arrays.asList(new Transaction(10D, timestamp),
																 new Transaction(20D, timestamp),
																 new Transaction(20D, timestamp),
																 new Transaction(30D, timestamp));
		statsAgg.aggreate(transactionsToBeAggregated);
	}
	
	@Test
	public void testAvg(){
		assertThat(20D).isEqualTo(statsAgg.getAvg());
	}
	
	@Test
	public void testMax(){
		assertThat(30D).isEqualTo(statsAgg.getMax());
	}
	
	@Test
	public void testMin(){
		assertThat(10D).isEqualTo(statsAgg.getMin());
	}
	
	@Test
	public void testSum(){
		assertThat(80D).isEqualTo(statsAgg.getSum());
	}
	
	@Test
	public void testCount(){
		assertThat(4L).isEqualTo(statsAgg.getCount());
	}
}
