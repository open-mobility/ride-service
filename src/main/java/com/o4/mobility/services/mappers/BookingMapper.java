package com.o4.mobility.services.mappers;

import com.o4.mobility.dao.entities.BookingEntity;
import com.o4.mobility.dtos.Booking;
import com.o4.mobility.dtos.Coordinates;

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

    public static BookingEntity map(Booking src) {
        BookingEntity dest = new BookingEntity();
        dest.setCustomerId(src.getCustomerId());
        dest.setDriverId(src.getDriverId());
        dest.setBookingId(src.getBookingId());
        dest.setStatus(src.getStatus());
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

    public static void overwrite(BookingEntity dest, Booking src) {
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
}
