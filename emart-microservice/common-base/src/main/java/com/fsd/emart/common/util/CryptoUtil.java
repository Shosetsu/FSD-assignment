package com.fsd.emart.common.util;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fsd.emart.common.constants.AuthConstants;

@Component
public class CryptoUtil {

    private static final String SALT = "";

    PasswordEncoder passwordEncoder =
        new DelegatingPasswordEncoder(AuthConstants.CURRENT_AUTH_TYPE, AuthConstants.AUTH_MAP);

    public String encodePassword(String password) {
        return this.passwordEncoder.encode(password + SALT);
    }

    public boolean comparePassword(String password, String encodedPassword) {
        return this.passwordEncoder.matches(password + SALT, encodedPassword);
    }

    public String createSessionKey() {
        return new String(Hex.encode(KeyGenerators.secureRandom(AuthConstants.SESSION_KEY_LENGTH).generateKey()));
    }

    public String createRandomPassword() {
        return new String(Hex.encode(KeyGenerators.secureRandom(AuthConstants.RANDOM_PASSWORD_LENGTH).generateKey()));
    }

}
