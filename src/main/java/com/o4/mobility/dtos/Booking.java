package com.o4.mobility.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Booking extends BookingRequest {
    private Long bookingId;
    private Date dtCreated;
    private Date dtUpdated;
}
