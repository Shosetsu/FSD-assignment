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
@Table(name = "t_auth")
public class AuthInfo implements Serializable {

    /** UID */
    private static final long serialVersionUID = 8874383918901366170L;

    @Id
    @Column
    private String id;

    @Column
    private String password;

    @Column
    private Timestamp updateTime;

}
