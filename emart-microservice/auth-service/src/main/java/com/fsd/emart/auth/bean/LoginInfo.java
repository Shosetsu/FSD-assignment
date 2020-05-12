package com.fsd.emart.auth.bean;

import lombok.Data;

@Data
public class LoginInfo {
	private String accountType;
	private String accountId;
	private String sessionKey;

}
