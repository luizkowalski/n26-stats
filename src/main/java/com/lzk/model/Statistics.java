package com.lzk.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Statistics implements Serializable{

	private static final long serialVersionUID = -7246465783178141330L;

	private Double sum;
	private Double avg;
	private Double max;
	private Double min;
	private Long count;
}
