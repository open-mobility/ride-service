package com.o4.mobility.services.validators;

import com.o4.mobility.common.exceptions.BadInputException;
import com.o4.mobility.common.exceptions.Errors;
import com.o4.mobility.common.utils.ValueUtils;
import com.o4.mobility.dtos.Booking;
import com.o4.mobility.dtos.Coordinates;

public class BookingValidator {


    public static void validate(Booking booking) {
        if (ValueUtils.empty(booking)) {
            throw new BadInputException("Booking object is missing");
        }

        if (!ValueUtils.isId(booking.getCustomerId())) {
            throw new BadInputException(Errors.BAD_CUSTOMER_ID, "A valid customer id is required");
        }

        if (!ValueUtils.isId(booking.getDriverId())) {
            throw new BadInputException(Errors.BAD_DRIVER_ID, "A valid driver id is required");
        }

        if (isInvalidLocation(booking.getPickup())) {
            throw new BadInputException(Errors.BAD_PICKUP_LOCATION, "A valid Pickup location is required");
        }

        if (isInvalidLocation(booking.getDropOff())) {
            throw new BadInputException(Errors.BAD_DROP_OFF_LOCATION, "A valid DropOff location is required");
        }
    }

    public static void validateWithId(Booking booking) {
        validate(booking);
        if (!ValueUtils.isId(booking.getBookingId())) {
            throw new BadInputException("Booking ID is missing");
        }
    }

    private static boolean isInvalidLocation(Coordinates location) {
        return null == location ||
                null == location.getLatitude()
                || null == location.getLongitude();
    }
}
