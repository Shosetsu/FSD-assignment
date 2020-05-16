package com.fsd.emart.auth.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fsd.emart.auth.bean.LoginInfo;
import com.fsd.emart.common.dao.AuthDao;
import com.fsd.emart.common.dao.CustomerDao;
import com.fsd.emart.common.dao.SessionDao;
import com.fsd.emart.common.entity.AuthInfo;
import com.fsd.emart.common.entity.CustomerInfo;
import com.fsd.emart.common.entity.SessionInfo;
import com.fsd.emart.common.exception.ApplicationException;
import com.fsd.emart.common.util.AuthUtil;
import com.fsd.emart.common.util.CryptoUtil;

@SpringBootTest
@SpringBootConfiguration
class AuthServiceImplTest {

    @InjectMocks
    private AuthServiceImpl sut;

    @Mock
    private AuthDao authDao;
    @Mock
    private CustomerDao customerDao;
    @Mock
    private SessionDao sessionDao;
    @Mock
    private CryptoUtil cryptoUtil;
    @Mock
    private AuthUtil authUtil;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLogin() {

        AuthInfo info = new AuthInfo();
        info.setId("Setsu");
        info.setPassword("SSS12345");

        // Test Id is invalid
        when(authDao.findById(anyString())).thenReturn(Optional.ofNullable(null));
        String errMsg = "";
        try {
            sut.login(info);
        } catch (ApplicationException e) {
            errMsg = e.getMessage();
        }

        assertEquals("Invalid password or account id!", errMsg);

        // Test password is invalid
        AuthInfo value = new AuthInfo();
        value.setPassword("{auth_a}error");
        when(authDao.findById(anyString())).thenReturn(Optional.of(value));
        when(cryptoUtil.comparePassword(anyString(), anyString())).thenReturn(false);
        errMsg = "";
        try {
            sut.login(info);
        } catch (ApplicationException e) {
            errMsg = e.getMessage();
        }

        assertEquals("Invalid password or account id!", errMsg);

        // Test success
        CustomerInfo cValue = new CustomerInfo();
        cValue.setType("T");
        cValue.setId("AAAA");
        when(customerDao.getOne(anyString())).thenReturn(cValue);

        value = new AuthInfo();
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        value.setPassword("{auth_a}" + bc.encode("SSS12345"));
        when(authDao.findById(anyString())).thenReturn(Optional.of(value));
        when(cryptoUtil.comparePassword(anyString(), anyString())).thenReturn(true);
        when(cryptoUtil.createSessionKey()).thenReturn("testaaaa");
        errMsg = "";
        LoginInfo result = sut.login(info);

        assertEquals("AAAA", result.getAccountId());
        assertEquals("testaaaa", result.getSessionKey());
        assertEquals("T", result.getAccountType());
    }

    @Test
    public void testLogout() {
        SessionInfo info = new SessionInfo();
        info.setId("id");
        info.setSessionKey("1234");

        // Test invalid session logout
        when(authUtil.checkSession(anyString(), anyString())).thenReturn(false);
        sut.logout(info);
        verify(sessionDao, never()).deleteById(anyString());

        // Test valid session logout
        when(authUtil.checkSession(anyString(), anyString())).thenReturn(true);
        sut.logout(info);
        verify(sessionDao, times(1)).deleteById(anyString());
    }

    @Test
    public void testCheckSession() {
        when(authUtil.checkSession(anyString(), anyString())).thenReturn(false);
        assertFalse(sut.checkSession("aaa", "bbbbsession"));
    }

    @Test
    public void testGetCustomerInfo() {
        // Test success
        CustomerInfo cValue = new CustomerInfo();
        cValue.setType("H");
        when(customerDao.getOne(anyString())).thenReturn(cValue);

        CustomerInfo assertValue = new CustomerInfo();
        assertValue.setType("H");
        assertEquals(assertValue, sut.getCustomerInfo("Test111"));
    }

}
