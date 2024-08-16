package com.o4.mobility.services.validators;

import com.o4.mobility.common.dtos.Coordinates;
import com.o4.mobility.common.exceptions.BadInputException;
import com.o4.mobility.common.exceptions.Errors;
import com.o4.mobility.common.utils.ValueUtils;
import com.o4.mobility.dtos.BookingRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Validator class for Booking Request
 *
 * @author M. Mazhar Hassan
 * @see BookingRequest
 * @since 1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookingValidator {

    public static void validate(BookingRequest booking) {
        if (ValueUtils.empty(booking)) {
            throw new BadInputException("Booking object is missing");
        }

        if (!ValueUtils.isPositiveNumber(booking.getCustomerId())) {
            throw new BadInputException(Errors.BAD_CUSTOMER_ID, "A valid customer id is required");
        }

        if (null != booking.getDriverId() && !ValueUtils.isPositiveNumber(booking.getDriverId())) {
            throw new BadInputException(Errors.BAD_DRIVER_ID, "A valid driver id is required");
        }

        if (isInvalidLocation(booking.getPickup())) {
            throw new BadInputException(Errors.BAD_PICKUP_LOCATION, "A valid Pickup location is required");
        }

        if (isInvalidLocation(booking.getDropOff())) {
            throw new BadInputException(Errors.BAD_DROP_OFF_LOCATION, "A valid DropOff location is required");
        }
    }


    public static void validateId(Long id) {
        if (ValueUtils.isPositiveNumber(id)) {
            throw new BadInputException("Booking ID is missing");
        }
    }

    public static void validate(Long id, BookingRequest booking) {
        validateId(id);
        validate(booking);
    }

    private static boolean isInvalidLocation(Coordinates location) {
        return null == location ||
                null == location.getLatitude()
                || null == location.getLongitude();
    }
}
