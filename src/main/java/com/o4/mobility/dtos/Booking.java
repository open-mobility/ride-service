package com.o4.mobility.dtos;

import com.o4.mobility.common.dtos.BookingStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Booking {
    private Long bookingId;
    private Long customerId;
    private Long driverId;
    private BookingStatus status;
    private Coordinates pickup;
    private Coordinates dropOff;
    private Date dtCreated;
    private Date dtUpdated;
}
