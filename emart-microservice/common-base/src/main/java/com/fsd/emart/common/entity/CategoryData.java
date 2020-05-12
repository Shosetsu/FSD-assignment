package com.fsd.emart.common.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "s_category")
public class CategoryData implements Serializable {

	/** UID */
	private static final long serialVersionUID = 4049443688922991540L;

	@Id
	@Column
	private String name;
	@Column
	private String create_user;
	@Column
	private Timestamp create_date;

}
