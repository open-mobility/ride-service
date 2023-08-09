package com.o4.mobility.common.exceptions;

public class ApplicationException extends RuntimeException implements Errors {
    private final int code;

    public ApplicationException(int code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
    }

    public ApplicationException(int code, String message) {
        this(code, message, null);
    }

    public ApplicationException(String message) {
        this(Errors.UNKNOWN_ERROR_OCCURRED, message);
    }

    public ApplicationException(String message, Throwable throwable) {
        this(Errors.UNKNOWN_ERROR_OCCURRED, message);
    }


    public int getCode() {
        return code;
    }
}
