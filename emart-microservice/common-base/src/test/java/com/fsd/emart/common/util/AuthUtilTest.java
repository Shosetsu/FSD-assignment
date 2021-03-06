package com.fsd.emart.common.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import com.fsd.emart.common.dao.AuthDao;
import com.fsd.emart.common.dao.CustomerDao;
import com.fsd.emart.common.dao.SessionDao;
import com.fsd.emart.common.entity.CustomerInfo;
import com.fsd.emart.common.entity.SessionInfo;
import com.fsd.emart.common.exception.AuthException;

@SpringBootTest
@SpringBootConfiguration
class AuthUtilTest {

    @InjectMocks
    private AuthUtil sut;

    @Mock
    private AuthDao authDao;
    @Mock
    private CustomerDao customerDao;
    @Mock
    private SessionDao sessionDao;

    @Test
    void testCheckSession() {
        // Test not exist session
        when(sessionDao.findByIdAndSessionKey(anyString(), anyString())).thenReturn(Optional.ofNullable(null));
        assertFalse(sut.checkSession("id", "sessionKey"));

        // Test old session
        SessionInfo value = new SessionInfo();
        value.setLastLoginTime(Timestamp.valueOf("1990-12-25 22:00:00.000"));
        when(sessionDao.findByIdAndSessionKey(anyString(), anyString())).thenReturn(Optional.ofNullable(value));
        assertFalse(sut.checkSession("id", "sessionKey"));

        // Test new session
        value = new SessionInfo();
        value.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
        when(sessionDao.findByIdAndSessionKey(anyString(), anyString())).thenReturn(Optional.ofNullable(value));
        assertTrue(sut.checkSession("id", "sessionKey"));
    }

    @Test
    void testAuthCheck() {
        // Test same id
        sut.authCheck("aaaa", "sessionKey", "aaaa");

        // Test M
        sut.authCheck("aaaa", "M", "bbb");

        // Test not M and different id
        String msg = "";
        try {
            sut.authCheck("aaaa", "S", "bbb");
        } catch (AuthException e) {
            msg = e.getMessage();
        }
        assertEquals(msg, "System Error.");
    }

    @Test
    void testIsExistAccountId() {
        when(customerDao.findById(anyString())).thenReturn(Optional.ofNullable(null));
        assertFalse(sut.isExistAccountId("asdf"));

        when(customerDao.findById(anyString())).thenReturn(Optional.of(new CustomerInfo()));
        assertTrue(sut.isExistAccountId("asdf"));
    }

}
