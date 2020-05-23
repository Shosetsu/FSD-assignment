package com.fsd.emart.gateway.constants;

public class GatewayConstants {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String CRYPT_KEY = "!z%C*F-JaNdRgUkXp2s5v8y/A?D(G+KbPeShVmYq3t6w9z$C&E)H@McQfTjWnZr4";
    public static final long TOKEN_TERM = 1000L * 3600 * 24 * 30;

    public static final String HEADER_ID = "hid";
    public static final String HEADER_ROLE = "rt";

    public static final String ROLE_ANY = "A";
    public static final String ROLE_BUYER = "B";
    public static final String ROLE_SELLER = "S";
    public static final String ROLE_ADMIN = "M";
}
