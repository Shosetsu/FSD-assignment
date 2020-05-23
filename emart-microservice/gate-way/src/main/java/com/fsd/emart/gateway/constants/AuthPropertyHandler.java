package com.fsd.emart.gateway.constants;

import java.io.IOException;
import java.util.Properties;

public class AuthPropertyHandler {
    private static final String INIT_FILE_PATH = "/conf/auth.properties";
    private static Properties properties;

    private AuthPropertyHandler() {}

    static {
        loadProperties();
    }

    private static void loadProperties() {
        properties = new Properties();
        try {
            properties.load(AuthPropertyHandler.class.getResourceAsStream("/conf/auth.properties"));
        } catch (IOException e) {
            System.out.println(String.format("Load Failed:%s", INIT_FILE_PATH));
        }

    }

    public static String getProperty(final String key) {
        return getProperty(key, "");
    }

    public static String getProperty(final String key, final String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

}
