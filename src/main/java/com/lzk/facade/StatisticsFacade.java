package com.lzk.facade;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.lzk.components.DatastoreComponent;
import com.lzk.components.StatsAggregatorComponent;
import com.lzk.models.Statistic;
import com.lzk.models.Transaction;
import com.lzk.presenter.StatisticsPresenter;

@Component
@Scope("prototype")
public class StatisticsFacade {

	@Autowired DatastoreComponent dataStore;
	@Autowired StatsAggregatorComponent statsAgg;
	
	public StatisticsFacade aggregate(){
		List<Transaction> validTransactions = new ArrayList<>();
		dataStore.getTransactions().tailMap(sixtySecondsAgo())
						.values()
						.parallelStream()
						.forEach(list -> validTransactions.addAll(list));
		statsAgg.aggreate(validTransactions);
		return this;
	}
	
	public Statistic andPresent(){
		return new StatisticsPresenter(statsAgg.getSum(), statsAgg.getAvg(), statsAgg.getMax(), statsAgg.getMin(), statsAgg.getCount()).present();
	}
	
	private Long sixtySecondsAgo(){
		return Instant.now().minusSeconds(60L).toEpochMilli();
	}
}
