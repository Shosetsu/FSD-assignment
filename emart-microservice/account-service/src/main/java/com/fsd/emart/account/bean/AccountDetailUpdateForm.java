package com.fsd.emart.account.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class AccountDetailUpdateForm implements Serializable {

    /** UID */
    private static final long serialVersionUID = 9135994390495149108L;

    private String newPassword;
    private String password;
    private String email;
    private String telNumber;

    private String accountType;

    private String coName;
    private String postalAddr;
    private String GSTIN;
    private String bankDetail;
}
