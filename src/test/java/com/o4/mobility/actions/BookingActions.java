package com.o4.mobility.actions;

import com.o4.mobility.common.dtos.ExceptionResponse;
import com.o4.mobility.common.utils.JsonUtils;
import com.o4.mobility.data.BookingDataHelper;
import com.o4.mobility.dtos.Booking;
import com.o4.mobility.dtos.BookingRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Booking CRUD utility functions that can help you with testing
 * You can focus on testing instead of making HTTP calls logistics
 *
 * @author M. Mazhar Hassan
 * @see com.o4.mobility.controllers.BookingController
 * @since 1.0
 */
public interface BookingActions {

    static Booking create(MockMvc mockMvc, BookingRequest validRequest) throws Exception {

        String response = mockMvc.perform(post("/api/v1/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJson(validRequest))
                )
                .andDo(print()).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Booking booking = JsonUtils.toObject(response, Booking.class);
        assertEquals(validRequest.getCustomerId(), booking.getCustomerId());
        assertEquals(validRequest.getStatus(), booking.getStatus());

        return booking;
    }

    static ExceptionResponse createWithError(MockMvc mockMvc, BookingRequest validRequest, int expectedStatusCode) throws Exception {

        String response = mockMvc.perform(post("/api/v1/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJson(validRequest))
                )
                .andDo(print()).andExpect(status().is(expectedStatusCode))
                .andReturn().getResponse().getContentAsString();

        return JsonUtils.toObject(response, ExceptionResponse.class);
    }

    static Booking findById(MockMvc mockMvc, Long bookingId) throws Exception {
        String response = mockMvc.perform(get("/api/v1/bookings/" + bookingId))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Booking booking = JsonUtils.toObject(response, Booking.class);
        assertEquals(bookingId, booking.getBookingId());

        return booking;
    }

    static ExceptionResponse findByWithError(MockMvc mockMvc, Long bookingId) throws Exception {
        String response = mockMvc.perform(get("/api/v1/bookings/" + bookingId))
                .andDo(print())
                .andExpect(status().is(404))
                .andReturn().getResponse().getContentAsString();

         return JsonUtils.toObject(response, ExceptionResponse.class);
    }

    static Booking createRandomBooking(MockMvc mockMvc) throws Exception {
        BookingRequest request = BookingDataHelper.createRandomRequest();

        return create(mockMvc, request);
    }

}
