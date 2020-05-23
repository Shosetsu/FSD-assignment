package com.fsd.emart.common.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

public class AuthConstants {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_ID = "hid";
    public static final String HEADER_AUTH = "ats";

    public static final String CRYPT_KEY = "!z%C*F-JaNdRgUkXp2s5v8y/A?D(G+KbPeShVmYq3t6w9z$C&E)H@McQfTjWnZr4";

    public static final long TOKEN_TERM = 1000L * 3600 * 24 * 30;

    public static final Map<String, PasswordEncoder> AUTH_MAP;

    static {
        Map<String, PasswordEncoder> auth_map = new HashMap<>();
        auth_map.put("auth_a", new BCryptPasswordEncoder());
        auth_map.put("auth_b", new Pbkdf2PasswordEncoder());

        AUTH_MAP = Collections.unmodifiableMap(auth_map);
    }

    public static final String CURRENT_AUTH_TYPE = "auth_a";

    public static final int SESSION_KEY_LENGTH = 16;

    public static final int RANDOM_PASSWORD_LENGTH = 12;
}
