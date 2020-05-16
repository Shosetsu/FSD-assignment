package com.fsd.emart.common.util;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fsd.emart.common.constans.AuthConstants;

@Component
public class CryptoUtil {

    PasswordEncoder passwordEncoder =
        new DelegatingPasswordEncoder(AuthConstants.CURRENT_AUTH_TYPE, AuthConstants.AUTH_MAP);

    public String encodePassword(String password) {
        return this.passwordEncoder.encode(password);
    }

    public boolean comparePassword(String password, String encodedPassword) {
        return this.passwordEncoder.matches(password, encodedPassword);
    }

    public String createSessionKey() {
        return new String(Hex.encode(KeyGenerators.secureRandom(AuthConstants.SESSION_KEY_LENGTH).generateKey()));
    }

    public String createRandomPassword() {
        return new String(Hex.encode(KeyGenerators.secureRandom(AuthConstants.RANDOM_PASSWORD_LENGTH).generateKey()));
    }

}
