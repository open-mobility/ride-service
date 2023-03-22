package com.o4.mobility.controllers;


import com.o4.mobility.dtos.Booking;
import com.o4.mobility.services.BookingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Booking get(@PathVariable("id") Long id) {
        return service.findById(id);
    }
}
