package com.o4.mobility.common;

import com.o4.mobility.common.dtos.ExceptionResponse;
import com.o4.mobility.common.exceptions.ApplicationException;
import com.o4.mobility.common.exceptions.RecordNotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {NoHandlerFoundException.class, HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ExceptionResponse> handleError404(ServletException exception, HttpServletRequest request) {
        return new ResponseEntity<>(getServletExceptionResponse(exception, request), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<ExceptionResponse> handleAllExceptions(Exception exception) {
        if (exception instanceof ApplicationException) {
            return handleApplicationException((ApplicationException) exception);
        } else if (exception instanceof HttpMessageNotReadableException) {
            return handleFormattingException((HttpMessageNotReadableException) exception);
        }
        logger.error("Global exception handler", exception);
        return new ResponseEntity<>(ExceptionResponse.of(ApplicationException.UNKNOWN_ERROR_OCCURRED, "Unknown error occured please contact site admin"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ExceptionResponse> handleFormattingException(HttpMessageNotReadableException exception) {
        String message;

        if (exception.getCause() instanceof com.fasterxml.jackson.databind.exc.InvalidFormatException) {
            message = "Parsing error, Date format might not be correct";
        } else {
            message = "Parser error, please make sure JSON is properly formatted";
        }

        return new ResponseEntity<>(ExceptionResponse.of(ApplicationException.UNABLE_TO_MAP_OBJECT,
                message),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    protected ResponseEntity<ExceptionResponse> handleApplicationException(ApplicationException exception) {
        HttpStatus status = HttpStatus.CONFLICT;
        if (exception instanceof RecordNotFoundException) {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(ExceptionResponse.of(exception), status);
    }

    private ExceptionResponse getServletExceptionResponse(ServletException exception, HttpServletRequest request) {
        ExceptionResponse response;
        if (exception instanceof NoHandlerFoundException) {
            response = ExceptionResponse.of(ApplicationException.ENDPOINT_NOT_FOUND,
                    "END POINT not found " + request.getRequestURI());
        } else {
            response = ExceptionResponse.of(ApplicationException.HTTP_METHOD_NOT_SUPPORTED,
                    "HTTP Method Not supported: " + request.getMethod());
        }

        return response;
    }

}