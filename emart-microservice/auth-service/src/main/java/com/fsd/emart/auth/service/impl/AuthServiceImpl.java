package com.fsd.emart.auth.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fsd.emart.auth.bean.LoginInfo;
import com.fsd.emart.auth.service.AuthService;
import com.fsd.emart.common.constants.AuthConstants;
import com.fsd.emart.common.dao.AuthDao;
import com.fsd.emart.common.dao.CustomerDao;
import com.fsd.emart.common.dao.SessionDao;
import com.fsd.emart.common.entity.AuthInfo;
import com.fsd.emart.common.entity.CustomerInfo;
import com.fsd.emart.common.entity.SessionInfo;
import com.fsd.emart.common.exception.ApplicationException;
import com.fsd.emart.common.util.AuthUtil;
import com.fsd.emart.common.util.CryptoUtil;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private AuthDao authDao;

    @Resource
    private CustomerDao customerDao;

    @Resource
    private SessionDao sessionDao;

    @Resource
    private CryptoUtil cryptoUtil;

    @Resource
    private AuthUtil authUtil;

    @Override
    public LoginInfo login(AuthInfo info) {
        LoginInfo result = new LoginInfo();
        Optional<AuthInfo> referInfo = authDao.findById(info.getId());

        if (!referInfo.isPresent()) {
            throw new ApplicationException("Invalid password or account id!");
        }

        if (!cryptoUtil.comparePassword(info.getPassword(), referInfo.get().getPassword())) {
            throw new ApplicationException("Invalid password or account id!");
        }

        // Register new session key
        String session_key = cryptoUtil.createSessionKey();
        SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.setId(info.getId());
        sessionInfo.setSessionKey(session_key);
        sessionInfo.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
        sessionDao.saveAndFlush(sessionInfo);

        // Set response
        CustomerInfo cusInfo = getCustomerInfo(info.getId());
        result.setAccountId(cusInfo.getId());
        result.setAccountType(cusInfo.getType());
        result.setAuthKey(Jwts.builder().setId(info.getId()).setSubject(session_key)
            .setExpiration(new Date(System.currentTimeMillis() + AuthConstants.TOKEN_TERM))
            .signWith(Keys.hmacShaKeyFor(AuthConstants.CRYPT_KEY.getBytes())).compact());

        return result;
    }

    @Override
    public void logout(String accountId) {
        sessionDao.deleteById(accountId);
    }

    @Override
    public boolean checkSession(String id, String sessionKey) {
        return authUtil.checkSession(id, sessionKey);
    }

    @Override
    public CustomerInfo getCustomerInfo(String accountId) {
        return customerDao.getOne(accountId);
    }

}
