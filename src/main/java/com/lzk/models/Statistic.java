package com.lzk.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Statistic implements Serializable{

	private static final long serialVersionUID = -7246465783178141330L;

	private Double sum;
	private Double avg;
	private Double max;
	private Double min;
	private Long count;
}
