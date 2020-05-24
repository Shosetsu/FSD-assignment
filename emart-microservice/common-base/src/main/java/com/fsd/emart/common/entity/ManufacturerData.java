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
@Table(name = "s_manufacturer")
public class ManufacturerData implements Serializable {

    /** UID */
    private static final long serialVersionUID = -717060792549894036L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(length = 3)
    private String id;

    @Column(length = 255)
    private String name;

    @Column(length = 255)
    private String createUser;

    @Column
    private Timestamp createTime;

}
