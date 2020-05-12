package com.fsd.emart.account.service;

import java.sql.Timestamp;

import com.fsd.emart.common.entity.CustomerInfo;

public interface AccountService {
	public void register(CustomerInfo info,String newPassword);
	
	public void unregist(String accountId,String password);
	
	public void findAccount(String mail);
	
	public Timestamp getSellerCreateTime(String accountId);
	
	public CustomerInfo getAccountDetail(String accountId);
	
	public void updateAccountDetail(CustomerInfo info);
}
