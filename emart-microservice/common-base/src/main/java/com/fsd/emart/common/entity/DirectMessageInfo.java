package com.fsd.emart.common.entity;

import java.io.Serializable;
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
@Table(name = "t_message")
public class DirectMessageInfo implements Serializable {

    /** UID */
    private static final long serialVersionUID = -6497737526387127725L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    private String msgId;

    @Column
    private String sendby;

    @Column
    private String sendto;

    @Column
    private String text;

    @Column
    private Timestamp createTime;

}
