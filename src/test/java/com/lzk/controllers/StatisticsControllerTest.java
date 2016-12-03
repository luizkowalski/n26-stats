package com.lzk.controllers;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;

import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.lzk.components.DatastoreComponent;
import com.lzk.models.Statistic;
import com.lzk.models.Transaction;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class StatisticsControllerTest {

	@Value("${local.server.port}")
	private int port;

	@Autowired private DatastoreComponent datastoreComponent;

	@Before
	public void setUp() {
		RestAssured.port = port;
		Arrays.asList(new Transaction(20D, timeAgo(20)), new Transaction(40D, timeAgo(30)))
				.stream()
				.forEach(t -> datastoreComponent.addTransaction(t));
	}
	
	@Test
	public void testCallOk(){
		Response resp = callStatsEndpoint();
		Assertions.assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
	}
	
	@Test
	public void testResponseAvg() throws JsonProcessingException, IOException{
		Response resp = callStatsEndpoint();
		Statistic result = new ObjectMapper().readValue(resp.getBody().asString(), Statistic.class);
		Assertions.assertThat(result.getAvg()).isEqualTo(30D);
	}
	
	@Test
	public void testResponseSum() throws JsonProcessingException, IOException{
		Response resp = callStatsEndpoint();
		Statistic result = new ObjectMapper().readValue(resp.getBody().asString(), Statistic.class);
		Assertions.assertThat(result.getSum()).isEqualTo(60D);
	}
	
	@Test
	public void testResponseCount() throws JsonProcessingException, IOException{
		Response resp = callStatsEndpoint();
		Statistic result = new ObjectMapper().readValue(resp.getBody().asString(), Statistic.class);
		Assertions.assertThat(result.getCount()).isEqualTo(2L);
	}
	
	@Test
	public void testResponseMax() throws JsonProcessingException, IOException{
		Response resp = callStatsEndpoint();
		Statistic result = new ObjectMapper().readValue(resp.getBody().asString(), Statistic.class);
		Assertions.assertThat(result.getMax()).isEqualTo(40D);
	}

	@Test
	public void testResponseMin() throws JsonProcessingException, IOException{
		Response resp = callStatsEndpoint();
		Statistic result = new ObjectMapper().readValue(resp.getBody().asString(), Statistic.class);
		Assertions.assertThat(result.getMin()).isEqualTo(20D);
	}
	
	@After
	public void coolDown() {
		datastoreComponent.clearTransactions();
	}
	
	private Response callStatsEndpoint() {
		return given().contentType("application/json")
			.when().get("/statistics");
	}
	
	private long timeAgo(long time){
		return Instant.now().minusSeconds(time).toEpochMilli();
	}
}
