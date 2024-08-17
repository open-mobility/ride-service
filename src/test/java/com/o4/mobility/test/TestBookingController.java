package com.o4.mobility.test;

import com.o4.mobility.actions.BookingActions;
import com.o4.mobility.common.dtos.ExceptionResponse;
import com.o4.mobility.common.dtos.events.EventType;
import com.o4.mobility.common.exceptions.Errors;
import com.o4.mobility.common.utils.JsonUtils;
import com.o4.mobility.data.BookingDataHelper;
import com.o4.mobility.dtos.Booking;
import com.o4.mobility.dtos.BookingRequest;
import com.o4.mobility.services.event.MobilityEventPublisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @MockBean
    private MobilityEventPublisher publisher;

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
    void testFind_with404() throws Exception {
        //ACTION: - Find the Booking
        ExceptionResponse response = BookingActions.findByWithError(mockMvc, 27L);
        assertNotNull(response);
        assertEquals(Errors.RECORD_NOT_FOUND, response.getCode());
        assertEquals("Record not found.", response.getMessage());
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

    @Test
    void testCreate_verifyPublishEvent() throws Exception {

        BookingRequest request = BookingDataHelper.createRandomRequest();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJson(request))
                )
                .andDo(print()).andExpect(status().isOk());

        // Verify the interaction
        verify(publisher, times(1)).publishCustomEvent(eq(EventType.BOOKING_CREATED), any(Booking.class));
    }


    @Test
    void testCreate_badPickup() throws Exception {

        //ARRANGE: Create Request
        BookingRequest request = BookingDataHelper.createPredefinedRequest();
        request.setPickup(null);

        //ACTION: Create actual booking
        ExceptionResponse response = BookingActions.createWithError(mockMvc, request, 400);

        //ASSERT
        assertNotNull(response);
        assertEquals(response.getCode(), Errors.BAD_PICKUP_LOCATION);
        assertEquals(response.getMessage(), "A valid Pickup location is required");
    }

    @Test
    void testCreate_badDropOff() throws Exception {

        //ARRANGE: Create Request
        BookingRequest request = BookingDataHelper.createPredefinedRequest();
        request.setDropOff(null);

        //ACTION: Create actual booking
        ExceptionResponse response = BookingActions.createWithError(mockMvc, request, 400);

        //ASSERT
        assertNotNull(response);
        assertEquals(response.getCode(), Errors.BAD_DROP_OFF_LOCATION);
        assertEquals(response.getMessage(), "A valid DropOff location is required");
    }

}
