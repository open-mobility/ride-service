package com.o4.mobility.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValueUtils {
    public static boolean isId(Long id) {
        return null != id && id > 0;
    }

    public static boolean empty(Object object) {
        return null == object;
    }
}
