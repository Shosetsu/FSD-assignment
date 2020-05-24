package com.fsd.emart.seller.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class ChangeStatusInfo implements Serializable {
    /** UID */
    private static final long serialVersionUID = -1068985572856264127L;

    private String iid;
    private Integer status;
}
