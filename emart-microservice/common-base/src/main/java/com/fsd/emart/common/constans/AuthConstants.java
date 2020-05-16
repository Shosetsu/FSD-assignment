package com.fsd.emart.common.constans;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

public class AuthConstants {

    public static final Map<String, PasswordEncoder> AUTH_MAP;

    static {
        Map<String, PasswordEncoder> auth_map = new HashMap<>();
        auth_map.put("auth_a", new BCryptPasswordEncoder());
        auth_map.put("auth_b", new Pbkdf2PasswordEncoder());

        AUTH_MAP = Collections.unmodifiableMap(auth_map);
    }

    public static String CURRENT_AUTH_TYPE = "auth_a";

    public static int SESSION_KEY_LENGTH = 16;

    public static int RANDOM_PASSWORD_LENGTH = 12;
}
