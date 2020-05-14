package com.fsd.emart.common.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "t_item")
public class ItemInfo implements Serializable {

	/** UID */
	private static final long serialVersionUID = -2218899802295585516L;

	@Id
	@Column
	private String itemId;

	@Column
	private String name;

	@Column
	private String manufacturer;

	@Column
	private String category;

	@Column
	private String detail;

	@Column
	private BigDecimal price;

	@Column
	private Integer stock;

	@Column
	private String ownerId;

	@Column
	private Integer soldCount;

	@Column
	private Timestamp updateTime;

	@Column
	private Timestamp createTime;

	/**
	 * 0:normal,1:blocked,2:archived
	 */
	@Column
	private Integer status;

}
