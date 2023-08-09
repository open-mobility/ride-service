package com.o4.mobility.services.impl;

import com.o4.mobility.dao.entities.BookingEntity;
import com.o4.state.StateEngine;
import org.springframework.stereotype.Component;

@Component
public class BookingStateMachine {
    private final StateEngine stateEngine;

    public BookingStateMachine() {
        stateEngine = new StateEngine();
        stateEngine.add("PENDING", "CANCEL", "IN_PROGRESS");
        stateEngine.add("IN_PROGRESS", "DONE");
        stateEngine.add("CANCEL");
        stateEngine.add("DONE");
    }

    public void validateTransition(BookingEntity booking, String status) {
        stateEngine.validate(booking.getStatus().name(), status);
    }

}
