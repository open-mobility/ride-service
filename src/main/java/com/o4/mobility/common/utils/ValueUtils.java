package com.o4.mobility.common.utils;

public class ValueUtils {
    public static boolean isId(Long id) {
        return null != id && id.longValue() > 0;
    }
}
