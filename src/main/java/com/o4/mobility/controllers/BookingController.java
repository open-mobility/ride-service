package com.o4.mobility.controllers;


import com.o4.mobility.common.dtos.BooleanResponse;
import com.o4.mobility.dao.entities.BookingEntity;
import com.o4.mobility.dtos.Booking;
import com.o4.mobility.services.BookingService;
import java.util.Collections;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<BookingEntity> list() {
        return Collections.emptyList();
    }
    @PostMapping("/")
    public Booking save(@RequestBody Booking booking) {
        return service.save(booking);
    }

    @PutMapping("/")
    public Booking update(@RequestBody Booking booking) {
        return service.update(booking);
    }

    @DeleteMapping("/{id}")
    public BooleanResponse delete(@PathVariable("id") Long id) {
        return service.deleteById(id);
    }


}
