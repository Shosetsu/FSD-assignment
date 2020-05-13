package com.fsd.emart.order.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class OrderDetail implements Serializable {

	/** UID */
	private static final long serialVersionUID = -8758930923709077235L;

	private String orderId;
	private String seller;
	private String buyer;
	private String name;
	private String[] category;
	private String manufacturer;
	private BigDecimal price;
	private Integer count;
	private BigDecimal amount;
	private Timestamp timestamp;
	private String goodId;
}
