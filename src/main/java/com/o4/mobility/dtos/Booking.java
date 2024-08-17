package com.o4.mobility.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
public class Booking extends BookingRequest {
    private Long bookingId;
    private Date dtCreated;
    private Date dtUpdated;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(bookingId, booking.bookingId) && Objects.equals(dtCreated, booking.dtCreated) && Objects.equals(dtUpdated, booking.dtUpdated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, dtCreated, dtUpdated);
    }
}
