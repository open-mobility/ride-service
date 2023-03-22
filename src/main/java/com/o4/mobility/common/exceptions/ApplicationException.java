package com.o4.mobility.common.exceptions;

public class ApplicationException extends RuntimeException implements Errors {
    private final int code;

    public ApplicationException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ApplicationException(String message) {
        this(Errors.UNKNOWN_ERROR_OCCURRED, message);
    }


    public int getCode() {
        return code;
    }
}
