package com.fsd.emart.auth.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fsd.emart.auth.controller.LoginResult;
import com.fsd.emart.auth.entity.AuthInfo;
import com.fsd.emart.auth.service.AuthService;

@SpringBootTest
@RunWith(SpringRunner.class)
class AuthServiceImplTest {

	@Autowired
	private AuthService sut;

	@Test
	void testLogin() {
		AuthInfo info = new AuthInfo();
		info.setId("Setsu");
		info.setPassword("SSS12345");
		LoginResult result = sut.login(info);
	}

	@Test
	void testLogout() {
		fail("尚未实现");
	}

	@Test
	void testCheckSession() {
		fail("尚未实现");
	}

	@Test
	void testGetAccountType() {
		fail("尚未实现");
	}

}
