package com.fsd.emart.common.util;

import com.fsd.emart.common.exception.ApplicationException;

public class StringUtil {

    private StringUtil() {}

    public static String[] splitString(String str, String regex) {
        return str.indexOf(regex) > -1 ? str.split(regex) : new String[] {str};
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static String emptyToString(String str, String defaultValue) {
        return isEmpty(str) ? defaultValue : str;
    }

    public static String emptyToBlank(String str) {
        return emptyToString(str, "");
    }

    public static String getNonblankString(String str) {
        if (isEmpty(str)) {
            throw new ApplicationException("Input invalid.");
        }

        return str;
    }
}
