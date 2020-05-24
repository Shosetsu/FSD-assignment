package com.fsd.emart.common.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "t_order")
public class OrderInfo implements Serializable {

    /** UID */
    private static final long serialVersionUID = 1536354983346218329L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    private String orderId;

    @Column
    private String itemId;

    @Column
    private BigDecimal price;

    @Column
    private BigInteger count;

    @Column
    private BigDecimal amount;

    @Column
    private String sellerId;

    @Column
    private String buyerId;

    @Column
    private Timestamp orderTime;

}
