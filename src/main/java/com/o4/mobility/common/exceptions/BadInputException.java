package com.o4.mobility.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadInputException extends ApplicationException {

    public BadInputException(int code, String message) {
        super(code, message);
    }

    public BadInputException(String message) {
        this(BAD_INPUT_PARAMETER, message);
    }

    public BadInputException() {
        this("Bad Request, Input parameter is not correct.");
    }
}
