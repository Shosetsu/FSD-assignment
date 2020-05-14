package com.fsd.emart.auth.service;

import com.fsd.emart.auth.bean.LoginInfo;
import com.fsd.emart.common.entity.AuthInfo;
import com.fsd.emart.common.entity.CustomerInfo;
import com.fsd.emart.common.entity.SessionInfo;

public interface AuthService {

	/**
	 * Login
	 * 
	 * @param AuthInfo
	 * @return SessionInfo
	 */
	LoginInfo login(AuthInfo info);

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
	 * get Customer Info
	 * 
	 * @param accountId
	 * @return CustomerInfo
	 */
	CustomerInfo getCustomerInfo(String accountId);

}
