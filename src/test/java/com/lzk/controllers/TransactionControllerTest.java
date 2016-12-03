package com.lzk.controllers;

import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.lzk.models.Transaction;

import static com.jayway.restassured.RestAssured.given;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class TransactionControllerTest {
	
	@Value("${local.server.port}")
	private int port;
	
	private String transaction;

	@Before
	public void setUp(){
		try {
			transaction = new ObjectMapper().writeValueAsString(new Transaction(10D, 123123L));
			RestAssured.port = port;
		} catch (JsonProcessingException e) {
			Assertions.fail(e.getMessage());
		}
	}

	@Test
	public void testCall(){
		Response resp = given().contentType("application/json")
			.body(transaction).when().post("/transactions");
		
		Assertions.assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.SC_CREATED);
	}
}
