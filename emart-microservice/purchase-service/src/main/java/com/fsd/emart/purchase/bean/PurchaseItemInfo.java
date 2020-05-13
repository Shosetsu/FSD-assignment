package com.fsd.emart.purchase.bean;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PurchaseItemInfo {

	private String id;
	private BigDecimal price;
	private Integer count;
}
