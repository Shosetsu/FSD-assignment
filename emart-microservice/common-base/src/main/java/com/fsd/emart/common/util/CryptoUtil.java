package com.fsd.emart.common.util;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fsd.emart.common.constans.AuthConstants;

@Component
public class CryptoUtil {

	private String CURRENT_AUTH_TYPE = "auth_a";

	private int SESSION_KEY_LENGTH = 16;

	private int RANDOM_PASSWORD_LENGTH = 12;

	PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(CURRENT_AUTH_TYPE, AuthConstants.AUTH_MAP);

	public PasswordEncoder getEncoder() {
		return passwordEncoder;
	}

	public String createSessionKey() {
		return new String(Hex.encode(KeyGenerators.secureRandom(SESSION_KEY_LENGTH).generateKey()));
	}

	public String createRandomPassword() {
		return new String(Hex.encode(KeyGenerators.secureRandom(RANDOM_PASSWORD_LENGTH).generateKey()));
	}

}
