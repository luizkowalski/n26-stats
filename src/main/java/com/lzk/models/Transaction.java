package com.lzk.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Transaction implements Serializable{

	private static final long serialVersionUID = 4782612550980962226L;

	private Double amount;
	private Long timestamp;
	
	
}
