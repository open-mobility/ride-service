package com.o4.mobility.test;

import com.o4.mobility.actions.BookingActions;
import com.o4.mobility.data.BookingDataHelper;
import com.o4.mobility.dtos.Booking;
import com.o4.mobility.dtos.BookingRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for BookingController
 *
 * @author M. Mazhar Hassan
 * @see com.o4.mobility.controllers.BookingController
 * @since 1.0
 */
@AutoConfigureMockMvc
@SpringBootTest
public class TestBookingController {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testFind() throws Exception {

        //ARRANGE: - Create object that we want to find
        Booking booking = BookingActions.createRandomBooking(mockMvc);

        //ACTION: - Find the Booking
        Booking found = BookingActions.findById(mockMvc, booking.getBookingId());

        //ASSERT: - Validate if correct object is found
        assertEquals(booking.getStatus(), found.getStatus());
        assertEquals(booking.getPickup(), found.getPickup());
    }

    @Test
    void testCreate() throws Exception {

        //ARRANGE: Create Request
        BookingRequest request = BookingDataHelper.createPredefinedRequest();

        //ACTION: Create actual booking
        Booking saved = BookingActions.create(mockMvc, request);

        //ASSERT:
        //  Validate REST level response should be correct
        assertEquals(request.getStatus(), saved.getStatus());
        assertEquals(request.getPickup(), saved.getPickup());
        assertEquals(request.getDropOff(), saved.getDropOff());
        assertNotNull(saved.getBookingId()); //ensure ID auto assigned
        assertNotNull(saved.getDtCreated()); //ensure audit works
        assertNotNull(saved.getDtUpdated()); //ensure audit works

        //  Ensure changes are written in DB and not just returned form cache
        Booking found = BookingActions.findById(mockMvc, saved.getBookingId());
        assertNotNull(found, "Create booking not found in database");

        assertEquals(request.getStatus(), found.getStatus());
        assertEquals(request.getPickup(), found.getPickup());
        assertEquals(request.getDropOff(), found.getDropOff());
        assertNotNull(found.getBookingId()); //ensure ID auto assigned
        assertNotNull(found.getDtCreated()); //ensure audit works
        assertNotNull(found.getDtUpdated()); //ensure audit works

    }

}
