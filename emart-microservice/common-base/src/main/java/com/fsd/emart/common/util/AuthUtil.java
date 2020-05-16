package com.fsd.emart.common.util;

import java.util.Date;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fsd.emart.common.constans.Constants;
import com.fsd.emart.common.dao.CustomerDao;
import com.fsd.emart.common.dao.SessionDao;
import com.fsd.emart.common.entity.SessionInfo;
import com.fsd.emart.common.exception.AuthException;

@Component
public class AuthUtil {

    @Resource
    private CustomerDao customerDao;

    @Resource
    private SessionDao sessionDao;

    public boolean checkSession(String accountId, String sessionKey) {
        Optional<SessionInfo> tempInfo = sessionDao.findByIdAndSessionKey(accountId, sessionKey);

        return tempInfo.isPresent()
            && tempInfo.get().getLastLoginTime().getTime() > (new Date().getTime() - 1000L * 60 * 60 * 24 * 30);
    }

    public void froceCheck(String accountId, String sessionKey) {
        if (!checkSession(accountId, sessionKey)) {
            throw new AuthException("Not login.");
        }
    }

    public void authCheck(String accountId, String sessionKey, String targetId) {
        // session exist?
        froceCheck(accountId, sessionKey);

        if (!StringUtil.emptyToBlank(targetId).equals(accountId)
            && !Constants.ROLE_ADMIN.equals(customerDao.getOne(accountId).getType())) {
            throw new AuthException("System Error.");
        }
    }

    public boolean isExistAccountId(String accountId) {
        return customerDao.findById(accountId).isPresent();
    }

}
