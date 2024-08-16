package com.o4.mobility.dtos;

import com.o4.mobility.common.dtos.BookingStatus;
import com.o4.mobility.common.dtos.Coordinates;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingRequest {
    @NotNull
    private Long customerId;
    private Long driverId;
    private String geoHash;
    private BookingStatus status;
    @NotNull
    private Coordinates pickup;
    @NotNull
    private Coordinates dropOff;
    private Integer paymentMethod;
    private Integer serviceType;
}
