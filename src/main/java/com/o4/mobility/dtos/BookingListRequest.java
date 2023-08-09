package com.o4.mobility.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingListRequest extends ListRequest{
    private Long driverId;
    private Long customerId;
    private String status;
}
