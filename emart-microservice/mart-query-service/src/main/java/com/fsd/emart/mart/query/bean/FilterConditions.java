package com.fsd.emart.mart.query.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class FilterConditions implements Serializable {

	/** UID */
	private static final long serialVersionUID = 152532627781L;

	private String category;
	private String manufacturer;
	private BigDecimal priceF;
	private BigDecimal priceT;

}
