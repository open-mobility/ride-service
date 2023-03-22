package com.o4.mobility.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Booking {
    private Long bookingId;
    private Long customerId;
    private Long driverId;
    private Coordinates pickup;
    private Coordinates dropOff;
}
