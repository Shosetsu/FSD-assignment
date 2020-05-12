package com.fsd.emart.account.controller;

import java.io.Serializable;

import lombok.Data;

@Data
public class SignupContainer implements Serializable {

	/** UID */
	private static final long serialVersionUID = 69213144390736757L;
	
	private String accountId;
	private String password;
	private String email;
	private String telNumber;
	private boolean asSeller;
	private String coName;
	private String postalAddr;
	private String GSTIN;
	private String bankDetail;
}
