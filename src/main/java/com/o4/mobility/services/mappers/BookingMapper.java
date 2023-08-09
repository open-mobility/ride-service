package com.o4.mobility.services.mappers;

import com.o4.mobility.dao.entities.BookingEntity;
import com.o4.mobility.dtos.Booking;
import com.o4.mobility.dtos.BookingRequest;
import com.o4.mobility.dtos.Coordinates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookingMapper {

    private BookingMapper() {

    }

    public static Booking map(BookingEntity src) {
        Booking dest = new Booking();
        dest.setCustomerId(src.getCustomerId());
        dest.setStatus(src.getStatus());
        dest.setPickup(new Coordinates(src.getPickupLat(), src.getPickupLong()));
        dest.setDropOff(new Coordinates(src.getDropOffLat(), src.getDropOffLong()));
        dest.setDriverId(src.getDriverId());
        dest.setBookingId(src.getBookingId());
        dest.setDtCreated(src.getDtCreated());
        dest.setDtUpdated(src.getDtUpdated());

        return dest;
    }

    public static BookingEntity mapRequest(BookingRequest src) {
        BookingEntity dest = new BookingEntity();
        dest.setCustomerId(src.getCustomerId());

        if (null != src.getPickup()) {
            dest.setPickupLat(src.getPickup().getLatitude());
            dest.setPickupLong(src.getPickup().getLongitude());
        }

        if (null != src.getDropOff()) {
            dest.setDropOffLat(src.getDropOff().getLatitude());
            dest.setDropOffLong(src.getDropOff().getLongitude());
        }
        return dest;
    }

    public static BookingEntity map(Booking src) {
        BookingEntity dest = mapRequest(src);
        dest.setDriverId(src.getDriverId());
        dest.setBookingId(src.getBookingId());
        dest.setStatus(src.getStatus());

        return dest;
    }

    public static void overwrite(BookingEntity dest, BookingRequest src) {
        dest.setDriverId(src.getDriverId());

        if (null != src.getPickup()) {
            dest.setPickupLat(src.getPickup().getLatitude());
            dest.setPickupLong(src.getPickup().getLongitude());
        }

        if (null != src.getDropOff()) {
            dest.setDropOffLat(src.getDropOff().getLatitude());
            dest.setDropOffLong(src.getDropOff().getLongitude());
        }
        dest.setStatus(src.getStatus());
    }

    public static List<Booking> map(List<BookingEntity> entities) {
        if (null == entities || entities.isEmpty()) {
            return Collections.emptyList();
        }

        List<Booking> bookings = new ArrayList<>();
        for(BookingEntity entity: entities) {
            bookings.add(map(entity));
        }

        return bookings;
    }
}
