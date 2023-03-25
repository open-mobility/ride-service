package com.o4.mobility.common.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BooleanResponse {
    private boolean success;
    private String message;

    public static BooleanResponse success() {
        return success("Operation success.");
    }

    public static BooleanResponse success(String message) {
        return respond(true, message);
    }

    public static BooleanResponse failed() {
        return failed("Operation failed.");
    }

    public static BooleanResponse failed(String message) {
        return respond(false, message);
    }

    private static BooleanResponse respond(boolean success, String message) {
        return new BooleanResponse(success, message);
    }
}
