package com.fsd.emart.account.service.impl;

import java.sql.Timestamp;

import com.fsd.emart.account.service.AccountService;
import com.fsd.emart.common.entity.CustomerInfo;

public class AccountServiceImpl implements AccountService {

	@Override
	public void register(CustomerInfo info, String newPassword) {
		// TODO

	}

	@Override
	public void unregist(String accountId, String password) {
		// TODO

	}

	@Override
	public void findAccount(String mail) {
		// TODO

	}

	@Override
	public Timestamp getSellerCreateTime(String accountId) {
		// TODO
		return null;
	}

	@Override
	public CustomerInfo getAccountDetail(String accountId) {
		// TODO
		return null;
	}

	@Override
	public void updateAccountDetail(CustomerInfo info) {
		// TODO

	}

}
