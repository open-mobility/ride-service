package com.o4.mobility.data;

import com.o4.mobility.common.dtos.BookingStatus;
import com.o4.mobility.common.dtos.Coordinates;
import com.o4.mobility.common.utils.random.CoordinatesGenerator;
import com.o4.mobility.common.utils.random.RandomLongGenerator;
import com.o4.mobility.dtos.BookingRequest;

import java.util.Map;

/**
 * A utility to help in crating request and response objects
 * You can use ready-made objects to speed up your testing
 *
 * @author M. Mazhar Hassan
 * @see com.o4.mobility.controllers.BookingController
 * @since 1.0
 */
public interface BookingDataHelper {
    Map<String, Coordinates> locations = Map.of(
            "ZERO", createCoordinates("0", "0"),
            "LDA", createCoordinates("31.422241", "74.229647"),
            "SHOKAT_KHANAM", createCoordinates("31.447560", "74.270356")
    );
    Long CUSTOMER_ID = 1L;

    static BookingRequest createPredefinedRequest(Coordinates pickUp, Coordinates dropOff, Long customerId) {
        BookingRequest request = new BookingRequest();
        request.setPickup(pickUp);
        request.setDropOff(dropOff);
        request.setCustomerId(customerId);
        request.setServiceType(1);
        request.setStatus(BookingStatus.PENDING);
        request.setPaymentMethod(1);

        return request;
    }

    static BookingRequest createPredefinedRequest() {

        return createPredefinedRequest(getPickup(), getDropOff(), CUSTOMER_ID);
    }

    static BookingRequest createRandomRequest() {

        return createPredefinedRequest(
                CoordinatesGenerator.generateRandomCoordinates(),
                CoordinatesGenerator.generateRandomCoordinates(),
                RandomLongGenerator.getLong(300,3000));
    }

    static Coordinates createCoordinates(String latitude, String longitude) {

        return new Coordinates(latitude, longitude);
    }

    static Coordinates getLocation(String locationKey) {

        return locations.containsKey(locationKey) ? locations.get(locationKey) : locations.get("ZERO");
    }

    static Coordinates getPickup() {

        return getLocation("LDA");
    }

    static Coordinates getDropOff() {

        return getLocation("SHOKAT_KHANAM");
    }
}
