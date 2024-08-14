package com.o4.mobility.common.exceptions;

import java.util.Collection;

import static com.o4.mobility.common.exceptions.Errors.BAD_ID_VALUE;

/**
 * It helps in validating user inputs
 *
 * <p>Validation utility class</p>
 *
 * @author M. Mazhar Hassan
 * @see BadInputException
 * @since 1.0
 */
public class InputValidator {

    public static void validateId(Integer value) {
        validateId(value, "ID must be a positive integer");
    }
    public static void validateId(Integer value, String message) {
        validateId(value, message, BAD_ID_VALUE);
    }
    public static void validateId(Integer value, String message, int code) {
        if (null == value || value < 0) {
            throw new BadInputException(code, message);
        }
    }
    public static void validateNotEmpty(String value) {
        validateNotEmpty(value, "String");
    }

    public static void validateNotEmpty(String value, String fieldName) {
        if (null == value || value.isEmpty()) {
            throw new BadInputException(fieldName +" value cannot be null or empty string");
        }
    }

    public static void validateList(Collection collection) {
        validateList(collection, Errors.EMPTY_OR_NULL_COLLECTION);
    }

    public static void validateList(Collection collection, int code) {
        if (null == collection || collection.isEmpty()) {
            throw new BadInputException(code, "List value cannot be null or empty");
        }
    }
}
