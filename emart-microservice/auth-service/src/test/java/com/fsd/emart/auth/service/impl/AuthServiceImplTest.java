package com.fsd.emart.auth.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.fsd.emart.auth.controller.LoginResult;
import com.fsd.emart.auth.dao.AuthDao;
import com.fsd.emart.auth.dao.CustomerDao;
import com.fsd.emart.auth.dao.SessionDao;
import com.fsd.emart.auth.entity.AuthInfo;
import com.fsd.emart.auth.entity.CustomerInfo;
import com.fsd.emart.auth.entity.SessionInfo;
import com.fsd.emart.common.exception.BizException;

@SpringBootTest
@RunWith(SpringRunner.class)
class AuthServiceImplTest {

	@InjectMocks
	private AuthServiceImpl sut;

	@Mock
	private AuthDao authDao;
	@Mock
	private CustomerDao customerDao;
	@Mock
	private SessionDao sessionDao;

	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.initMocks(this);

		ReflectionTestUtils.setField(sut, "auth_type", "auth_a");
		ReflectionTestUtils.setField(sut, "session_key_length", 16);
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
		} catch (BizException e) {
			errMsg = e.getMessage();
		}

		assertEquals("Invalid password or account id!", errMsg);

		// Test password is invalid
		AuthInfo value = new AuthInfo();
		value.setPassword("{auth_a}error");
		when(authDao.findById(anyString())).thenReturn(Optional.of(value));
		errMsg = "";
		try {
			sut.login(info);
		} catch (BizException e) {
			errMsg = e.getMessage();
		}

		assertEquals("Invalid password or account id!", errMsg);

		// Test success
		CustomerInfo cValue = new CustomerInfo();
		cValue.setType("T");
		when(customerDao.getOne(anyString())).thenReturn(cValue);

		value = new AuthInfo();
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		value.setPassword("{auth_a}" + bc.encode("SSS12345"));
		when(authDao.findById(anyString())).thenReturn(Optional.of(value));
		errMsg = "";
		LoginResult result = sut.login(info);

		assertEquals("Setsu", result.getAccountId());
		assertNotNull(result.getSessionKey());
		assertEquals("T", result.getAccountType());
	}

	@Test
	public void testLogout() {
		SessionInfo info = new SessionInfo();
		info.setId("id");
		info.setSessionKey("1234");
		
		//Test invalid session logout
		when(sessionDao.findByIdAndSessionKey(anyString(),anyString())).thenReturn(Optional.ofNullable(null));
		sut.logout(info);
		verify(sessionDao,never()).deleteById(anyString());


		//Test valid session logout
		doNothing().when(sessionDao).deleteById(anyString());
		SessionInfo value = new SessionInfo();
		value.setLastLoginTime(new Timestamp(new Date().getTime()));
		when(sessionDao.findByIdAndSessionKey(anyString(),anyString())).thenReturn(Optional.ofNullable(value));
		sut.logout(info);
		verify(sessionDao,times(1)).deleteById(anyString());
	}

	@Test
	public void testCheckSession() {
		//Test not exist session
		when(sessionDao.findByIdAndSessionKey(anyString(),anyString())).thenReturn(Optional.ofNullable(null));
		assertFalse(sut.checkSession("id", "sessionKey"));
		
		//Test old session
		SessionInfo value = new SessionInfo();
		value.setLastLoginTime(Timestamp.valueOf("1990-12-25 22:00:00.000"));
		when(sessionDao.findByIdAndSessionKey(anyString(),anyString())).thenReturn(Optional.ofNullable(value));
		assertFalse(sut.checkSession("id", "sessionKey"));
		
		//Test new session
		value = new SessionInfo();
		value.setLastLoginTime(new Timestamp(new Date().getTime()));
		when(sessionDao.findByIdAndSessionKey(anyString(),anyString())).thenReturn(Optional.ofNullable(value));
		assertTrue(sut.checkSession("id", "sessionKey"));
	}

	@Test
	public void testGetAccountType() {
		// Test success
		CustomerInfo cValue = new CustomerInfo();
		cValue.setType("H");
		when(customerDao.getOne(anyString())).thenReturn(cValue);

		assertEquals("H", sut.getAccountType("Test111"));
	}

}
