package com.lzk.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Transaction implements Serializable{

	private static final long serialVersionUID = 4782612550980962226L;

	private Double amount;
	private Long timestamp;
}
