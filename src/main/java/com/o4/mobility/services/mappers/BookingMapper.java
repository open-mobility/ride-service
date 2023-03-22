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
        dest.setPickup(new Coordinates(src.getPickupLat(), src.getPickupLong()));
        dest.setDropOff(new Coordinates(src.getDropoffLat(), src.getDropoffLong()));
        dest.setDriverId(src.getDriverId());
        dest.setBookingId(src.getBookingId());

        return dest;
    }

    public static BookingEntity map(Booking src) {
        BookingEntity dest = new BookingEntity();
        dest.setCustomerId(src.getCustomerId());
        dest.setDriverId(src.getDriverId());
        dest.setBookingId(src.getBookingId());

        if (null != src.getPickup()) {
            dest.setPickupLat(src.getPickup().getLatitude());
            dest.setPickupLong(src.getPickup().getLongitude());
        }

        if (null != src.getDropOff()) {
            dest.setDropoffLat(src.getDropOff().getLatitude());
            dest.setDropoffLong(src.getDropOff().getLongitude());
        }
        return dest;
    }

    public static void mapTo(BookingEntity dest, Booking src) {
        dest.setDriverId(src.getDriverId());
        //dest.setBookingId(src.getBookingId());

        if (null != src.getPickup()) {
            dest.setPickupLat(src.getPickup().getLatitude());
            dest.setPickupLong(src.getPickup().getLongitude());
        }

        if (null != src.getDropOff()) {
            dest.setDropoffLat(src.getDropOff().getLatitude());
            dest.setDropoffLong(src.getDropOff().getLongitude());
        }
    }
}
