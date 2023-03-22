package com.o4.mobility.services.impl;

import com.o4.mobility.common.exceptions.BadInputException;
import com.o4.mobility.common.exceptions.RecordNotFoundException;
import com.o4.mobility.common.utils.ValueUtils;
import com.o4.mobility.dtos.Booking;
import com.o4.mobility.dtos.Coordinates;
import com.o4.mobility.services.BookingService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookingServiceImpl implements BookingService {

    Map<Long, Booking> bookings = new HashMap<>();

    public BookingServiceImpl() {
        bookings.put(401L, createBooking(3001L, "PL1", "PL2"));
        bookings.put(402L, createBooking(3002L, "PL1", "PL2"));
        bookings.put(403L, createBooking(3003L, "PL1", "PL2"));
    }

    private Booking createBooking(Long customerId, String PickupLat, String PickupLong) {
        Booking booking = new Booking();
        booking.setCustomerId(customerId);
        booking.setPickup(new Coordinates(PickupLat, PickupLong));

        return booking;
    }

    @Override
    public Booking save(Booking booking) {
        return null;
    }

    @Override
    public Booking update(Booking booking) {
        return null;
    }

    @Override
    public Booking findById(Long bookingId) {

        if (!ValueUtils.isId(bookingId)) {
            throw new BadInputException("Booking id is missing or not valid positive integer");
        }

        if (!bookings.containsKey(bookingId)) {
            throw new RecordNotFoundException();
        }

        return bookings.get(bookingId);
    }

    @Override
    public List<Booking> list() {
        return null;
    }
}
