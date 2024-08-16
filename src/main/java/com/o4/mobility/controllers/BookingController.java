package com.o4.mobility.controllers;


import com.o4.mobility.common.dtos.BooleanResponse;
import com.o4.mobility.dtos.*;
import com.o4.mobility.services.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Booking controller helps in creating bookings
 *
 * <p>This class provide complete CRUD operations
 * </p>
 *
 * @author M. Mazhar Hassan
 * @see BookingService
 * @since 1.0
 */

@Slf4j
@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @Operation(summary = "Find a booking by its id")
    @GetMapping("/{id}")
    public Booking get(@PathVariable("id") Long id) {
        log.info("Find Booking Id {}", id);

        return service.findById(id);
    }


    @GetMapping
    public BookingListResponse list(BookingListRequest request) {

        return service.list(request);
    }

    @Operation(summary = "Creates a new booking")
    @PostMapping({"/", ""})
    public Booking save(@Validated @RequestBody BookingRequest booking) {
        return service.save(booking);
    }

    @Operation(summary = "Update an existing booking")
    @PutMapping("/{id}")
    public Booking update(@PathVariable("id") Long id, @RequestBody @Validated BookingRequest booking) {
        return service.update(id, booking);
    }

    @Operation(summary = "Update booking status")
    @PatchMapping("/{id}/status")
    public Booking updateStatus(@PathVariable("id") Long id, @RequestParam("status") String status) {
        return service.updateStatus(id, status);
    }

    @Operation(summary = "Deletes an existing booking")
    @DeleteMapping("/{id}")
    public BooleanResponse delete(@PathVariable("id") Long id) {
        return service.deleteById(id);
    }
}
