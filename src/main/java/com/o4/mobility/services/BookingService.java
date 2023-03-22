package com.o4.mobility.services;

import com.o4.mobility.dtos.Booking;

import java.util.List;

public interface BookingService {

    /**
     * Create a new booking
     *
     * @param booking
     * @return booking
     */
    Booking save(Booking booking);

    /**
     * Update existing booking
     *
     * @param booking
     * @return
     */
    Booking update(Booking booking);


    /**
     * Find a booking by Id
     *
     * @param bookingId
     * @return
     */
    Booking findById(Long bookingId);

    /**
     * List all bookings
     *
     * @return List of Booking
     */
    List<Booking> list();
}
