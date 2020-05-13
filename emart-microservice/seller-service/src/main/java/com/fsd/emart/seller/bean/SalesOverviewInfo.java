package com.fsd.emart.seller.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class SalesOverviewInfo implements Serializable {
	/** UID */
	private static final long serialVersionUID = 4343367517598045502L;

	private Integer count;
	private BigDecimal amount;

}
