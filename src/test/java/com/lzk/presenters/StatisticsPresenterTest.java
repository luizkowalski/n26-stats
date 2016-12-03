package com.lzk.presenters;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.lzk.presenter.StatisticsPresenter;

@RunWith(JUnit4.class)
public class StatisticsPresenterTest {

	private StatisticsPresenter presenter = null;
	
	@Before
	public void before(){
		presenter = new StatisticsPresenter(10D, 11D, 13D, 12D, 14L);
	}
	
	@Test
	public void assertPresentWorking(){
		assertThat(10D).isEqualTo(presenter.present().getSum());
		assertThat(11D).isEqualTo(presenter.present().getAvg());
		assertThat(13D).isEqualTo(presenter.present().getMax());
		assertThat(12D).isEqualTo(presenter.present().getMin());
		assertThat(14L).isEqualTo(presenter.present().getCount());
	}
}
