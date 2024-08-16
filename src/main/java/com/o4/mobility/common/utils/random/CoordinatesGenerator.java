package com.o4.mobility.common.utils.random;

import com.o4.mobility.common.dtos.Coordinates;

import java.util.Random;

/**
 * Utility for Generating GPS Coordinates
 *
 * @author M. Mazhar Hassan
 * @see Coordinates
 * @since 1.0
 */
public interface CoordinatesGenerator {

    Random RANDOM = new Random();

    /**
     * Generates a random valid Coordinates object.
     *
     * @return A Coordinates object with random latitude and longitude.
     */
    static Coordinates generateRandomCoordinates() {
        double latitude = -90 + (90 - (-90)) * RANDOM.nextDouble();
        double longitude = -180 + (180 - (-180)) * RANDOM.nextDouble();

        // Format latitude and longitude to 6 decimal places
        String formattedLatitude = String.format("%.6f", latitude);
        String formattedLongitude = String.format("%.6f", longitude);

        return new Coordinates(formattedLatitude, formattedLongitude);
    }
}
