package com.o4.mobility.services;

import com.o4.mobility.common.dtos.BooleanResponse;
import com.o4.mobility.dtos.Booking;
import com.o4.mobility.dtos.BookingListRequest;
import com.o4.mobility.dtos.BookingListResponse;
import com.o4.mobility.dtos.BookingRequest;

import java.util.List;

public interface BookingService {

    /**
     * Create a new booking
     *
     * @param booking
     * @return booking
     */
    Booking save(BookingRequest booking);

    /**
     * Update existing booking
     *
     * @param booking
     * @return
     */
    Booking update(Long bookingId, BookingRequest booking);


    /**
     * Find a booking by Id
     *
     * @param bookingId
     * @return
     */
    Booking findById(Long bookingId);

    BooleanResponse deleteById(Long id);

    Booking updateStatus(Long id, String status);

    BookingListResponse list(BookingListRequest request);
}
