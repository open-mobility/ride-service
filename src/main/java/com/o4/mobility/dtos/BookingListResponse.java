package com.o4.mobility.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class BookingListResponse extends ListResponse {
    private BookingListRequest request;
    private List<Booking> records;

    public void setRecords(List<Booking> bookings) {
        this.records = bookings;
    }
}
