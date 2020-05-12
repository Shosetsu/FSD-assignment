package com.fsd.emart.auth.controller;

import lombok.Data;

@Data
public class LoginResult {
	private String accountType;
	private String accountId;
	private String sessionKey;

}
