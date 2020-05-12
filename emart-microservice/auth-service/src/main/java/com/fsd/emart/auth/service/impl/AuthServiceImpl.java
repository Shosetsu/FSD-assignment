package com.fsd.emart.auth.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fsd.emart.auth.constants.AuthConstants;
import com.fsd.emart.auth.controller.LoginResult;
import com.fsd.emart.auth.service.AuthService;
import com.fsd.emart.common.dao.AuthDao;
import com.fsd.emart.common.dao.CustomerDao;
import com.fsd.emart.common.dao.SessionDao;
import com.fsd.emart.common.entity.AuthInfo;
import com.fsd.emart.common.entity.SessionInfo;
import com.fsd.emart.common.exception.BizException;

@Service
public class AuthServiceImpl implements AuthService {

	@Value("${auth.type}")
	private String auth_type;
	@Value("${auth.session-key.length}")
	private int session_key_length;

	@Resource
	private AuthDao authDao;

	@Resource
	private CustomerDao customerDao;

	@Resource
	private SessionDao sessionDao;

	@Override
	public LoginResult login(AuthInfo info) {
		LoginResult result = new LoginResult();
		Optional<AuthInfo> referInfo = authDao.findById(info.getId().toLowerCase());

		if(!referInfo.isPresent()) {
			throw new BizException("Invalid password or account id!");			
		}
		
		PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(auth_type, AuthConstants.AUTH_MAP);
		if (!passwordEncoder.matches(info.getPassword(), referInfo.get().getPassword())) {
			throw new BizException("Invalid password or account id!");
		}

		String session_key = new String(Hex.encode(KeyGenerators.secureRandom(session_key_length).generateKey()));

		// Register login info
		SessionInfo sessionInfo = new SessionInfo();
		sessionInfo.setId(info.getId().toLowerCase());
		sessionInfo.setSessionKey(session_key);
		sessionInfo.setLastLoginTime(new Timestamp(new Date().getTime()));
		sessionDao.saveAndFlush(sessionInfo);

		result.setAccountId(info.getId());
		result.setAccountType(getAccountType(info.getId()));
		result.setSessionKey(session_key);

		return result;
	}

	@Override
	public void logout(SessionInfo info) {
		if (checkSession(info.getId(),info.getSessionKey())) {
			sessionDao.deleteById(info.getId());
		}
	}

	@Override
	public boolean checkSession(String id, String sessionKey) {
		Optional<SessionInfo> tempInfo = sessionDao.findByIdAndSessionKey(id,sessionKey);

		return tempInfo.isPresent()
				&& tempInfo.get().getLastLoginTime().getTime() > (new Date().getTime() - 1000L * 60 * 60 * 24 * 30);
	}

	@Override
	public String getAccountType(String accountId) {
		return customerDao.getOne(accountId.toLowerCase()).getType();
	}

}
