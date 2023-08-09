package com.o4.mobility.common.dtos;

import java.util.HashSet;
import java.util.Set;

public enum BookingStatus {
    PENDING, DONE, CANCEL, IN_PROGRESS;

    private static final Set<String> ENUM_NAMES = new HashSet<>();

    static {
        for (BookingStatus status : BookingStatus.values()) {
            ENUM_NAMES.add(status.name());
        }
    }

    @Override
    public String toString() {
        return name();
    }

    public static boolean contains(String value) {
        return ENUM_NAMES.contains(value);
    }
}
