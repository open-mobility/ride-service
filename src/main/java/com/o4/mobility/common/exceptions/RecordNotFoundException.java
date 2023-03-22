package com.o4.mobility.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends ApplicationException {
    public RecordNotFoundException(int code, String message) {
        super(code, message);
    }

    public RecordNotFoundException(String message) {
        this(RECORD_NOT_FOUND, message);
    }

    public RecordNotFoundException() {
        this("Record not found.");
    }
}
