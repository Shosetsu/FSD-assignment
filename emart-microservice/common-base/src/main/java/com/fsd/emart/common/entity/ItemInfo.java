package com.fsd.emart.common.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
    @Column(length = 10)
    private String itemId;

    @Column(length = 45)
    private String name;

    @Column(length = 45)
    private String manufacturer;

    @Column(length = 45)
    private String category;

    @Column(length = 5000)
    private String detail;

    @Column
    private BigDecimal price;

    @Column(length = 11)
    private BigInteger stock;

    @Column
    private String ownerId;

    @Column(length = 11)
    private BigInteger soldCount;

    @Column
    private Timestamp updateTime;

    @Column
    private Timestamp createTime;

    /**
     * 0:normal,1:blocked,2:archived
     */
    @Column(length = 1)
    private Integer status;

}
