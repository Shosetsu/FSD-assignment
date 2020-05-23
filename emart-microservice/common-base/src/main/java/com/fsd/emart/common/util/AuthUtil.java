package com.fsd.emart.common.util;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fsd.emart.common.constants.AuthConstants;
import com.fsd.emart.common.constants.Constants;
import com.fsd.emart.common.dao.CustomerDao;
import com.fsd.emart.common.dao.ItemDao;
import com.fsd.emart.common.dao.SessionDao;
import com.fsd.emart.common.entity.SessionInfo;
import com.fsd.emart.common.exception.AuthException;

@Component
public class AuthUtil {

    @Resource
    private CustomerDao customerDao;

    @Resource
    private SessionDao sessionDao;

    @Resource
    private ItemDao itemDao;

    public String getAccountType(String accountId) {
        return customerDao.getOne(accountId).getType();
    }

    public boolean checkSession(String accountId, String sessionKey) {
        Optional<SessionInfo> tempInfo = sessionDao.findByIdAndSessionKey(accountId, sessionKey);

        return tempInfo.isPresent()
            && tempInfo.get().getLastLoginTime().getTime() > (System.currentTimeMillis() - AuthConstants.TOKEN_TERM);
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
            && !Constants.ROLE_ADMIN.equals(getAccountType(accountId))) {
            throw new AuthException("System Error.");
        }
    }

    public boolean isExistAccountId(String accountId) {
        return customerDao.findById(accountId).isPresent();
    }

}
