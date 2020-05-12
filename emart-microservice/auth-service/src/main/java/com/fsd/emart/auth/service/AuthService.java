package com.fsd.emart.auth.service;

import com.fsd.emart.auth.controller.LoginResult;
import com.fsd.emart.common.entity.AuthInfo;
import com.fsd.emart.common.entity.SessionInfo;

public interface AuthService {

	/**
	 * Login
	 * 
	 * @param AuthInfo
	 * @return SessionInfo
	 */
	LoginResult login(AuthInfo info);

	/**
	 * Logout
	 * 
	 * @param SessionInfo
	 */
	void logout(SessionInfo info);

	/**
	 * check Session Info
	 * 
	 * @param id
	 * @param sessionKey
	 * @return valid or invalid
	 */
	boolean checkSession(String id,String sessionKey);

	/**
	 * get Account Type
	 * 
	 * @param accountId
	 * @return accountType
	 */
	String getAccountType(String accountId);

}
