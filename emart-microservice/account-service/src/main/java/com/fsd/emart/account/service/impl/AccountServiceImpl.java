package com.fsd.emart.account.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fsd.emart.account.service.AccountService;
import com.fsd.emart.common.constans.Constants;
import com.fsd.emart.common.dao.AuthDao;
import com.fsd.emart.common.dao.CustomerDao;
import com.fsd.emart.common.dao.SessionDao;
import com.fsd.emart.common.entity.AuthInfo;
import com.fsd.emart.common.entity.CustomerInfo;
import com.fsd.emart.common.exception.BizException;
import com.fsd.emart.common.util.CryptoUtil;

@Service
public class AccountServiceImpl implements AccountService {

	@Resource
	private AuthDao authDao;

	@Resource
	private CustomerDao customerDao;

	@Resource
	private SessionDao sessionDao;

	@Resource
	private CryptoUtil cryptoUtil;

	@Override
	public void register(CustomerInfo info, String newPassword) {

		// check mail exist
		if (customerDao.findByEmail(info.getEmail()).isPresent()) {
			throw new BizException("The Email address is registered.");
		}

		// check accountId exist
		if (customerDao.findById(info.getId()).isPresent()) {
			throw new BizException("The Account Id is registered.");
		}

		// pre-process
		AuthInfo authInfo = new AuthInfo();
		authInfo.setId(info.getId());
		authInfo.setPassword(cryptoUtil.getEncoder().encode(newPassword));
		authInfo.setUpdateTime(info.getCreateTime());

		// process
		customerDao.save(info);
		authDao.save(authInfo);
	}

	@Override
	public void unregist(String accountId, String password) {

		// check authorization
		Optional<AuthInfo> authInfo = authDao.findById(accountId);

		if (!authInfo.isPresent()) {
			// Not Client?
			throw new BizException("System Error!");
		}

		if (!cryptoUtil.getEncoder().matches(password, authInfo.get().getPassword())) {
			throw new BizException("Password invalid.");
		}

		// process
		customerDao.deleteById(accountId);
		authDao.deleteById(accountId);
		sessionDao.deleteById(accountId);
	}

	@Override
	public void findAccount(String mail) {
		// check mail exist
		Optional<CustomerInfo> customerInfo = customerDao.findByEmail(mail);

		if (!customerInfo.isPresent()) {
			throw new BizException("The Email address is not registered.");
		}

		// process
		String newPassword = cryptoUtil.createRandomPassword();

		AuthInfo authInfo = new AuthInfo();
		authInfo.setId(customerInfo.get().getId());
		authInfo.setPassword(cryptoUtil.getEncoder().encode(newPassword));
		authInfo.setUpdateTime(new Timestamp(new Date().getTime()));

		// TODO Call mail server
	}

	@Override
	public Timestamp getSellerCreateTime(String accountId) {
		return getAccountDetail(accountId).getSellerDate();
	}

	@Override
	public CustomerInfo getAccountDetail(String accountId) {

		Optional<CustomerInfo> customerInfo = customerDao.findById(accountId);

		// check accountId exist
		if (!customerInfo.isPresent()) {
			throw new BizException("System Error!");
		}

		return customerInfo.get();
	}

	@Override
	public void updateAccountDetail(CustomerInfo info) {

		CustomerInfo currentInfo = customerDao.findById(info.getId()).orElse(null);

		// check accountId exist
		if (currentInfo == null) {
			throw new BizException("System Error!");
		}

		info.setId(currentInfo.getId());
		if (Constants.ROLE_BUYER.equals(currentInfo.getType()) && Constants.ROLE_SELLER.equals(info.getType())) {
			info.setSellerDate(new Timestamp(new Date().getTime()));
		}

		customerDao.save(info);

	}

}
