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
@Table(name = "t_session")
public class SessionInfo implements Serializable {

    /** UID */
    private static final long serialVersionUID = 2514589262873053104L;

    @Id
    @Column
    private String id;

    @Column
    private String sessionKey;

    @Column
    private Timestamp lastLoginTime;

}
