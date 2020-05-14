package com.fsd.emart.common.entity;

import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "t_customer")
public class CustomerInfo implements Serializable {

	/** UID */
	private static final long serialVersionUID = -9036397848083162009L;

	@Column
	private String type;
	@Id
	@Column
	private String id;
	@Column
	private String email;
	@Column
	private String tel;
	@Column
	private String company;
	@Column
	private String address;
	@Column
	private String gstin;
	@Column
	private String bankDetail;
	@Column
	private Timestamp sellerDate;
	@Column
	private Timestamp createTime;



}
