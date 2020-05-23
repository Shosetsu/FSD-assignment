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
@Table(name = "t_cart")
public class CartInfo implements Serializable {

    /** UID */
    private static final long serialVersionUID = 5193833692649739115L;

    @Id
    @Column
    private String accountId;

    @Column
    private String cartItems;

    @Column
    private String cartCounts;

    @Column
    private Timestamp updateTime;

}
